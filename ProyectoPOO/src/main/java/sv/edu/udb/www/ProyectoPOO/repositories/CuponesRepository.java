package sv.edu.udb.www.ProyectoPOO.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.edu.udb.www.ProyectoPOO.entities.Cupones;

@Repository("CuponesRepository")
public interface CuponesRepository extends JpaRepository<Cupones, String>{
		
	public abstract List<Cupones> findAllByOrderByCodigoCupo();
	
	public abstract Cupones findByCodigoCupo(String codigo);
}
