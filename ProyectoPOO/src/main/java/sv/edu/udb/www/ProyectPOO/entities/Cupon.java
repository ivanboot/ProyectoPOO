package sv.edu.udb.www.ProyectPOO.entities;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "cupones", catalog = "bddpoo")

public class Cupon implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoCupo;
	private Date fechaCompra;
	private Date fechaCanje;
	private Cliente cliente;
	private Oferta oferta;
	private EstadoCupon estadoCupon;

	public Cupon() {
		this.codigoCupo = "";
		this.fechaCompra = new Date();
		this.fechaCanje = new Date();
		this.cliente = null;
		this.oferta = null;
		this.estadoCupon = null;
	}

	@Id
	@Basic(optional = false)	
	@NotNull
	@Size(min = 1, max = 13)
	public String getCodigoCupo() {
		return codigoCupo;
	}

	public void setCodigoCupo(String codigoCupo) {
		this.codigoCupo = codigoCupo;
	}

	@Basic(optional = false)
	@NotNull
	@Temporal(TemporalType.DATE)
	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	@Basic(optional = false)
	@NotNull
	@Temporal(TemporalType.DATE)
	public Date getFechaCanje() {
		return fechaCanje;
	}

	public void setFechaCanje(Date fechaCanje) {
		this.fechaCanje = fechaCanje;
	}

	@JoinColumn(name = "IdCliente",nullable=false)
	@ManyToOne(fetch = FetchType.LAZY)
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@JoinColumn(name = "IdOferta", nullable=false)
    @ManyToOne(fetch= FetchType.LAZY)
	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}
	
	@JoinColumn(name = "IdEstadoCupon", nullable=false)
    @ManyToOne(fetch= FetchType.LAZY)
	public EstadoCupon getEstadoCupon() {
		return estadoCupon;
	}

	public void setEstadoCupon(EstadoCupon estadoCupon) {
		this.estadoCupon = estadoCupon;
	}

}
