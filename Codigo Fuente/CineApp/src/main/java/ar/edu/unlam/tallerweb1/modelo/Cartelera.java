package ar.edu.unlam.tallerweb1.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cartelera {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date fechaDesde;
	private Date fechaHasta;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
}
