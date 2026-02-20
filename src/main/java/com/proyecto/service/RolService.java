package com.proyecto.service;

import com.proyecto.model.Rol;
import com.proyecto.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }

    public Rol guardarRol(Rol rol) {
        return rolRepository.save(rol);
    }

    public Optional<Rol> obtenerRolPorId(Integer id) {
        return rolRepository.findById(id);
    }

    public void eliminarRol(Integer id) {
        rolRepository.deleteById(id);
    }
}

