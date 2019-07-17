package ar.edu.unlam.tallerweb1.Models;

import java.sql.Date;

import javax.persistence.*;

@Entity
public class Tarjeta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private Integer numero;
	@Column(nullable = false)
	private Integer codigoSeguridad;
	@Column(nullable = false)
	private Integer DNITitular;
	@Column(nullable = false)
	private Date fechaVencimiento;
	
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
	
}
