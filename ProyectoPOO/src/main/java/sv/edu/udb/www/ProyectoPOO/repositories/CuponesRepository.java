package sv.edu.udb.www.ProyectoPOO.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sv.edu.udb.www.ProyectoPOO.entities.Cupones;

@Repository("CuponesRepository")
public interface CuponesRepository extends JpaRepository<Cupones, String>{
	
	public abstract List<Cupones> findAllByOrderByCodigoCupo();
		
	public abstract List<Cupones> findAllByClientesIdCliente(Integer id);
	
	@Transactional
	@Query("SELECT c FROM Cupones c INNER JOIN c.clientes cl  WHERE cl.idCliente=?1 AND c.estadocupon.idEstadoCupon=?2")
	public abstract List<Cupones> listarCuponesPorCliente(Integer idCliente,Integer idEstadoCupon);
	
	public abstract Cupones findByCodigoCupo(String codigo);
	
	@Query("SELECT c FROM Cupones c INNER JOIN c.estadocupon es WHERE es.idEstadoCupon=1 AND c.codigoCupo=?1")
	public abstract Cupones mostrarEtadoCupon(String codigo);
	
	@Query("SELECT c FROM Cupones c INNER JOIN c.estadocupon es INNER JOIN c.clientes d WHERE es.idEstadoCupon=4 AND d.idCliente=?1")
	public abstract List<Cupones> cuponesEtadoCupon(Integer idCliente);
	
	@Query("SELECT c FROM Cupones c INNER JOIN c.estadocupon es INNER JOIN c.clientes d WHERE es.idEstadoCupon=1 AND d.idCliente=?1")
	public abstract List<Cupones> cuponesDisponibles(Integer idCliente);
	
	@Query("SELECT c FROM Cupones c INNER JOIN c.estadocupon es INNER JOIN c.clientes d WHERE es.idEstadoCupon=2 AND d.idCliente=?1")
	public abstract List<Cupones> cuponesCanjeados(Integer idCliente);
	
	@Query("SELECT c FROM Cupones c INNER JOIN c.estadocupon es INNER JOIN c.clientes d WHERE es.idEstadoCupon=3 AND d.idCliente=?1")
	public abstract List<Cupones> cuponesVencidos(Integer idCliente);
		
}
