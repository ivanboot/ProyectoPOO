package sv.edu.udb.www.ProyectoPOO.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasAuthority('Cliente')")
@RequestMapping("/cliente")
public class ClienteController {
	
	@GetMapping("/inicio")
	public String inicioCliente() {
		
		return "/cliente/index";

	}

}
