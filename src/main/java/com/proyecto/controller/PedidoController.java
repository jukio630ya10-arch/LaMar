package com.proyecto.controller;

import com.proyecto.model.Pedido;
import com.proyecto.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public Optional<Pedido> obtenerPedido(@PathVariable Integer id) {
        return pedidoService.obtenerPedidoPorId(id);
    }

    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        return pedidoService.guardarPedido(pedido);
    }

    @PutMapping("/{id}")
    public Pedido actualizarPedido(@PathVariable Integer id, @RequestBody Pedido pedido) {
        pedido.setId(id);
        return pedidoService.guardarPedido(pedido);
    }

    @DeleteMapping("/{id}")
    public void eliminarPedido(@PathVariable Integer id) {
        pedidoService.eliminarPedido(id);
    }
}

