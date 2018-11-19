package sv.edu.udb.www.ProyectoPOO.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sv.edu.udb.www.ProyectoPOO.entities.Usuarios;
import sv.edu.udb.www.ProyectoPOO.repositories.UsuariosRepository;

@Service("usuariosService")
public class UsuariosService implements UserDetailsService{

	
	@Autowired
	@Qualifier("UsuariosRepository")
	UsuariosRepository usuariosRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuarios usuario= usuariosRepository.findByCorreoAndConfirmado(username,1);
		List<GrantedAuthority> lista= new ArrayList<>();
		lista.add(new SimpleGrantedAuthority(usuario.getTipousuario().getTipoUsuario()));
		return new User(username, usuario.getContrasena(), lista);
	}
			
			

}
