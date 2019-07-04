package ar.edu.unlam.tallerweb1.Models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class AsientoFuncion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Asiento asiento;
	
	@ManyToOne
	private Funcion funcion;
	
	@OneToOne
	private EstadoAsiento estadoAsiento;

	public EstadoAsiento getEstadoAsiento() {
		return estadoAsiento;
	}

	public void setEstadoAsiento(EstadoAsiento estadoAsiento) {
		this.estadoAsiento = estadoAsiento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}

	public Funcion getFuncion() {
		return funcion;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}
}
