package sv.edu.udb.www.ProyectoPOO.controller;

import java.util.Random;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
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
import sv.edu.udb.www.ProyectoPOO.entities.Estadooferta;
import sv.edu.udb.www.ProyectoPOO.entities.Ofertas;
import sv.edu.udb.www.ProyectoPOO.entities.Rubros;
import sv.edu.udb.www.ProyectoPOO.entities.Tipousuario;
import sv.edu.udb.www.ProyectoPOO.entities.Usuarios;
import sv.edu.udb.www.ProyectoPOO.repositories.ClientesRepository;
import sv.edu.udb.www.ProyectoPOO.repositories.CuponesRepository;
import sv.edu.udb.www.ProyectoPOO.repositories.EmpresasRepository;
import sv.edu.udb.www.ProyectoPOO.repositories.OfertasRepository;
import sv.edu.udb.www.ProyectoPOO.repositories.RubrosRepository;
import sv.edu.udb.www.ProyectoPOO.repositories.UsuariosRepository;
import sv.edu.udb.www.ProyectoPOO.utils.Correo;
import sv.edu.udb.www.ProyectoPOO.utils.SecurityUtils;

@Controller
@PreAuthorize("hasAuthority('Administrador')")
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

	@Autowired
	@Qualifier("ClientesRepository")
	ClientesRepository clientesRepository;
	
	@Autowired
	@Qualifier("CuponesRepository")
	CuponesRepository cuponesRepository;
	
	@Autowired
	@Qualifier("OfertasRepository")
	OfertasRepository ofertasRepository;

	@GetMapping("/inicio")
	public String inicioAdmin() {

		
		return "/administrador/index";

	}

	@GetMapping("/lista")
	public String listarEmpresas(Model model) {
		model.addAttribute("lista", empresasRepository.findAllByOrderByNombreEmpresa());
		return "/administrador/listarEmpresas";

	}
	
	@GetMapping("/lista/{codigo}")
	public String listarOfertasEmpresas(@PathVariable("codigo") String codigo, Model model) {
		
		model.addAttribute("codigo", codigo);
		model.addAttribute("oEspera", ofertasRepository.listarOfertasPorEmpresa(codigo,1));
		model.addAttribute("oAprovada", ofertasRepository.listarOfertasPorEmpresa(codigo,2));
		model.addAttribute("oActiva", ofertasRepository.listarOfertasPorEmpresa(codigo,3));
		model.addAttribute("oFinalizada", ofertasRepository.listarOfertasPorEmpresa(codigo,4));
		model.addAttribute("oRechazada", ofertasRepository.listarOfertasPorEmpresa(codigo,5));
		model.addAttribute("oDescartada", ofertasRepository.listarOfertasPorEmpresa(codigo,6));
		
		return "/administrador/listarOfertasEmpresas";
	}
	
	@GetMapping("/lista/{codigo}/{codigo2}")
	public String aprovarOferta(@PathVariable("codigo") Integer codigo,@PathVariable("codigo2") String codigo2, 
			Model model,RedirectAttributes atributos ) {
		
		Ofertas ofertas = new Ofertas();
		
		ofertas=ofertasRepository.findByIdOferta(codigo);
		
		if(ofertas!=null) {
			System.out.println("Entra al metodo");
			Estadooferta estadooferta = new Estadooferta();
			estadooferta.setIdEstadoOferta(2);
			ofertas.setEstadooferta(estadooferta);
			ofertasRepository.save(ofertas);
			atributos.addFlashAttribute("exito", "Oferta aceptada!");
		}else {
			atributos.addFlashAttribute("fracaso", "La oferta no pudo ser aceptada...");
		}
		
		return "redirect:/administrador/lista/"+codigo2;
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
			Model model, RedirectAttributes atributos, @Valid @ModelAttribute("usuarios") Usuarios usuarios)
			throws Exception {

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

			usuarios.setContrasena(SecurityUtils.encriptarSHA(pass));
			usuarios.setConfirmado(true);
			usuarios.setIdConfirmacion(cadenaAleatoria);
			usuarios.setTipousuario(new Tipousuario(2));

			if (usuariosRepository.findByCorreo(usuarios.getCorreo()) != null) {
				atributos.addFlashAttribute("fracaso", "El correo asignado se encuentra en uso...");
				return "redirect:/administrador/nuevo";
			}
			usuariosRepository.save(usuarios); // Ingreso el usuario a la BDD (funciona xd)

			usuarios = usuariosRepository.findByCorreo(usuarios.getCorreo());

			String texto = "";
			texto += "Su cuenta ha sido creada, para ingresar al sistema como Empresa debe utilizar <br/>" + "Correo: "
					+ usuarios.getCorreo() + "<br/>" + "Contra: " + usuarios.getContrasena();

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
				atributos.addFlashAttribute("exito", "Empresa ingresada exitosamente!");
				return "redirect:/administrador/lista";
			}
		}
	}

	@GetMapping("/modificar/{codigo}")
	public String verEmpresa(@PathVariable("codigo") String codigo, Model model) {

		model.addAttribute("empresas", empresasRepository.findByCodigoEmpresa(codigo));
		model.addAttribute("usuarios",usuariosRepository.findByCorreo(usuariosRepository.usuarioPorCodigoEmpresa(codigo).getCorreo()));
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
					atributos.addFlashAttribute("fracaso", "El correo asignado se encuentra en uso...");
					return "redirect:/administrador/modificar/" + empresas.getCodigoEmpresa();
				}
			}
			usuariosRepository.save(usuarios);

			usuarios = usuariosRepository.findByCorreo(usuarios.getCorreo());

			empresas.setUsuarios(new Usuarios(usuarios.getIdUsuario()));

			empresasRepository.save(empresas);
			atributos.addFlashAttribute("exito", "Empresa modificada exitosamente!");
			return "redirect:/administrador/lista";

		}
	}

	@GetMapping("/borrar/{codigo}")
	public String eliminarEmpresa(@PathVariable("codigo") String codigo, Model model, RedirectAttributes atributos) {
		Usuarios usuarios = new Usuarios();
		Empresas empresas = new Empresas();
		try {

			usuarios = usuariosRepository.usuarioPorCodigoEmpresa(codigo);
			empresas = empresasRepository.findByCodigoEmpresa(codigo);
			System.out.println(empresas.getCodigoEmpresa());

			if (empresasRepository.existsById(empresas.getCodigoEmpresa())) {
				empresasRepository.deleteById(empresas.getCodigoEmpresa());
				usuariosRepository.borrarUsuarioPorId(usuarios.getIdUsuario());
				atributos.addFlashAttribute("exito", "Empresa eliminada exitosamente!");
			} else {

			}
		} catch (Exception ex) {
			atributos.addFlashAttribute("fracaso", "No se pudo eliminar la empresa...");
		}

		return "redirect:/administrador/lista";

	}

	@GetMapping("/listarRubros")
	public String listarRubros(Model model) {
		model.addAttribute("lista", rubrosRepository.findAllByOrderByRubro());
		return "/administrador/listarRubros";

	}

	@GetMapping("/nuevoRubro")
	public String nuevoRubro(Model model) {
		model.addAttribute("rubros", new Rubros());
		return "/administrador/nuevoRubro";
	}

	@PostMapping("/nuevoRubro")
	public String insertarRubro(@Valid @ModelAttribute("rubros") Rubros rubros, BindingResult result, Model model,
			RedirectAttributes atributos) {

		if (result.hasErrors()) {
			model.addAttribute("rubros", rubros);
			atributos.addFlashAttribute("fracaso", "El rubro asignado ya existe en el sistema");
			return "/administrador/nuevoRubro";
		} else {
			if (rubrosRepository.findByRubro(rubros.getRubro()) != null) {
				atributos.addFlashAttribute("fracaso", "El rubro asignado ya existe en el sistema");
				return "/administrador/nuevoRubro";
			}
			rubrosRepository.save(rubros);
			atributos.addFlashAttribute("exito", "Rubro ingresado exitosamente!");
			return "redirect:/administrador/listarRubros";
		}
	}

	@GetMapping("/modificarRubro/{codigo}")
	public String verRubro(@PathVariable("codigo") Integer codigo, Model model) {
		model.addAttribute("rubros", rubrosRepository.findByIdRubro(codigo));
		return "/administrador/modificarRubro";
	}

	@PostMapping("/modificarRubro")
	public String modificarRubro(@Valid @ModelAttribute("rubros") Rubros rubros, BindingResult result, Model model,
			RedirectAttributes atributos) {

		if (result.hasErrors()) {
			model.addAttribute("rubros", rubros);
			return "/administrador/modificarRubro";
		} else {
			if (rubrosRepository.findByRubro(rubros.getRubro()) != null) {
				if (rubrosRepository.findByRubro(rubros.getRubro()).getRubro() != rubros.getRubro()) {
					atributos.addFlashAttribute("fracaso", "El rubro asignado ya existe en el sistema");
					return "redirect:/administrador/modificarRubro/" + rubros.getIdRubro();
				}
			}
			rubrosRepository.save(rubros);
			atributos.addFlashAttribute("exito", "Rubro modificado exitosamente!");
			return "redirect:/administrador/listarRubros";
		}
	}

	@GetMapping("/borrarRubro/{codigo}")
	public String eliminarRubro(@PathVariable("codigo") Integer codigo, Model model, RedirectAttributes atributos) {
		Rubros rubros = new Rubros();
		try {

			rubros = rubrosRepository.findByIdRubro(codigo);

			if (rubrosRepository.findByRubro(rubros.getRubro()) != null) {
				//System.out.println(rubros.getRubro());
				rubrosRepository.borrarRubrosPorId(rubros.getRubro());				
				atributos.addFlashAttribute("exito", "El rubro ha sido eliminado exitosamente!");
			} else {
				atributos.addFlashAttribute("fracaso", "El rubro no pudo ser eliminado...");
			}
		} catch (Exception ex) {
			atributos.addFlashAttribute("fracaso", "El rubro no pudo ser eliminado...");
		}

		return "redirect:/administrador/listarRubros";

	}

	@GetMapping("/listarClientes")
	public String listarClientes(Model model) {

		model.addAttribute("lista", clientesRepository.findAllByOrderByNombreClientes());

		return "/administrador/listarClientes";

	}

	@GetMapping("/listarClientes/listarCupones/{codigo}")
	public String listarCuponesClientes(@PathVariable("codigo") String codigo,Model model) {
		model.addAttribute("clientes", clientesRepository.findByIdCliente(Integer.parseInt(codigo)));
		try {
		model.addAttribute("disponibles", cuponesRepository.listarCuponesPorCliente(Integer.parseInt(codigo),1));
		model.addAttribute("canjeados", cuponesRepository.listarCuponesPorCliente(Integer.parseInt(codigo),2));
		model.addAttribute("vencidos", cuponesRepository.listarCuponesPorCliente(Integer.parseInt(codigo),3));
		}catch(Exception e) {
			System.out.println(e);
		}
		return "/administrador/listarCuponesClientes";
	}
	
	
}