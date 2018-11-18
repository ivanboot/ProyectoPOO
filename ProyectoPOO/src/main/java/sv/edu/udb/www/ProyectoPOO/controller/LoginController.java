package sv.edu.udb.www.ProyectoPOO.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String showFormLogin() {

		String tipoUsuario = "";

		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		for (Iterator iterator = authorities.iterator(); iterator.hasNext();) {
			SimpleGrantedAuthority simpleGrantedAuthority = (SimpleGrantedAuthority) iterator.next();
			tipoUsuario = simpleGrantedAuthority.toString();
		}

		if (tipoUsuario.equals("Administrador")) {
			return "/administrador/index";
		}else if(tipoUsuario.equals("Cliente")) {
			return "/cliente/index";
		}else if(tipoUsuario.equals("Empresa")) {
			return "/empresa/index";
		}else if(tipoUsuario.equals("Empleado")) {
			return "/empleado/index";
		}

		return "/public/login";
	}

}
