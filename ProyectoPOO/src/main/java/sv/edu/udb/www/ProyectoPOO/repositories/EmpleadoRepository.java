package sv.edu.udb.www.ProyectoPOO.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sv.edu.udb.www.ProyectoPOO.entities.Empleado;
import sv.edu.udb.www.ProyectoPOO.entities.Empresas;
import sv.edu.udb.www.ProyectoPOO.entities.Ofertas;

@Repository("EmpleadoRepository")
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
	
	public abstract List<Empleado> findByNombreEmpleado(String nombre);
	public abstract List<Empleado> findAllByOrderByNombreEmpleado();
	
	@Query("SELECT e FROM Empleado e INNER JOIN e.empresas es WHERE es.codigoEmpresa = ?1")
	public abstract List<Empleado> listarEmpleados(String idEmpresa);
	
	public abstract Empleado findByIdEmpleado(Integer id);
	
	
}
