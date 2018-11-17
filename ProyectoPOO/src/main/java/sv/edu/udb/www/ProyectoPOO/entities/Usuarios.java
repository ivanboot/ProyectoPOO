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
@Table(name="usuarios"
    ,catalog="bddpoo"
)
public class Usuarios implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idUsuario;
    private String correo;
    private String contrasenia;
    private TipoUsuario tipoUsuario;
    private int confirmado;
    private String id_confirmacion;
    
    private Set<Administrador> administrador = new HashSet<Administrador>(0);
    private Set<Empleado> empleado = new HashSet<Empleado>(0);
    private Set<Empresas> empresas = new HashSet<Empresas>(0);
    private Set<Clientes> clientes = new HashSet<Clientes>(0);
    
    public Usuarios(){
        this.idUsuario=0;
        this.correo="";
        this.contrasenia="";
        this.tipoUsuario=null;
        this.confirmado=0;
        this.id_confirmacion="";
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    public String getCorreo() {
        return correo;
    }
    
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    @JoinColumn(name = "IdTipoUsuario",nullable=false)
    @ManyToOne(fetch=FetchType.LAZY)
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    @Basic(optional = false)
    @NotNull
    public int getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(int confirmado) {
        this.confirmado = confirmado;
    }
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)    
    public String getId_confirmacion() {
        return id_confirmacion;
    }

    public void setId_confirmacion(String id_confirmacion) {
        this.id_confirmacion = id_confirmacion;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="usuarios")
    public Set<Administrador> getAdministrador() {
        return this.administrador;
    }
    
    public void setAdministrador(Set<Administrador> administrador) {
        this.administrador= administrador;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="usuarios")
    public Set<Empleado> getEmpleado() {
        return this.empleado;
    }
    
    public void setEmpleado(Set<Empleado> empleado) {
        this.empleado= empleado;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="usuarios")
    public Set<Empresas> getEmpresas() {
        return this.empresas;
    }
    
    public void setEmpresas(Set<Empresas> empresas) {
        this.empresas= empresas;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="usuarios")
    public Set<Clientes> getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Set<Clientes> clientes) {
        this.clientes= clientes;
    }
}
