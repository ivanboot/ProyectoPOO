package sv.edu.udb.www.ProyectoPOO.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import sv.edu.udb.www.ProyectoPOO.entities.Cupones;
import sv.edu.udb.www.ProyectoPOO.repositories.CuponesRepository;

@RestController
@RequestMapping("/api/cupones")

public class CuponesRest {
	
	@Autowired
	@Qualifier("CuponesRepository")
	CuponesRepository cuponesRepository;
	
	@GetMapping()
	public List<Cupones> listarCupones(){
		return cuponesRepository.findAll();
	}
	

}
