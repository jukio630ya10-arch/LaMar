package com.proyecto.controller;

import com.proyecto.model.Reserva;
import com.proyecto.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> listarReservas() {
        return reservaService.listarReservas();
    }

    @GetMapping("/{id}")
    public Optional<Reserva> obtenerReserva(@PathVariable Integer id) {
        return reservaService.obtenerReservaPorId(id);
    }

    @PostMapping
    public Reserva crearReserva(@RequestBody Reserva reserva) {
        return reservaService.guardarReserva(reserva);
    }

    @PutMapping("/{id}")
    public Reserva actualizarReserva(@PathVariable Integer id, @RequestBody Reserva reserva) {
        reserva.setId(id);
        return reservaService.guardarReserva(reserva);
    }

    @DeleteMapping("/{id}")
    public void eliminarReserva(@PathVariable Integer id) {
        reservaService.eliminarReserva(id);
    }
}

