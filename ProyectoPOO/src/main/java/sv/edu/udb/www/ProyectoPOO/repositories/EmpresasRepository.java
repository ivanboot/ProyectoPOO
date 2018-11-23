package sv.edu.udb.www.ProyectoPOO.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sv.edu.udb.www.ProyectoPOO.entities.Cupones;
import sv.edu.udb.www.ProyectoPOO.entities.Empresas;


@Repository("EmpresasRepository")
public interface EmpresasRepository extends JpaRepository<Empresas, String> {
	
	public abstract List<Empresas> findByNombreEmpresa(String nombre);
	public abstract List<Empresas> findAllByOrderByNombreEmpresa();
	
	@Query("SELECT SUBSTRING(MAX(e.codigoEmpresa) , 4,6) AS Numero FROM Empresas e")
	public abstract Integer generarCodigoEmpresa();
	
	@Query("SELECT e FROM Empresas e INNER JOIN e.usuarios u where u.idUsuario=?1")
	public abstract Empresas obtenerEmpresaPorUsuario(Integer idUsuario);
	
	public abstract Empresas findByCodigoEmpresa(String codigoEditorial);

	/*@Query("SELECT e.libroses FROM Editoriales e WHERE e.codigoEditorial=?1")
	public abstract List<Libros> listarLibrosPorEditorial(String codigo);*/
	
	
	
}
