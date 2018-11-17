package sv.edu.udb.www.ProyectoPOO.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.edu.udb.www.ProyectoPOO.entities.Ofertas;

@Repository("OfertasRepository")
public interface OfertasRepository extends JpaRepository<Ofertas, String>{
	
	public abstract List<Ofertas> findByTituloOferta(String titulo);
	public abstract List<Ofertas> findAllByOrderByTituloOferta();
	
	public abstract Ofertas findByIdOferta(Integer id);
}
