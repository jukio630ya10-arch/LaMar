package com.proyecto.controller;

import com.proyecto.service.CategoriaService;
import com.proyecto.service.ProductoService;
import com.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;

@Controller
public class MenuViewController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/categorias")
    public String listarCategorias(Model model) {
        model.addAttribute("lista", categoriaService.listarCategorias());
        return "categoria/lista";
    }

    @GetMapping("/productos")
    public String listarProductos(Model model) {
        model.addAttribute("lista", productoService.listarProductos());
        return "productos/lista";
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("lista", usuarioService.listarUsuarios());
        return "Usuario/lista";
    }

    @GetMapping("/movimientos")
    public String listarMovimientos(Model model) {
        model.addAttribute("lista", Collections.emptyList());
        return "movimiento/lista";
    }

    @GetMapping("/ventas")
    public String listarVentas(Model model) {
        model.addAttribute("lista", Collections.emptyList());
        return "ventas/lista";
    }

    @GetMapping("/entradas")
    public String listarEntradas(Model model) {
        model.addAttribute("lista", Collections.emptyList());
        return "ventas/entrada";
    }
}
