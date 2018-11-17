package sv.edu.udb.www.ProyectoPOO.entities;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cupones", catalog = "bddpoo")

public class Cupones implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoCupo;
	private Date fechaCompra;
	private Date fechaCanje;
	private Clientes clientes;
	private Ofertas ofertas;
	private EstadoCupon estadoCupon;

	public Cupones() {
		this.codigoCupo = "";
		this.fechaCompra = new Date();
		this.fechaCanje = new Date();
		this.clientes = null;
		this.ofertas = null;
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
	public Clientes getClientes() {
		return clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	@JoinColumn(name = "IdOferta", nullable=false)
    @ManyToOne(fetch= FetchType.LAZY)
	public Ofertas getOfertas() {
		return ofertas;
	}

	public void setOfertas(Ofertas ofertas) {
		this.ofertas = ofertas;
	}
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "IdEstadoCupon", nullable=false)    
	public EstadoCupon getEstadoCupon() {
		return this.estadoCupon;
	}

	public void setEstadoCupon(EstadoCupon estadoCupon) {
		this.estadoCupon = estadoCupon;
	}

}
