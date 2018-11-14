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
@Table(name = "empresas", catalog = "bddpoo")
public class Empresa implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoEmpresa;
	private String nombreEmpresa;
	private String nombreContacto;
	private String direccion;
	private String telefono;
	private Rubro rubro;
	private double comision;
	private Usuario usuario;
	
	private Set<Oferta> ofertas = new HashSet<Oferta>(0);
	private Set<Empleado> empleados = new HashSet<Empleado>(0);
	
	public Empresa() {
		this.codigoEmpresa = "";
		this.nombreEmpresa = "";
		this.nombreContacto = "";
		this.direccion = "";
		this.telefono = "";
		this.rubro = null;
		this.comision = 0;
		this.usuario = null;
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
	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
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
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="empresas")
    public Set<Oferta> getOfertas() {
        return this.ofertas;
    }
    
    public void setOfertas(Set<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="empresas")
    public Set<Empleado> getEmpleados() {
        return this.empleados;
    }
    
    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados= empleados;
    }
}
