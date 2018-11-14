package sv.edu.udb.www.ProyectPOO.entities;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;


@Entity
@Table(name="ofertas"
    ,catalog="bddpoo"
)
public class Oferta implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idOferta;
    private String tituloOferta;
    private double precioRegular;
    private double precioOferta;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaLimite;
    private int cantidadLimite;
    private String descripcionOferta;
    private String otrosDetalles;
    private EstadoOferta estadoOferta;
    private String justificacion;
    private Empresa empresa;
    private String url_foto;
    
    private Set<Cupon> cupones = new HashSet<Cupon>(0);
    
    public Oferta(){
        this.idOferta=0;
        this.tituloOferta="";
        this.precioOferta=0;
        this.precioRegular=0;
        this.fechaInicio=new Date();
        this.fechaFin=new Date();
        this.fechaLimite=new Date();
        this.cantidadLimite=0;
        this.descripcionOferta="";
        this.otrosDetalles="";
        this.estadoOferta=null;
        this.justificacion="";
        this.empresa=null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    public String getTituloOferta() {
        return tituloOferta;
    }

    public void setTituloOferta(String tituloOferta) {
        this.tituloOferta = tituloOferta;
    }

    @Basic(optional = false)
    @NotNull
    public double getPrecioRegular() {
        return precioRegular;
    }

    public void setPrecioRegular(double precioRegular) {
        this.precioRegular = precioRegular;
    }
    
    @Basic(optional = false)
    @NotNull
    public double getPrecioOferta() {
        return precioOferta;
    }

    public void setPrecioOferta(double precioOferta) {
        this.precioOferta = precioOferta;
    }
    
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public int getCantidadLimite() {
        return cantidadLimite;
    }

    public void setCantidadLimite(int cantidadLimite) {
        this.cantidadLimite = cantidadLimite;
    }
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    public String getDescripcionOferta() {
        return descripcionOferta;
    }

    public void setDescripcionOferta(String descripcionOferta) {
        this.descripcionOferta = descripcionOferta;
    }
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    public String getOtrosDetalles() {
    	return otrosDetalles;
    }
    
    public void setOtrosDetalles(String otrosDetalles) {
    	this.otrosDetalles = otrosDetalles;
    }
    
    @JoinColumn(name = "IdEstado", nullable=false)
    @ManyToOne(fetch=FetchType.LAZY)
    public EstadoOferta getEstadoOferta() {
        return estadoOferta;
    }
    
    public void setEstadoOferta(EstadoOferta estadoOferta) {
        this.estadoOferta = estadoOferta;
    }
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }
    
    @JoinColumn(name = "CodigoEmpresa", nullable=false)
    @ManyToOne(fetch=FetchType.LAZY)
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Size(max = 100)    
    public String getUrl_foto() {
        return url_foto;
    }

    public void setUrl_foto(String url_foto) {
        this.url_foto = url_foto;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="ofertas")
    public Set<Cupon> getCupones() {
        return this.cupones;
    }
    
    public void setCupones(Set<Cupon> cupones) {
        this.cupones = cupones;
    }
    
}
