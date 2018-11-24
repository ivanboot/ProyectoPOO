package sv.edu.udb.www.ProyectoPOO.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sv.edu.udb.www.ProyectoPOO.entities.Empresas;
import sv.edu.udb.www.ProyectoPOO.entities.Estadooferta;
import sv.edu.udb.www.ProyectoPOO.entities.Ofertas;
import sv.edu.udb.www.ProyectoPOO.entities.Usuarios;
import sv.edu.udb.www.ProyectoPOO.repositories.CuponesRepository;
import sv.edu.udb.www.ProyectoPOO.repositories.EmpresasRepository;
import sv.edu.udb.www.ProyectoPOO.repositories.OfertasRepository;
import sv.edu.udb.www.ProyectoPOO.repositories.UsuariosRepository;

@Controller
@PreAuthorize("hasAuthority('Empresa')")
@RequestMapping("/empresa")
public class EmpresaController {

	@Autowired
	@Qualifier("OfertasRepository")
	OfertasRepository ofertasRepository;
	
	@Autowired
	@Qualifier("UsuariosRepository")
	UsuariosRepository usuariosRepository;
	
	@Autowired
	@Qualifier("EmpresasRepository")
	EmpresasRepository empresasRepository;
	
	@GetMapping("/inicio")
	public String inicioEmpresa() {

		return "/empresa/index";

	}
	
	@GetMapping("/listarOfertas")
	public String listarOfertas(Model model) {

		model.addAttribute("lista", ofertasRepository.findAllByOrderByTituloOferta());

		return "/empresa/listarOfertas";

	}
	
	
	@GetMapping("/nuevo")
	public String nuevaEmpresa(Model model) {
		
		model.addAttribute("ofertas", new Ofertas());
		model.addAttribute("usuarios", new Usuarios());
		
		
		return "/empresa/nuevaOferta";

	}
	
	
	@PostMapping("/nuevo")
	public String insertarOferta(@Valid @ModelAttribute("ofertas") Ofertas ofertas, BindingResult result,
			Model model, RedirectAttributes atributos, @Valid @ModelAttribute("usuarios") Usuarios usuarios) throws ParseException {
		
		if(result.hasErrors()) {
			model.addAttribute("ofertas", ofertas);
			model.addAttribute("usuarios", usuarios);
			return "/empresa/nuevaOferta";
		} else {
			
			Date fecha1 = new SimpleDateFormat("yyyy-MM-dd").parse(ofertas.getFechaInicio().toString());
			Date fecha2 = new SimpleDateFormat("yyyy-MM-dd").parse(ofertas.getFechaFin().toString());
			Date fecha3 = new SimpleDateFormat("yyyy-MM-dd").parse(ofertas.getFechaLimite().toString());
			
			ofertas.setFechaInicio(fecha1);
			ofertas.setFechaFin(fecha2);
			ofertas.setFechaLimite(fecha3);
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    UserDetails userDetail = (UserDetails) auth.getPrincipal();
		    
		    Usuarios usuario = new Usuarios();
		    
		    usuario = usuariosRepository.findByCorreo(userDetail.getUsername());
		    
		    //En el repositorio de empresas crea un método para obtener la empresa según el id del usuario
		    
			Empresas empresa = new Empresas();
			
			empresa = empresasRepository.obtenerEmpresaPorUsuario(usuario.getIdUsuario());
			
			ofertas.setEmpresas(empresa);
			ofertas.setEstadooferta(new Estadooferta("En espera"));
			
			ofertasRepository.save(ofertas);
			
		}
		
		return "/empresa/listarOfertas";
		
	}
	
	

	
}
