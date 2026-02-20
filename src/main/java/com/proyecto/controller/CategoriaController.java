package com.proyecto.controller;

import com.proyecto.model.Categoria;
import com.proyecto.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @GetMapping("/{id}")
    public Optional<Categoria> obtenerCategoria(@PathVariable Integer id) {
        return categoriaService.obtenerCategoriaPorId(id);
    }

    @PostMapping
    public Categoria crearCategoria(@RequestBody Categoria categoria) {
        return categoriaService.guardarCategoria(categoria);
    }

    @PutMapping("/{id}")
    public Categoria actualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        categoria.setId(id);
        return categoriaService.guardarCategoria(categoria);
    }

    @DeleteMapping("/{id}")
    public void eliminarCategoria(@PathVariable Integer id) {
        categoriaService.eliminarCategoria(id);
    }
}

