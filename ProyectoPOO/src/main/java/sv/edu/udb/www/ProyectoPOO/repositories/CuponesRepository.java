package sv.edu.udb.www.ProyectoPOO.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import sv.edu.udb.www.ProyectoPOO.entities.Cupones;

@Repository("CuponesRepository")
public interface CuponesRepository extends JpaRepository<Cupones, String>{
		
	public abstract List<Cupones> findAllByOrderByCodigoCupo();
	
	@Transactional
	public abstract List<Cupones> findAllByClientesIdCliente(Integer id);
	
	public abstract Cupones findByCodigoCupo(String codigo);
}
