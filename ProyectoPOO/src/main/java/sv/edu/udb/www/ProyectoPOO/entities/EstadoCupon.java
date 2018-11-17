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
@Table(name="estadocupon"
    ,catalog="bddpoo"
)
public class EstadoCupon implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idEstadoCupon;
    private String estado;
    
    private Set<Cupones> cupones= new HashSet<Cupones>(0);
    
    public EstadoCupon(){
        this.idEstadoCupon=0;
        this.estado="";
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    public int getIdEstadoCupon() {
        return idEstadoCupon;
    }
        
    public void setIdEstadoCupon(int idEstadoCupon) {
        this.idEstadoCupon = idEstadoCupon;
    }

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="estadocupon")
    public Set<Cupones> getCupones() {
        return cupones;
    }
    
    public void setCupones(Set<Cupones> cupones) {
        this.cupones = cupones;
    }
}
