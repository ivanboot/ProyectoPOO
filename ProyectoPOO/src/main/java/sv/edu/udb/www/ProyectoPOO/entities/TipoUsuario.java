package sv.edu.udb.www.ProyectoPOO.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tipousuario")
public class TipoUsuario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="IdTipoUsuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipoUsuario;
	private String tipoUsuarios;

	public TipoUsuario() {
		this.idTipoUsuario = 0;
		this.tipoUsuarios = "";
	}

	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}
	
	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario=idTipoUsuario;
	}
	
	public String getTipoUsuarios() {
		return tipoUsuarios;
	}
	
	public void setTipoUsuarios(String tipoUsuarios) {
		this.tipoUsuarios = tipoUsuarios;
	}
}
