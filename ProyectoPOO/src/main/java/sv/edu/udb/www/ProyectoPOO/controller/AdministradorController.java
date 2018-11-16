package sv.edu.udb.www.ProyectoPOO.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@GetMapping("/inicio")
	public String inicioAdmin() {
		
		System.out.println("Si entra");
		return "/administrador/index";
	
	}
	
}
