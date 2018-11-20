package sv.edu.udb.www.ProyectoPOO.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sv.edu.udb.www.ProyectoPOO.entities.Clientes;
import sv.edu.udb.www.ProyectoPOO.entities.Cupones;


@Repository("ClientesRepository")
public interface ClientesRepository extends JpaRepository<Clientes, Integer> {
	
	public abstract List<Clientes> findByNombreClientes(String nombre);
	public abstract List<Clientes> findAllByOrderByNombreClientes();
	
	@Transactional
	public abstract Clientes findByIdCliente(Integer idCliente);
	
	
	@Query("SELECT c FROM Cupones c INNER JOIN c.clientes cl  where cl.idCliente=?1")
	public abstract List<Cupones> listarCuponesPorCliente(Integer idCliente);
	
}
