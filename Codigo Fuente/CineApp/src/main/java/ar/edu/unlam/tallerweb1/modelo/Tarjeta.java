package ar.edu.unlam.tallerweb1.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Tarjeta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Integer numero;
	private Integer codigoSeguridad;
	private String titular;
	private Integer DNITitular;
	private Date fechaVencimiento;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getCodigoSeguridad() {
		return codigoSeguridad;
	}

	public void setCodigoSeguridad(Integer codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Integer getDNITitular() {
		return DNITitular;
	}

	public void setDNITitular(Integer dNITitular) {
		DNITitular = dNITitular;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
