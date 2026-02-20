package com.proyecto.service;

import com.proyecto.model.Producto;
import com.proyecto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Optional<Producto> obtenerProductoPorId(Integer id) {
        return productoRepository.findById(id);
    }

    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }
}

