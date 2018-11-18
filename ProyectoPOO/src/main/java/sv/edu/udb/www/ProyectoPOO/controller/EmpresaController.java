package sv.edu.udb.www.ProyectoPOO.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasAuthority('Empresa')")
@RequestMapping("/empresa")
public class EmpresaController {

}
