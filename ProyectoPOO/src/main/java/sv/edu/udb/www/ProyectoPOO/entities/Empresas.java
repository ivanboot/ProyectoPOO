package sv.edu.udb.www.ProyectoPOO.entities;

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

import javax.persistence.Id;


@Entity
@Table(name = "empresas", catalog = "bddpoo")
public class Empresas implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoEmpresa;
	private String nombreEmpresa;
	private String nombreContacto;
	private String direccion;
	private String telefono;
	private Rubros rubros;
	private double comision;
	private Usuarios usuarios;
	
	private Set<Ofertas> oferta = new HashSet<Ofertas>(0);
	private Set<Empleado> empleado = new HashSet<Empleado>(0);
	
	public Empresas() {
		this.codigoEmpresa = "";
		this.nombreEmpresa = "";
		this.nombreContacto = "";
		this.direccion = "";
		this.telefono = "";
		this.rubros = null;
		this.comision = 0;
		this.usuarios = null;
		this.oferta=null;
		this.empleado=null;
	}

	@Id
	@Basic(optional = false)	
	@NotNull
	@Size(min = 1, max = 6)
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 9)
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@JoinColumn(name = "IdRubro", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	public Rubros getRubros() {
		return rubros;
	}

	public void setRubros(Rubros rubros) {
		this.rubros = rubros;
	}

	@Basic(optional = false)
	@NotNull
	public double getComision() {
		return comision;
	}

	public void setComision(double comision) {
		this.comision = comision;
	}
	
	@JoinColumn(name = "IdUsuario", nullable=false)
    @ManyToOne(fetch=FetchType.LAZY)
	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="empresas")
    public Set<Ofertas> getOferta() {
        return this.oferta;
    }
    
    public void setOferta(Set<Ofertas> oferta) {
        this.oferta = oferta;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="empresas")
    public Set<Empleado> getEmpleado() {
        return this.empleado;
    }
    
    public void setEmpleado(Set<Empleado> empleado) {
        this.empleado= empleado;
    }
}
