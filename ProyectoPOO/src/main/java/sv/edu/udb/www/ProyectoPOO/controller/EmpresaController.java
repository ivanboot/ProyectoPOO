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
import sv.edu.udb.www.ProyectoPOO.repositories.EmpleadoRepository;
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
	@Qualifier("EmpleadoRepository")
	EmpleadoRepository empleadoRepository;
	
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
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    
	    Usuarios usuario = new Usuarios();
	    
	    usuario = usuariosRepository.findByCorreo(userDetail.getUsername());
	    
	    
		Empresas empresa = new Empresas();
		
		empresa = empresasRepository.obtenerEmpresaPorUsuario(usuario.getIdUsuario());
		
		String codigoEmpresa = empresa.getCodigoEmpresa();
		Integer idUsuario = usuario.getIdUsuario();
		

		model.addAttribute("oEspera", ofertasRepository.listarOfertasPorEmpresa(codigoEmpresa,1,idUsuario) );
		model.addAttribute("oAprovada", ofertasRepository.listarOfertasPorEmpresa(codigoEmpresa,2,idUsuario) );
		model.addAttribute("oActiva", ofertasRepository.listarOfertasPorEmpresa(codigoEmpresa,3,idUsuario) );
		model.addAttribute("oFinalizada", ofertasRepository.listarOfertasPorEmpresa(codigoEmpresa,4,idUsuario) );
		model.addAttribute("oRechazada", ofertasRepository.listarOfertasPorEmpresa(codigoEmpresa,5,idUsuario) );
		model.addAttribute("oDescartada", ofertasRepository.listarOfertasPorEmpresa(codigoEmpresa,6,idUsuario) );
		

		return "/empresa/listarOfertas";

	}
	
	
	@GetMapping("/listarEmpleados")
	public String listarEmpleados(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    
	    Usuarios usuario = new Usuarios();
	    
	    usuario = usuariosRepository.findByCorreo(userDetail.getUsername());
	    
	    
		Empresas empresa = new Empresas();
		
		empresa = empresasRepository.obtenerEmpresaPorUsuario(usuario.getIdUsuario());
		

		model.addAttribute("lista", empleadoRepository.listarEmpleados(empresa.getCodigoEmpresa()));

		return "/empresa/listarEmpleados";

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
			
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    UserDetails userDetail = (UserDetails) auth.getPrincipal();
		    
		    Usuarios usuario = new Usuarios();
		    
		    usuario = usuariosRepository.findByCorreo(userDetail.getUsername());
		    
		    //En el  repositorio de empresas crea un método para obtener la empresa según el id del usuario
		    
			Empresas empresa = new Empresas();
			
			empresa = empresasRepository.obtenerEmpresaPorUsuario(usuario.getIdUsuario());
			
			ofertas.setEmpresas(empresa);
			ofertas.setEstadooferta(new Estadooferta(1));
			
			System.out.println(ofertas.getEmpresas().getCodigoEmpresa());
			System.out.println(ofertas.getEstadooferta().getIdEstadoOferta());
			
			ofertasRepository.save(ofertas);
			
		}
		
		return "redirect:/empresa/listarOfertas";
		
	}
	
	

	
}
