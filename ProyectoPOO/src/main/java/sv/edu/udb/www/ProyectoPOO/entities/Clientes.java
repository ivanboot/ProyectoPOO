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
@Table(name="clientes"
    ,catalog="bddpoo"
)
public class Clientes implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idCliente;
    private String nombreClientes;
    private String apellidosClientes;
    private String direccion;
    private String dui;
    private Usuarios usuarios;
    
    private Set<Cupones> cupones = new HashSet<Cupones>(0);
    
    public Clientes(){
        this.idCliente=0;
        this.nombreClientes="";
        this.apellidosClientes="";
        this.direccion="";
        this.dui="";
        this.usuarios=null;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Basic(optional = false)
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    public String getNombreClientes() {
        return nombreClientes;
    }

    public void setNombreClientes(String nombreClientes) {
        this.nombreClientes = nombreClientes;
    }
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    public String getApellidosClientes() {
        return apellidosClientes;
    }

    public void setApellidosClientes(String apellidosClientes) {
        this.apellidosClientes = apellidosClientes;
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
    @Size(min = 1, max = 10)
    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }
    
    @JoinColumn(name = "IdUsuario", nullable=false)
    @ManyToOne(fetch=FetchType.LAZY)
    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuario(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="clientes")
    public Set<Cupones> getCupones() {
        return this.cupones;
    }
    
    public void setCupones(Set<Cupones> cupones) {
        this.cupones = cupones;
    }
    
}
