package com.proyecto.service;

import com.proyecto.model.Mesa;
import com.proyecto.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    public Mesa guardarMesa(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public Optional<Mesa> obtenerMesaPorId(Integer id) {
        return mesaRepository.findById(id);
    }

    public void eliminarMesa(Integer id) {
        mesaRepository.deleteById(id);
    }
}

