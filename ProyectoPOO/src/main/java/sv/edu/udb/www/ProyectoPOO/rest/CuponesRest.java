package sv.edu.udb.www.ProyectoPOO.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

import sv.edu.udb.www.ProyectoPOO.entities.Cupones;
import sv.edu.udb.www.ProyectoPOO.repositories.CuponesRepository;

@RestController
@RequestMapping("/api/cupones")

public class CuponesRest {
	
	@Autowired
	@Qualifier("CuponesRepository")
	CuponesRepository cuponesRepository;
	
	@GetMapping("/lista")
	public List<Cupones> listarCupones(){
		return cuponesRepository.findAllByOrderByCodigoCupo();
	}
	//Esto he agregado
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Cupones>> obtenerCupones(@PathVariable("codigo") String codigo){
		try {
			Optional<Cupones> cupon = (Optional<Cupones>) cuponesRepository.listarCuponesConDetalles(codigo);
			if(cupon.isPresent()) {
				return new ResponseEntity<Optional<Cupones>>(cupon, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	

}
