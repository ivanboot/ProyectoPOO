package sv.edu.udb.www.ProyectoPOO.entities;
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

import javax.persistence.Id;

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
	private Usuarios usuarios;
	private Empresas empresas;

	public Empleado() {
		this.idEmpleado = 0;
		this.nombreEmpleado = "";
		this.apellidoEmpleado = "";
		this.usuarios = null;
		this.empresas = null;
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
	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}
	
    @ManyToOne(fetch=FetchType.LAZY)	
	@JoinColumn(name = "codigoEmpresa", nullable=false)
	public Empresas getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Empresas empresas) {
		this.empresas = empresas;
	}
}
