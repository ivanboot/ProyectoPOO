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
@Table(name="administrador"
    ,catalog="bddpoo"
)
public class Administrador {
	 private int idAdministrador;
	    private String nombreAdministrador;
	    private String apellidoAdministrador;
	    private Usuarios usuarios;
	    
	    public Administrador(){
	        this.idAdministrador=0;
	        this.nombreAdministrador="";
	        this.apellidoAdministrador="";
	        this.usuarios=null;
	    }
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @NotNull
	    @Basic(optional = false)
	    public int getIdAdministrador() {
	        return idAdministrador;
	    }

	    public void setIdAdministrador(int idAdministrador) {
	        this.idAdministrador = idAdministrador;
	    }
	    
	    @Basic(optional = false)
	    @NotNull
	    @Size(min = 1, max = 100)
	    public String getNombreAdministrador() {
	        return nombreAdministrador;
	    }

	    public void setNombreAdministrador(String nombreAdministrador) {
	        this.nombreAdministrador = nombreAdministrador;
	    }
	    
	    @Basic(optional = false)
	    @NotNull
	    @Size(min = 1, max = 100)
	    public String getApellidoAdministrador() {
	        return apellidoAdministrador;
	    }

	    public void setApellidoAdministrador(String apellidoAdministrador) {
	        this.apellidoAdministrador = apellidoAdministrador;
	    }
	    
	    @ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name = "IdUsuario", nullable=false)
	    public Usuarios getUsuarios() {
	        return usuarios;
	    }

	    public void setUsuario(Usuarios usuarios) {
	        this.usuarios = usuarios;
	    }
}
