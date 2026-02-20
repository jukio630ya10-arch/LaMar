package com.proyecto.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Autowired
    private CustomUserDetailsService customUserDetailsService;

	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
	}


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // URLs públicas
            		.requestMatchers("/", "/login", "/menu", "/web/clientes/crear").permitAll()
            		  .requestMatchers("/home/admin/**", "/home/mesas/**", "/home/clientes/**", "/home/pedidos/**", "/home/reservas/**").hasAnyAuthority("ADMIN", "ROLE_ADMIN")
                      .requestMatchers("/home/cliente/**").hasAnyAuthority("CLIENTE", "ROLE_CLIENTE", "ADMIN", "ROLE_ADMIN")
                   	// URLs para ADMIN solo
                      .requestMatchers("/web/productos/**", "/web/categorias/**", "/web/usuarios/**").hasAnyAuthority("ADMIN", "ROLE_ADMIN")
                // URLs para CLIENTE
                      .requestMatchers("/web/reservas/**", "/web/pedidos/**").hasAnyAuthority("CLIENTE", "ROLE_CLIENTE")
                   // cualquier otra URL requiere autenticación
                .anyRequest().authenticated()
            )
            // Form login
            .formLogin(form -> form
                .loginPage("/login")

                .successHandler((request, response, authentication) -> {
                    Set<String> authorities = authentication.getAuthorities().stream()
                            .map(grantedAuthority -> grantedAuthority.getAuthority().toUpperCase())
                            .collect(java.util.stream.Collectors.toSet());

                    if (authorities.contains("ROLE_ADMIN") || authorities.contains("ADMIN")) {
                        response.sendRedirect("/home/admin");
                        return;
                    }

                    response.sendRedirect("/home/cliente");
                })
                .permitAll()
            )
            // Logout
            .logout(logout -> logout.permitAll())
            .csrf(csrf -> csrf.disable()); // temporalmente deshabilitado para pruebas

        return http.build();
    }
    
   
}

