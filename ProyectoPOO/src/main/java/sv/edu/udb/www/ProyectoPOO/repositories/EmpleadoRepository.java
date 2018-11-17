package sv.edu.udb.www.ProyectoPOO.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sv.edu.udb.www.ProyectoPOO.entities.Empleado;

@Repository("EmpleadoRepository")
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
	
	public abstract List<Empleado> findByNombreEmpleado(String nombre);
	public abstract List<Empleado> findAllByOrderByNombreEmpleado();
	
	public abstract Empleado findByIdEmpleado(Integer id);
	
	
}
