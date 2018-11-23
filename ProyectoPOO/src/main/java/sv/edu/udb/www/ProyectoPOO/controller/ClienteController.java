package sv.edu.udb.www.ProyectoPOO.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sv.edu.udb.www.ProyectoPOO.entities.Ofertas;
import sv.edu.udb.www.ProyectoPOO.repositories.OfertasRepository;

@Controller
@PreAuthorize("hasAuthority('Cliente')")
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	@Qualifier("OfertasRepository")
	OfertasRepository ofertasRepository;

	@GetMapping("/inicio")
	public String inicioCliente(Model model) {

		List<Ofertas> ofertas = ofertasRepository.listarOfertasActivas();

		List<Ofertas> ultimasOfertas = new ArrayList<>();

		if (ofertas.size() > 5) {
			for (int i = 0; i < 5; i++) {
				ultimasOfertas.add(ofertas.get(i));
			}
		} else {
			for (int i = 0; i < ofertas.size(); i++) {
				ultimasOfertas.add(ofertas.get(i));
			}
		}

		model.addAttribute("oActiva", ultimasOfertas);

		return "/cliente/index";

	}
	
	
	@GetMapping("/ofertas")
	public String ofertasCliente(Model model) {

		List<Ofertas> ofertas = ofertasRepository.listarOfertasActivas();

		model.addAttribute("oActiva", ofertas);

		return "/cliente/Ofertas";

	}
	
	
	
	

}
