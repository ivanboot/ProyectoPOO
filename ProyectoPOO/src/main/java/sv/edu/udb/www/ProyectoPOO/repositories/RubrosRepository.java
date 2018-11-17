package sv.edu.udb.www.ProyectoPOO.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.edu.udb.www.ProyectoPOO.entities.Rubros;

@Repository("RubrosRepository")
public interface RubrosRepository extends JpaRepository <Rubros, String>{
	
	public abstract List<Rubros> findByRubro(String nombre);
	public abstract List<Rubros> findAllByOrderByRubro();
	
	public abstract Rubros findByIdRubro(int id);
	
}
