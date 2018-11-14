package sv.edu.udb.www.ProyectPOO.entities;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

@Entity
@Table(name="empleado"
    ,catalog="bddpoo"
)
public class Empleado implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idEmpleado;
	private String nombreEmpleado;
	private String apellidoEmpleado;
	private Usuario usuario;
	private Empresa empresa;

	public Empleado() {
		this.idEmpleado = 0;
		this.nombreEmpleado = "";
		this.apellidoEmpleado = "";
		this.usuario = null;
		this.empresa = null;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Basic(optional = false)
	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
	public String getApellidoEmpleado() {
		return apellidoEmpleado;
	}

	public void setApellidoEmpleado(String apellidoEmpleado) {
		this.apellidoEmpleado = apellidoEmpleado;
	}
	
	@JoinColumn(name = "IdUsuario", nullable=false)
    @ManyToOne(fetch = FetchType.LAZY)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@JoinColumn(name = "CodigoEmpresa", nullable=false)
    @ManyToOne(fetch=FetchType.LAZY)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}
