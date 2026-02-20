package com.proyecto.repository;

import com.proyecto.model.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Metodo para buscar usuario por email
    Optional<Usuario> findByEmail(String email);
}

