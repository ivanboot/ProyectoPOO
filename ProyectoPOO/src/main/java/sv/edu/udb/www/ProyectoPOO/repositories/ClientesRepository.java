package sv.edu.udb.www.ProyectoPOO.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import sv.edu.udb.www.ProyectoPOO.entities.Clientes;


@Repository("ClientesRepository")
public interface ClientesRepository extends JpaRepository<Clientes, String> {
	
	public abstract List<Clientes> findByNombreClientes(String nombre);
	public abstract List<Clientes> findAllByOrderByNombreClientes();
	
	@Transactional
	public abstract Clientes findByIdCliente(Integer idCliente);
	
}
