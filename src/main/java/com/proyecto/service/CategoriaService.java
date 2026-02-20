package com.proyecto.service;

import com.proyecto.model.Categoria;
import com.proyecto.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> obtenerCategoriaPorId(Integer id) {
        return categoriaRepository.findById(id);
    }

    public void eliminarCategoria(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
