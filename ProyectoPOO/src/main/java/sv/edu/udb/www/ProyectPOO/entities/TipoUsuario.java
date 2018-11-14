package sv.edu.udb.www.ProyectPOO.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "tipousuario", catalog = "bddpoo")
public class TipoUsuario implements java.io.Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private int idTipoUsuario;
	private String tipoUsuario;
	
	private Set<Usuario> usuarios= new HashSet<Usuario>(0);
	
	public TipoUsuario() {
		this.idTipoUsuario = 0;
		this.tipoUsuario = "";
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
	
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="tipousuario")
    public Set<Usuario> getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
