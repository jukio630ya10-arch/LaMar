package com.proyecto.service;

import com.proyecto.model.DetallePedido;
import com.proyecto.repository.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public List<DetallePedido> listarDetalles() {
        return detallePedidoRepository.findAll();
    }

    public DetallePedido guardarDetalle(DetallePedido detalle) {
        return detallePedidoRepository.save(detalle);
    }

    public Optional<DetallePedido> obtenerDetallePorId(Integer id) {
        return detallePedidoRepository.findById(id);
    }

    public void eliminarDetalle(Integer id) {
        detallePedidoRepository.deleteById(id);
    }
}

