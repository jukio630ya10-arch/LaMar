package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@GetMapping("/")
	public String landing() {
	    return "index";
	}

	@GetMapping("/login")
	public String login() {
	    return "login"; // templates/login.html
	}

	@GetMapping("/home")
	public String home() {
	    return "index"; // redirige al index después del login
	}

	@GetMapping("/menu")
	public String menu() {
	    return "almacen-inicio";
	}
    
}
