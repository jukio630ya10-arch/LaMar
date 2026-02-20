package com.proyecto.controller;

import com.proyecto.model.Cliente;
import com.proyecto.model.Mesa;
import com.proyecto.model.Pedido;
import com.proyecto.model.Reserva;
import com.proyecto.service.CategoriaService;
import com.proyecto.service.ClienteService;
import com.proyecto.service.MesaService;
import com.proyecto.service.PedidoService;
import com.proyecto.service.ProductoService;
import com.proyecto.service.ReservaService;
import com.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class MenuViewController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MesaService mesaService;

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("mesas", mesaService.listarMesas());
        model.addAttribute("reservas", reservaService.listarReservas());
        model.addAttribute("pedidos", pedidoService.listarPedidos());
        model.addAttribute("clientes", clienteService.listarClientes());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "menu-admin";
    }

    @PostMapping("/home/mesas/{id}")
    public String actualizarMesa(@PathVariable Integer id, @ModelAttribute Mesa mesa) {
        mesaService.obtenerMesaPorId(id).ifPresent(actual -> {
            actual.setNumero(mesa.getNumero());
            actual.setCapacidad(mesa.getCapacidad());
            mesaService.guardarMesa(actual);
        });
        return "redirect:/home";
    }

    @PostMapping("/home/clientes/{id}")
    public String actualizarCliente(@PathVariable Integer id, @ModelAttribute Cliente cliente) {
        clienteService.obtenerClientePorId(id).ifPresent(actual -> {
            actual.setNombre(cliente.getNombre());
            actual.setEmail(cliente.getEmail());
            actual.setTelefono(cliente.getTelefono());
            clienteService.guardarCliente(actual);
        });
        return "redirect:/home";
    }

    @PostMapping("/home/pedidos/{id}")
    public String actualizarPedido(@PathVariable Integer id, @ModelAttribute Pedido pedido) {
        pedidoService.obtenerPedidoPorId(id).ifPresent(actual -> {
            actual.setEstado(pedido.getEstado());
            actual.setTotal(pedido.getTotal());
            pedidoService.guardarPedido(actual);
        });
        return "redirect:/home";
    }

    @PostMapping("/home/reservas/{id}")
    public String actualizarReserva(@PathVariable Integer id, @ModelAttribute Reserva reserva) {
        reservaService.obtenerReservaPorId(id).ifPresent(actual -> {
            actual.setFecha(reserva.getFecha());
            actual.setHora(reserva.getHora());
            reservaService.guardarReserva(actual);
        });
        return "redirect:/home";
    }

    
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
