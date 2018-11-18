package sv.edu.udb.www.ProyectoPOO.entities;
// Generated Nov 17, 2018 9:49:29 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Usuarios generated by hbm2java
 */
@Entity
@Table(name="usuarios"
    ,catalog="bddpoo"
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuarios  implements java.io.Serializable {


     private Integer idUsuario;
     private Tipousuario tipousuario;
          
     private String correo;
     
     private String contrasena;
     private boolean confirmado;
     private String idConfirmacion;
     private Set<Empresas> empresases = new HashSet<Empresas>(0);
     private Set<Administrador> administradors = new HashSet<Administrador>(0);
     private Set<Empleado> empleados = new HashSet<Empleado>(0);
     private Set<Clientes> clienteses = new HashSet<Clientes>(0);

    public Usuarios() {
    }
    
    public Usuarios(Integer idUsuario) {
    	this.idUsuario=idUsuario;
    }
	
    public Usuarios(Tipousuario tipousuario, String correo, String contrasena, boolean confirmado, String idConfirmacion) {
        this.tipousuario = tipousuario;
        this.correo = correo;
        this.contrasena = contrasena;
        this.confirmado = confirmado;
        this.idConfirmacion = idConfirmacion;
    }
    public Usuarios(Tipousuario tipousuario, String correo, String contrasena, boolean confirmado, String idConfirmacion, Set<Empresas> empresases, Set<Administrador> administradors, Set<Empleado> empleados, Set<Clientes> clienteses) {
       this.tipousuario = tipousuario;
       this.correo = correo;
       this.contrasena = contrasena;
       this.confirmado = confirmado;
       this.idConfirmacion = idConfirmacion;
       this.empresases = empresases;
       this.administradors = administradors;
       this.empleados = empleados;
       this.clienteses = clienteses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="IdUsuario", unique=true, nullable=false)
    public Integer getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="IdTipoUsuario", nullable=false)
    public Tipousuario getTipousuario() {
        return this.tipousuario;
    }
    
    public void setTipousuario(Tipousuario tipousuario) {
        this.tipousuario = tipousuario;
    }

    
    @Column(name="Correo", nullable=false, length=100)    
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
    @Column(name="Contrasena", nullable=false, length=100)
    public String getContrasena() {
        return this.contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    
    @Column(name="Confirmado", nullable=false)
    public boolean isConfirmado() {
        return this.confirmado;
    }
    
    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    
    @Column(name="Id_confirmacion", nullable=false, length=64)
    public String getIdConfirmacion() {
        return this.idConfirmacion;
    }
    
    public void setIdConfirmacion(String idConfirmacion) {
        this.idConfirmacion = idConfirmacion;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuarios")
    public Set<Empresas> getEmpresases() {
        return this.empresases;
    }
    
    public void setEmpresases(Set<Empresas> empresases) {
        this.empresases = empresases;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuarios")
    public Set<Administrador> getAdministradors() {
        return this.administradors;
    }
    
    public void setAdministradors(Set<Administrador> administradors) {
        this.administradors = administradors;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuarios")
    public Set<Empleado> getEmpleados() {
        return this.empleados;
    }
    
    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuarios")
    public Set<Clientes> getClienteses() {
        return this.clienteses;
    }
    
    public void setClienteses(Set<Clientes> clienteses) {
        this.clienteses = clienteses;
    }




}


