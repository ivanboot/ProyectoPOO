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
	@Query("SELECT c FROM Cupones c INNER JOIN c.clientes cl  WHERE cl.idCliente=?1")
	public abstract List<Cupones> listarCuponesPorCliente(Integer id);
	
	public abstract Cupones findByCodigoCupo(String codigo);
	
	//Esto he agregado
	@Query("SELECT o.tituloOferta AS Titulo_oferta, c.codigoCupo AS Codigo, c.fechaCompra AS Fecha_compra, o.descripcionOferta AS Descripción, o.otrosDetalles AS Detalles,"
				+ "cl.nombreClientes AS Nombres_cliente, cl.apellidosClientes AS Apellidos_clientes, es.estado AS Estado FROM Cupones c INNER JOIN Ofertas o "
				+ "ON c.ofertas.idOferta = o.idOferta "
			+ "INNER JOIN Clientes cl ON "
			+ "c.clientes.idCliente = cl.idCliente "
			+ "INNER JOIN Estadocupon es ON "
			+ "c.estadocupon.idEstadoCupon = es.idEstadoCupon WHERE c.codigoCupo=?1")
		public abstract Optional<Cupones> listarCuponesConDetalles(String codigo);
}
