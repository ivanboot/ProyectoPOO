package sv.edu.udb.www.ProyectoPOO.controller;

import java.util.Random;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.classmate.AnnotationConfiguration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import sv.edu.udb.www.ProyectoPOO.entities.Empresas;
import sv.edu.udb.www.ProyectoPOO.entities.Tipousuario;
import sv.edu.udb.www.ProyectoPOO.entities.Usuarios;
import sv.edu.udb.www.ProyectoPOO.repositories.EmpresasRepository;
import sv.edu.udb.www.ProyectoPOO.repositories.RubrosRepository;
import sv.edu.udb.www.ProyectoPOO.repositories.UsuariosRepository;
import sv.edu.udb.www.ProyectoPOO.utils.Correo;

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
	public String insertarEmpresa(@Valid @ModelAttribute("empresas") Empresas empresas, BindingResult result,
			Model model, RedirectAttributes atributos, @Valid @ModelAttribute("usuarios") Usuarios usuarios) {

		if (result.hasErrors()) {
			model.addAttribute("empresas", empresas);
			model.addAttribute("usuarios", usuarios);
			model.addAttribute("listarubros", rubrosRepository.findAllByOrderByRubro());
			return "/administrador/nuevaEmpresa";
		} else {

			empresas.setCodigoEmpresa("OMG" + (empresasRepository.generarCodigoEmpresa() + 1));
			System.out.println(empresas.getCodigoEmpresa());

			String cadenaAleatoria = UUID.randomUUID().toString();
			// Creacion password

			char[] caracteres;
			caracteres = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
					'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
					'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
					'u', 'v', 'w', 'x', 'y', 'z' };
			String pass = "";
			for (int i = 0; i < 8; i++) {
				pass += caracteres[new Random().nextInt(62)];
			}

			usuarios.setContrasena(pass);
			usuarios.setConfirmado(true);
			usuarios.setIdConfirmacion(cadenaAleatoria);
			usuarios.setTipousuario(new Tipousuario(2));

			if (usuariosRepository.findByCorreo(usuarios.getCorreo()) != null) {
				return "redirect:/administrador/nuevo";
			}
			usuariosRepository.save(usuarios); // Ingreso el usuario a la BDD (funciona xd)

			usuarios = usuariosRepository.findByCorreo(usuarios.getCorreo());

			String texto = "";
			String enlace = "Hola soy un enlace perron" + "?operacion=verificar&id=" + cadenaAleatoria;
			texto += "Su cuenta ha sido creada, para ingresar al sistema como Empresa debe utilizar\n" + "Correo: "
					+ usuarios.getCorreo() + "\n" + "Contra: " + usuarios.getContrasena();

			Correo correo = new Correo();
			correo.setAsunto("Confirmacion de registro");
			correo.setMensaje(texto);
			correo.setDestinatario(usuarios.getCorreo());
			correo.enviarCorreo();

			empresas.setUsuarios(new Usuarios(usuarios.getIdUsuario()));

			if (empresasRepository.existsById(empresas.getCodigoEmpresa())) {
				return null;
			} else {

				empresasRepository.save(empresas);
				atributos.addFlashAttribute("exito", "Editorial guardada exitosamente");
				return "redirect:/administrador/lista";
			}
		}
	}

	@GetMapping("/modificar/{codigo}")
	public String verEmpresa(@PathVariable("codigo") String codigo, Model model) {

		model.addAttribute("empresas", empresasRepository.findByCodigoEmpresa(codigo));
		model.addAttribute("usuarios",
				usuariosRepository.findByCorreo(usuariosRepository.usuarioPorCodigoEmpresa(codigo).getCorreo()));
		model.addAttribute("listarubros", rubrosRepository.findAllByOrderByRubro());
		return "/administrador/modificarEmpresa";

	}

	@PostMapping("/modificar")
	public String modificarEmpresa(@Valid @ModelAttribute("empresas") Empresas empresas, BindingResult result,
			Model model, RedirectAttributes atributos, @Valid @ModelAttribute("usuarios") Usuarios usuarios) {

		if (result.hasErrors()) {
			model.addAttribute("empresas", empresas);
			model.addAttribute("usuarios", usuarios);
			model.addAttribute("listarubros", rubrosRepository.findAllByOrderByRubro());
			return "/administrador/modificarEmpresa";
		} else {

			System.out.println(usuarios.getContrasena());
			System.out.println(usuarios.getIdConfirmacion());
			System.out.println(usuarios.getCorreo());
			System.out.println(usuarios.getIdUsuario());

			if (usuariosRepository.findByCorreo(usuarios.getCorreo()) != null) {
				if (usuariosRepository.findByCorreo(usuarios.getCorreo()).getCorreo() != usuarios.getCorreo()) {
					return "redirect:/administrador/modificar/" + empresas.getCodigoEmpresa();
				}
			}
			usuariosRepository.save(usuarios);

			usuarios = usuariosRepository.findByCorreo(usuarios.getCorreo());

			empresas.setUsuarios(new Usuarios(usuarios.getIdUsuario()));

			empresasRepository.save(empresas);
			atributos.addFlashAttribute("exito", "Editorial guardada exitosamente");
			return "redirect:/administrador/lista";

		}
	}

	@GetMapping("/borrar/{codigo}")
	public String eliminarEmpresa(@PathVariable("codigo") String codigo, Model model) {
		Usuarios usuarios = new Usuarios();
		Empresas empresas = new Empresas();
		try {

			usuarios = usuariosRepository.usuarioPorCodigoEmpresa(codigo);
			empresas = empresasRepository.findByCodigoEmpresa(codigo);

			empresasRepository.delete(empresas);
			usuariosRepository.delete(usuarios);
		} catch (Exception ex) {
			
		}

		return "/administrador/modificarEmpresa";

	}

}
