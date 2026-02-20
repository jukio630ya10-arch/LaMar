package com.proyecto.controller;

import com.proyecto.model.Rol;
import com.proyecto.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    // Listar todos los roles
    @GetMapping
    public List<Rol> listarRoles() {
        return rolService.listarRoles();
    }

    // Obtener un rol por ID
    @GetMapping("/{id}")
    public Optional<Rol> obtenerRol(@PathVariable Integer id) {
        return rolService.obtenerRolPorId(id);
    }

    // Crear un nuevo rol
    @PostMapping
    public Rol crearRol(@RequestBody Rol rol) {
        return rolService.guardarRol(rol);
    }

    // Actualizar un rol
    @PutMapping("/{id}")
    public Rol actualizarRol(@PathVariable Integer id, @RequestBody Rol rol) {
        rol.setId(id);
        return rolService.guardarRol(rol);
    }

    // Eliminar un rol
    @DeleteMapping("/{id}")
    public void eliminarRol(@PathVariable Integer id) {
        rolService.eliminarRol(id);
    }
}

