package com.proyecto.config;

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

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
	}


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // URLs públicas
                .requestMatchers("/", "/login", "/web/clientes/crear").permitAll()
                // URLs para ADMIN solo
                .requestMatchers("/web/productos/**", "/web/categorias/**", "/web/usuarios/**").hasRole("ADMIN")
                // URLs para CLIENTE
                .requestMatchers("/web/reservas/**", "/web/pedidos/**").hasRole("CLIENTE")
                // cualquier otra URL requiere autenticación
                .anyRequest().authenticated()
            )
            // Form login
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .permitAll()
            )
            // Logout
            .logout(logout -> logout.permitAll())
            .csrf(csrf -> csrf.disable()); // temporalmente deshabilitado para pruebas

        return http.build();
    }
    
   
}

