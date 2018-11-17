package sv.edu.udb.www.ProyectoPOO.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sv.edu.udb.www.ProyectoPOO.entities.Empresas;
import sv.edu.udb.www.ProyectoPOO.entities.Usuarios;
import sv.edu.udb.www.ProyectoPOO.repositories.EmpresasRepository;
import sv.edu.udb.www.ProyectoPOO.repositories.RubrosRepository;
import sv.edu.udb.www.ProyectoPOO.repositories.UsuariosRepository;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	@Qualifier("EmpresasRepository")
	EmpresasRepository empresasRepository;

	@Autowired
	@Qualifier("RubrosRepository")
	RubrosRepository rubrosRepository;

	@Autowired
	@Qualifier("UsuariosRepository")
	UsuariosRepository usuariosRepository;

	@GetMapping("/inicio")
	public String inicioAdmin() {

		System.out.println("Si entra");
		return "/administrador/index";

	}

	@GetMapping("/lista")
	public String listarEmpresas(Model model) {
		model.addAttribute("lista", empresasRepository.findAllByOrderByNombreEmpresa());
		return "/administrador/listarEmpresas";

	}

	@GetMapping("/nuevo")
	public String nuevaEmpresa(Model model) {
		model.addAttribute("empresas", new Empresas());
		model.addAttribute("usuarios", new Usuarios());
		model.addAttribute("listarubros", rubrosRepository.findAllByOrderByRubro());
		return "/administrador/nuevaEmpresa";

	}

	@PostMapping("/nuevo")
	public String insertarEmpresa(@Valid @ModelAttribute("empresas") Empresas empresas,
			@Valid @ModelAttribute("usuarios") Usuarios usuarios, BindingResult result, Model model,
			RedirectAttributes atributos) {
		
		System.out.println(usuarios.getCorreo());
		/*
		 * if(result.hasErrors()) { model.addAttribute("empresas", empresas);
		 * model.addAttribute("usuarios",usuarios); model.addAttribute("listarubros",
		 * rubrosRepository.findAllByOrderByRubro()); return
		 * "/administrador/nuevaEmpresa"; } else {
		 * if(empresasRepository.existsById(empresas.getCodigoEmpresa())) { return null;
		 * } else { empresasRepository.save(empresas);
		 * usuariosRepository.save(usuarios); atributos.addFlashAttribute("exito",
		 * "Editorial guardada exitosamente"); return "redirect:/editoriales/list"; }
		 */
		return "";
	}

}
