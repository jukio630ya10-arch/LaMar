package com.proyecto.service;

import com.proyecto.model.Reserva;
import com.proyecto.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    public Reserva guardarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Optional<Reserva> obtenerReservaPorId(Integer id) {
        return reservaRepository.findById(id);
    }

    public void eliminarReserva(Integer id) {
        reservaRepository.deleteById(id);
    }
}

