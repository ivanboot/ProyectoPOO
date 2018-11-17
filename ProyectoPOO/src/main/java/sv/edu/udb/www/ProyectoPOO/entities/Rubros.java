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
@Table(name="rubros"
    ,catalog="bddpoo"
)
public class Rubros implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idRubro;
    private String rubro;
    
    private Set<Empresas> empresas= new HashSet<Empresas>(0);
    
    public Rubros(){
        this.idRubro=0;
        this.rubro="";
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    public int getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(int idRubro) {
        this.idRubro = idRubro;
    }

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="rubros")
    public Set<Empresas> getEmpresas() {
        return this.empresas;
    }
    
    public void setEmpresas(Set<Empresas> empresas) {
        this.empresas= empresas;
    }
}
