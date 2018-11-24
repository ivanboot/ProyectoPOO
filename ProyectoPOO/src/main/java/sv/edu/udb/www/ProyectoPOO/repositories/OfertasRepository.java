package sv.edu.udb.www.ProyectoPOO.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sv.edu.udb.www.ProyectoPOO.entities.Ofertas;

@Repository("OfertasRepository")
public interface OfertasRepository extends JpaRepository<Ofertas, String>{
	
	public abstract List<Ofertas> findByTituloOferta(String titulo);
	public abstract List<Ofertas> findAllByOrderByTituloOferta();
	
	@Query("SELECT o FROM Ofertas o INNER JOIN o.empresas e INNER JOIN o.estadooferta es WHERE e.codigoEmpresa=?1")
	public abstract List<Ofertas> listarOfertasPorEmp(String codigoEmpresa);		
			
	@Query("SELECT o FROM Ofertas o INNER JOIN o.empresas e INNER JOIN o.estadooferta es WHERE e.codigoEmpresa=?1 AND es.idEstadoOferta=?2")
	public abstract List<Ofertas> listarOfertasPorEmpresa(String codigoEmpresa,Integer idEstadoOferta);
	
	@Query("SELECT o FROM Ofertas o INNER JOIN o.empresas e INNER JOIN e.usuarios u INNER JOIN o.estadooferta es WHERE e.codigoEmpresa=?1 AND es.idEstadoOferta=?2 AND u.idUsuario=?3")
	public abstract List<Ofertas> listarOfertasPorEmpresa(String codigoEmpresa,Integer idEstadoOferta,Integer idUsuario);
	
	@Query("SELECT o FROM Ofertas o INNER JOIN o.estadooferta es WHERE es.idEstadoOferta=3 ORDER BY o.idOferta DESC")
	public abstract List<Ofertas> listarOfertasActivas();
	
	@Query("UPDATE Ofertas o SET o.estadooferta.idEstadoOferta=2 WHERE o.idOferta=?1")
	public abstract Integer aprovarOferta(Integer idOferta);
	
	public abstract Ofertas findByIdOferta(Integer id);
}
