package com.proyecto.controller;

import com.proyecto.model.DetallePedido;
import com.proyecto.service.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detalles")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @GetMapping
    public List<DetallePedido> listarDetalles() {
        return detallePedidoService.listarDetalles();
    }

    @GetMapping("/{id}")
    public Optional<DetallePedido> obtenerDetalle(@PathVariable Integer id) {
        return detallePedidoService.obtenerDetallePorId(id);
    }

    @PostMapping
    public DetallePedido crearDetalle(@RequestBody DetallePedido detalle) {
        return detallePedidoService.guardarDetalle(detalle);
    }

    @PutMapping("/{id}")
    public DetallePedido actualizarDetalle(@PathVariable Integer id, @RequestBody DetallePedido detalle) {
        detalle.setId(id);
        return detallePedidoService.guardarDetalle(detalle);
    }

    @DeleteMapping("/{id}")
    public void eliminarDetalle(@PathVariable Integer id) {
        detallePedidoService.eliminarDetalle(id);
    }
}

