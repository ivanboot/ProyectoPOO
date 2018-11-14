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
@Table(name="estadooferta"
    ,catalog="bddpoo"
)
public class EstadoOferta implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idEstadoOferta;
    private String estado;
    
    private Set<Oferta> ofertas= new HashSet<Oferta>(0);
    
    public EstadoOferta(){
        this.idEstadoOferta=0;
        this.estado="";
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    public int getIdEstadoOferta() {
        return idEstadoOferta;
    }

    public void setIdEstadoOferta(int idEstadoOferta) {
        this.idEstadoOferta = idEstadoOferta;
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
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="estadooferta")
    public Set<Oferta> getOfertas() {
        return this.ofertas;
    }
    
    public void setOfertas(Set<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
}
