package sv.edu.udb.www.ProyectoPOO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sv.edu.udb.www.ProyectoPOO.repositories.CuponesRepository;
import sv.edu.udb.www.ProyectoPOO.repositories.OfertasRepository;

@Controller
@PreAuthorize("hasAuthority('Empresa')")
@RequestMapping("/empresa")
public class EmpresaController {

	@Autowired
	@Qualifier("OfertasRepository")
	OfertasRepository ofertasRepository;
	
	
	@GetMapping("/inicio")
	public String inicioEmpresa() {

		return "/empresa/index";

	}
	
	@GetMapping("/listar Ofertas")
	public String listarOfertas(Model model) {

		model.addAttribute("lista", ofertasRepository.findAllByOrderByTituloOferta());

		return "/empresa/listarOfertas";

	}
}
