package sv.edu.udb.www.ProyectoPOO.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sv.edu.udb.www.ProyectoPOO.entities.Clientes;
import sv.edu.udb.www.ProyectoPOO.entities.Cupones;
import sv.edu.udb.www.ProyectoPOO.entities.Empresas;
import sv.edu.udb.www.ProyectoPOO.entities.Estadocupon;
import sv.edu.udb.www.ProyectoPOO.entities.Ofertas;
import sv.edu.udb.www.ProyectoPOO.entities.Usuarios;
import sv.edu.udb.www.ProyectoPOO.repositories.ClientesRepository;
import sv.edu.udb.www.ProyectoPOO.repositories.CuponesRepository;
import sv.edu.udb.www.ProyectoPOO.repositories.EmpresasRepository;
import sv.edu.udb.www.ProyectoPOO.repositories.OfertasRepository;
import sv.edu.udb.www.ProyectoPOO.repositories.UsuariosRepository;

@Controller
@PreAuthorize("hasAuthority('Cliente')")
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	@Qualifier("OfertasRepository")
	OfertasRepository ofertasRepository;

	@Autowired
	@Qualifier("UsuariosRepository")
	UsuariosRepository usuariosRepository;

	@Autowired
	@Qualifier("CuponesRepository")
	CuponesRepository cuponesRepository;

	@Autowired
	@Qualifier("ClientesRepository")
	ClientesRepository clientesRepository;

	@Autowired
	@Qualifier("EmpresasRepository")
	EmpresasRepository empresasRepository;

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

	@PostMapping("/agregarCupon")
	public String agregarCupon(@RequestParam("idOferta") String idOferta, @RequestParam("cantidad") String cantidad,
			Model model, RedirectAttributes atributos) {

		Ofertas oferta1 = ofertasRepository.findByIdOferta(Integer.parseInt(idOferta));

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();

		Usuarios usuario = new Usuarios();

		usuario = usuariosRepository.findByCorreo(userDetail.getUsername());

		Empresas empresa = new Empresas();
		empresa = empresasRepository.empresaPorIdOferta(Integer.parseInt(idOferta));

		Date fechaActual = new Date();

		Cupones cupon = new Cupones();
		Ofertas oferta = new Ofertas();
		Clientes cliente = new Clientes();

		cliente = clientesRepository.clientePorIdUsuario(usuario.getIdUsuario());

		oferta.setIdOferta(Integer.parseInt(idOferta));

		cupon.setFechaCompra(fechaActual);
		cupon.setOfertas(oferta);
		cupon.setClientes(cliente);
		cupon.setEstadocupon(new Estadocupon(1));

		if (oferta1.getCantidadLimite() != null) {
			if (oferta1.getCantidadLimite() > Integer.parseInt(cantidad)) {

				for (int a = 0; a < Integer.parseInt(cantidad); a++) {
					char[] caracteres;
					caracteres = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
					String numeros = "";
					for (int i = 0; i < 7; i++) {
						numeros += caracteres[new Random().nextInt(10)];
					}
					String codigo = empresa.getCodigoEmpresa() + numeros;
					cupon.setCodigoCupo(codigo);

					cuponesRepository.save(cupon);

				}

				oferta1.setCantidadLimite(oferta1.getCantidadLimite() - Integer.parseInt(cantidad));

				ofertasRepository.save(oferta1);

			} else {
				atributos.addFlashAttribute("fracaso", "No hay suficientes productos");
				return "redirect:/cliente/ofertas";
			}
		} else {
			for (int a = 0; a < Integer.parseInt(cantidad); a++) {
				char[] caracteres;
				caracteres = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
				String numeros = "";
				for (int i = 0; i < 7; i++) {
					numeros += caracteres[new Random().nextInt(10)];
				}
				String codigo = empresa.getCodigoEmpresa() + numeros;
				cupon.setCodigoCupo(codigo);

				cuponesRepository.save(cupon);

			}
		}

		atributos.addFlashAttribute("exito", "Compra realizada exitosamente ");

		return "redirect:/cliente/ofertas";

	}

	@GetMapping("/carrito")
	public String carrito(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();

		Usuarios usuario = new Usuarios();

		usuario = usuariosRepository.findByCorreo(userDetail.getUsername());

		Clientes cliente = new Clientes();

		cliente = clientesRepository.clientePorIdUsuario(usuario.getIdUsuario());

		List<Cupones> cup = new ArrayList<>();
		int a = 0;

		for (Cupones cupon : cuponesRepository.cuponesEtadoCupon(cliente.getIdCliente())) {
			a = 0;
			if (cup.size() > 0) {
				for (int i = 0; i < cup.size(); i++) {
					if (cup.get(i).getOfertas().getIdOferta().equals(cupon.getOfertas().getIdOferta())) {

						cup.get(i).getOfertas().setPrecioOferta(cup.get(i).getOfertas().getPrecioOferta()
								.add(cup.get(i).getOfertas().getPrecioOferta()));
					} else {
						
						if (a == cup.size()) {
							cup.add(cupon);
						}
						a++;
					}

				}
			} else {
				cup.add(cupon);
			}
		}

		model.addAttribute("lista", cup);
		return "/cliente/Carrito";

	}
	
	@GetMapping("/cupones")
	public String listarCupones(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();

		Usuarios usuario = new Usuarios();

		usuario = usuariosRepository.findByCorreo(userDetail.getUsername());

		Clientes cliente = new Clientes();

		cliente = clientesRepository.clientePorIdUsuario(usuario.getIdUsuario());
		
		model.addAttribute("disponibles", cuponesRepository.cuponesDisponibles(cliente.getIdCliente()));
		
		model.addAttribute("canjeados", cuponesRepository.cuponesCanjeados(cliente.getIdCliente()));
		
		model.addAttribute("vencidos", cuponesRepository.cuponesVencidos(cliente.getIdCliente()));

		return "/cliente/cupones";

	}

}
