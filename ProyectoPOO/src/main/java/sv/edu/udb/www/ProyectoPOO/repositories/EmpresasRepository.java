package sv.edu.udb.www.ProyectoPOO.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sv.edu.udb.www.ProyectoPOO.entities.Empresas;


@Repository("EmpresasRepository")
public interface EmpresasRepository extends JpaRepository<Empresas, String> {
	
	public abstract List<Empresas> findByNombreEmpresa(String nombre);
	public abstract List<Empresas> findAllByOrderByNombreEmpresa();
	
	public abstract Empresas findByCodigoEmpresa(String codigoEditorial);

	/*@Query("SELECT e.libroses FROM Editoriales e WHERE e.codigoEditorial=?1")
	public abstract List<Libros> listarLibrosPorEditorial(String codigo);*/
	
}
