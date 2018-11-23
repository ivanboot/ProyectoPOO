package sv.edu.udb.www.ProyectoPOO.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sv.edu.udb.www.ProyectoPOO.entities.Clientes;
import sv.edu.udb.www.ProyectoPOO.entities.Empresas;
import sv.edu.udb.www.ProyectoPOO.entities.Tipousuario;
import sv.edu.udb.www.ProyectoPOO.entities.Usuarios;
import sv.edu.udb.www.ProyectoPOO.repositories.ClientesRepository;
import sv.edu.udb.www.ProyectoPOO.repositories.EmpresasRepository;
import sv.edu.udb.www.ProyectoPOO.repositories.UsuariosRepository;
import sv.edu.udb.www.ProyectoPOO.utils.Correo;
import sv.edu.udb.www.ProyectoPOO.utils.SecurityUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class LoginController {

	@Autowired
	@Qualifier("UsuariosRepository")
	UsuariosRepository usuariosRepository;

	@Autowired
	@Qualifier("ClientesRepository")
	ClientesRepository clientesRepository;
	
	@Autowired
	@Qualifier("EmpresasRepository")
	EmpresasRepository empresasRepository;

	@GetMapping("/login")
	public String showFormLogin(Model model) {

		String tipoUsuario = "";

		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		for (Iterator iterator = authorities.iterator(); iterator.hasNext();) {
			SimpleGrantedAuthority simpleGrantedAuthority = (SimpleGrantedAuthority) iterator.next();
			tipoUsuario = simpleGrantedAuthority.toString();
		}
		
		
				

		if (tipoUsuario.equals("Administrador")) {
			return "/administrador/index";
		} else if (tipoUsuario.equals("Cliente")) {								
			return "redirect:/cliente/inicio";
		} else if (tipoUsuario.equals("Empresa")) {
			
			
			//Obtener usuario logueado y empresa según el usuario logueado
			
			Authentication auth = SecurityContextHolder
		            .getContext()
		            .getAuthentication();
		    UserDetails userDetail = (UserDetails) auth.getPrincipal();
		    
		    Usuarios usuario = new Usuarios();
		    
		    usuario = usuariosRepository.findByCorreo(userDetail.getUsername());
		    
		    //En el repositorio de empresas crea un método para obtener la empresa según el id del usuario
		    
	    	Empresas empresa = new Empresas();
	    	
	    	empresa = empresasRepository.obtenerEmpresaPorUsuario(usuario.getIdUsuario());
	    	
	    	System.out.println(empresa.getCodigoEmpresa());
		    
		    //fin obtener usuario logueado
	    	
			
			return "/empresa/index";
		} else if (tipoUsuario.equals("Empleado")) {
			return "/empleado/index";
		}

		model.addAttribute("clientes", new Clientes());
		model.addAttribute("usuarios", new Usuarios());

		return "/public/login";
	}

	@PostMapping("/public/nuevoCliente")
	public String insertarCliente(@Valid @ModelAttribute("clientes") Clientes clientes, BindingResult result,
			Model model, RedirectAttributes atributos, @Valid @ModelAttribute("usuarios") Usuarios usuarios)
			throws Exception {

		if (result.hasErrors()) {
			model.addAttribute("clientes", clientes);
			model.addAttribute("usuarios", usuarios);
			return "/public/login";
		} else {

			String cadenaAleatoria = UUID.randomUUID().toString();

			usuarios.setContrasena(SecurityUtils.encriptarSHA(usuarios.getContrasena()));
			usuarios.setConfirmado(false);
			usuarios.setIdConfirmacion(cadenaAleatoria);
			usuarios.setTipousuario(new Tipousuario(4));

			if (usuariosRepository.findByCorreo(usuarios.getCorreo()) != null) {
				atributos.addFlashAttribute("fracaso", "El correo que ingreso ya esta registrado");
				return "redirect:/login";
			}
			usuariosRepository.save(usuarios);

			usuarios = usuariosRepository.findByCorreo(usuarios.getCorreo());

			String texto = "";
			String enlace = "http://localhost:8080/public/verificarCuenta/" + usuarios.getIdUsuario() + "/"
					+ cadenaAleatoria;
			texto += "Su cuenta ha sido creada, para ingresar al sitio debe verificar su cuenta <br/>Pulse <a href='"
					+ enlace + "'>aqui</a> para verificar su cuenta";

			Correo correo = new Correo();
			correo.setAsunto("Confirmacion de registro");
			correo.setMensaje(texto);
			correo.setDestinatario(usuarios.getCorreo());
			correo.enviarCorreo();

			clientes.setUsuarios(new Usuarios(usuarios.getIdUsuario()));
			clientesRepository.save(clientes);
			atributos.addFlashAttribute("exito",
					"Se ha registrado exitosamente, verifique su correo para validar la cuenta");
			System.out.println("Excelente");
			return "redirect:/login";

		}
	}

	@GetMapping("/public/verificarCuenta/{idUsuario}/{cadenaAleatoria}")
	public String verificarCuenta(@PathVariable("idUsuario") String idUsuario,
			@PathVariable("cadenaAleatoria") String cadenaAleatoria, Model model, RedirectAttributes atributos) {

		try {

			int id = Integer.parseInt(idUsuario);

			if (usuariosRepository.findByIdUsuario(id) != null) {

				Usuarios usuario = new Usuarios();

				usuario = usuariosRepository.findByIdUsuario(id);

				if (usuario.getIdConfirmacion().equals(cadenaAleatoria)) {

					usuario.setConfirmado(true);

					usuariosRepository.save(usuario);

					atributos.addFlashAttribute("exito", "Su cuenta ha sido verificada con exito");

					return "redirect:/login";
				}
			}

			return "redirect:/public/inicio";

		} catch (Exception e) {
			return "redirect:/public/inicio";
		}

	}

}
