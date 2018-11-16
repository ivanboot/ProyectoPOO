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

//import sv.edu.udb.www.DemoSpring.entities.Usuario;
//import sv.edu.udb.www.DemoSpring.repositories.UsuariosRepository;

@Service("usuariosService")
public class UsuariosService{

	
	/*@Autowired
	@Qualifier("usuariosRepository")
	UsuariosRepository usuariosRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario= usuariosRepository.findByUsuario(username);
		List<GrantedAuthority> lista= new ArrayList<>();
		lista.add(new SimpleGrantedAuthority(usuario.getTipoUsuario().getTipoUsuario()));
	return new User(username, usuario.getPassword(), lista);
	}*/
			
			

}
