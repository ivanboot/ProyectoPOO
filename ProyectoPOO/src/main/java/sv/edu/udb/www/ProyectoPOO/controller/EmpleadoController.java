package sv.edu.udb.www.ProyectoPOO.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sv.edu.udb.www.ProyectoPOO.entities.Cupones;
import sv.edu.udb.www.ProyectoPOO.entities.Empleado;
import sv.edu.udb.www.ProyectoPOO.entities.Estadocupon;
import sv.edu.udb.www.ProyectoPOO.repositories.CuponesRepository;
import sv.edu.udb.www.ProyectoPOO.repositories.EmpleadoRepository;

@Controller
@PreAuthorize("hasAuthority('Empleado')")
@RequestMapping("/empleado")
public class EmpleadoController {
	//Model-> mandar datos una pagina
	
	@Autowired
	@Qualifier("EmpleadoRepository")
	EmpleadoRepository empleadoRepository;
	
	@Autowired
	@Qualifier("CuponesRepository")
	CuponesRepository cuponesRepository;
	
	@GetMapping("/canjear")
	public String canjearCupon(Model model) {
		model.addAttribute("cupones", new Cupones());
		return "/empleado/canjearCupon";
	}
	
	@GetMapping("/mostrarCupon")
	public String mostrarCupon(Model model) {
		model.addAttribute("cupones", new Cupones());
		return "/empleado/mostrarCupones";
	}
	
	
	@PostMapping("/obtenerCupon")
	public String obtenerCupon(@Valid @ModelAttribute("cupones") Cupones cupones, BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			model.addAttribute("cupones", cupones);
			return "/empleado/canjearCupon";
		}else {
			if(cuponesRepository.existsById(cupones.getCodigoCupo())) {
				
				model.addAttribute("cupones", cuponesRepository.findByCodigoCupo(cupones.getCodigoCupo()));
				return "/empleado/mostrarCupones";
			}else {
				return "redirect:/empleado/canjear";
			}
		}
	}
	
	@PostMapping("/modificarEstado")
	public String modificarEstadoCupon(@Valid @ModelAttribute("cupones") Cupones cupones, BindingResult result,
			Model model, RedirectAttributes atributos) {
		
		if(result.hasErrors()) {
			model.addAttribute("cupones", cupones);
			return "/empleado/mostrarCupones";
		}else {
			if(cuponesRepository.mostrarEtadoCupon(cupones.getCodigoCupo()) != null) {
				cupones.setEstadocupon(new Estadocupon(2));
				cuponesRepository.save(cupones);
				atributos.addFlashAttribute("exito", "Cupón canjeado");
				return "/empleado/mostrarCupones" + cupones.getCodigoCupo();
			}
			atributos.addFlashAttribute("fracaso", "El cupón no esta disponible o caducado");
			return "/empleado/mostrarCupones";
		}
	}

}
