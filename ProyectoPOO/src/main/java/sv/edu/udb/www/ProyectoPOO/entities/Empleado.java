package sv.edu.udb.www.ProyectoPOO.entities;
// Generated Nov 17, 2018 9:49:29 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Empleado generated by hbm2java
 */
@Entity
@Table(name="empleado"
    ,catalog="bddpoo"
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Empleado  implements java.io.Serializable {


     private Integer idEmpleado;
     private Empresas empresas;
     private Usuarios usuarios;
     private String nombreEmpleado;
     private String apellidoEmpleado;

    public Empleado() {
    }

    public Empleado(Empresas empresas, Usuarios usuarios, String nombreEmpleado, String apellidoEmpleado) {
       this.empresas = empresas;
       this.usuarios = usuarios;
       this.nombreEmpleado = nombreEmpleado;
       this.apellidoEmpleado = apellidoEmpleado;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="IdEmpleado", unique=true, nullable=false)
    public Integer getIdEmpleado() {
        return this.idEmpleado;
    }
    
    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CodigoEmpresa", nullable=false)
    public Empresas getEmpresas() {
        return this.empresas;
    }
    
    public void setEmpresas(Empresas empresas) {
        this.empresas = empresas;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IdUsuario", nullable=false)
    public Usuarios getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    
    @Column(name="NombreEmpleado", nullable=false, length=50)
    public String getNombreEmpleado() {
        return this.nombreEmpleado;
    }
    
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    
    @Column(name="ApellidoEmpleado", nullable=false, length=50)
    public String getApellidoEmpleado() {
        return this.apellidoEmpleado;
    }
    
    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }




}


