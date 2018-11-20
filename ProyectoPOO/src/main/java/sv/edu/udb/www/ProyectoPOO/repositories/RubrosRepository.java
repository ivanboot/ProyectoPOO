package sv.edu.udb.www.ProyectoPOO.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sv.edu.udb.www.ProyectoPOO.entities.Rubros;

@Repository("RubrosRepository")
public interface RubrosRepository extends JpaRepository <Rubros, String>{
	
	public abstract Rubros findByRubro(String nombre);
	public abstract List<Rubros> findAllByOrderByRubro();
	
	public abstract Rubros findByIdRubro(Integer id);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Rubros r WHERE r.rubro=?1")
	public abstract void borrarRubrosPorId(String id);
}
