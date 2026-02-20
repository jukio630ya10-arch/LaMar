package com.proyecto.controller;

import com.proyecto.model.Cliente;
import com.proyecto.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public Optional<Cliente> obtenerCliente(@PathVariable Integer id) {
        return clienteService.obtenerClientePorId(id);
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.guardarCliente(cliente);
    }

    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return clienteService.guardarCliente(cliente);
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Integer id) {
        clienteService.eliminarCliente(id);
    }
}

