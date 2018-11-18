package sv.edu.udb.www.ProyectoPOO.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sv.edu.udb.www.ProyectoPOO.entities.Usuarios;

@Repository("UsuariosRepository")
public interface UsuariosRepository extends JpaRepository<Usuarios, String> {
	
	public abstract Usuarios findByCorreo(String correo);
	public abstract List<Usuarios> findAllByOrderByIdUsuario();
	
	public abstract Usuarios findByIdUsuario(Integer id);
	
	@Query("SELECT u FROM Usuarios u INNER JOIN u.empresases e WHERE e.codigoEmpresa=?1")
	public abstract Usuarios usuarioPorCodigoEmpresa(String codigo);
}