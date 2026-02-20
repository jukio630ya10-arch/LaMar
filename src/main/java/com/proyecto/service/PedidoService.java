package com.proyecto.service;

import com.proyecto.model.Pedido;
import com.proyecto.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> obtenerPedidoPorId(Integer id) {
        return pedidoRepository.findById(id);
    }

    public void eliminarPedido(Integer id) {
        pedidoRepository.deleteById(id);
    }
}

