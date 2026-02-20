package com.proyecto.controller;

import com.proyecto.model.Categoria;
import com.proyecto.model.Cliente;
import com.proyecto.model.Mesa;
import com.proyecto.model.Pedido;
import com.proyecto.model.Producto;
import com.proyecto.model.Reserva;
import com.proyecto.model.Usuario;
import com.proyecto.service.CategoriaService;
import com.proyecto.service.ClienteService;
import com.proyecto.service.MesaService;
import com.proyecto.service.PedidoService;
import com.proyecto.service.ProductoService;
import com.proyecto.service.ReservaService;
import com.proyecto.service.RolService;
import com.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Optional;

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

    @Autowired
    private RolService rolService;
    
    @GetMapping("/home")
    public String home(Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority().toUpperCase())
                .anyMatch(authority -> authority.equals("ROLE_ADMIN") || authority.equals("ADMIN"));

        return isAdmin ? "redirect:/home/admin" : "redirect:/home/cliente";
    }
    
    @GetMapping("/home/cliente")
    public String homeCliente(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        return "menu-cliente";
    }

    @GetMapping("/home/admin")
    public String homeAdmin(Model model) {        model.addAttribute("mesas", mesaService.listarMesas());
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
        return "redirect:/home/admin";    }

    @PostMapping("/home/clientes/{id}")
    public String actualizarCliente(@PathVariable Integer id, @ModelAttribute Cliente cliente) {
        clienteService.obtenerClientePorId(id).ifPresent(actual -> {
            actual.setNombre(cliente.getNombre());
            actual.setEmail(cliente.getEmail());
            actual.setTelefono(cliente.getTelefono());
            clienteService.guardarCliente(actual);
        });
        return "redirect:/home/admin";    }

    @PostMapping("/home/pedidos/{id}")
    public String actualizarPedido(@PathVariable Integer id, @ModelAttribute Pedido pedido) {
        pedidoService.obtenerPedidoPorId(id).ifPresent(actual -> {
            actual.setEstado(pedido.getEstado());
            actual.setTotal(pedido.getTotal());
            pedidoService.guardarPedido(actual);
        });
        return "redirect:/home/admin";    }

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
    
    @GetMapping("/categorias/nuevo")
    public String nuevaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria/form";
    }

    @GetMapping("/categorias/editar/{id}")
    public String editarCategoria(@PathVariable Integer id, Model model) {
        Optional<Categoria> categoria = categoriaService.obtenerCategoriaPorId(id);
        if (categoria.isEmpty()) {
            return "redirect:/categorias";
        }
        model.addAttribute("categoria", categoria.get());
        return "categoria/form";
    }

    @PostMapping("/categorias/guardar")
    public String guardarCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.guardarCategoria(categoria);
        return "redirect:/home/admin";
    }

    @GetMapping("/categorias/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Integer id) {
        categoriaService.eliminarCategoria(id);
        return "redirect:/categorias";
    }

    @GetMapping("/productos")
    public String listarProductos(Model model) {
        model.addAttribute("lista", productoService.listarProductos());
        return "productos/lista";
    }
    @GetMapping("/productos/nuevo")
    public String nuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "productos/form";
    }

    @GetMapping("/productos/editar/{id}")
    public String editarProducto(@PathVariable Integer id, Model model) {
        Optional<Producto> producto = productoService.obtenerProductoPorId(id);
        if (producto.isEmpty()) {
            return "redirect:/productos";
        }
        model.addAttribute("producto", producto.get());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "productos/form";
    }

    @PostMapping("/productos/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/home/admin";
    }

    @GetMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos";
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("lista", usuarioService.listarUsuarios());
        return "Usuario/lista";
    }
    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolService.listarRoles());
        return "Usuario/form";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuario(@PathVariable Integer id, Model model) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario.isEmpty()) {
            return "redirect:/usuarios";
        }
        model.addAttribute("usuario", usuario.get());
        model.addAttribute("roles", rolService.listarRoles());
        return "Usuario/form";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.guardarUsuario(usuario);
        return "redirect:/home/admin";
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
        return "redirect:/usuarios";
    }

    @GetMapping("/web/clientes/crear")
    public String crearClienteForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/form";
    }

    @PostMapping("/web/clientes/guardar")
    public String guardarClienteWeb(@ModelAttribute Cliente cliente) {
        clienteService.guardarCliente(cliente);
        return "redirect:/login";
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
