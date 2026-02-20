package com.proyecto.config;

import com.proyecto.model.Usuario;
import com.proyecto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        // Convertimos el rol en autoridad para Spring Security
        SimpleGrantedAuthority autoridad = new SimpleGrantedAuthority(usuario.getRol().getNombre());

        return new User(usuario.getEmail(), usuario.getPassword(), Collections.singleton(autoridad));
    }
}

