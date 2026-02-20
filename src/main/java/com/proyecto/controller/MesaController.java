package com.proyecto.controller;

import com.proyecto.model.Mesa;
import com.proyecto.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mesas")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @GetMapping
    public List<Mesa> listarMesas() {
        return mesaService.listarMesas();
    }

    @GetMapping("/{id}")
    public Optional<Mesa> obtenerMesa(@PathVariable Integer id) {
        return mesaService.obtenerMesaPorId(id);
    }

    @PostMapping
    public Mesa crearMesa(@RequestBody Mesa mesa) {
        return mesaService.guardarMesa(mesa);
    }

    @PutMapping("/{id}")
    public Mesa actualizarMesa(@PathVariable Integer id, @RequestBody Mesa mesa) {
        mesa.setId(id);
        return mesaService.guardarMesa(mesa);
    }

    @DeleteMapping("/{id}")
    public void eliminarMesa(@PathVariable Integer id) {
        mesaService.eliminarMesa(id);
    }
}

