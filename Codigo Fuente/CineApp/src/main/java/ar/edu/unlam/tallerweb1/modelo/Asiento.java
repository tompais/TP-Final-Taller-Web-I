package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Asiento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Integer fila;
	private Integer columna;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Sala sala;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private TipoAsiento tipoAsiento;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private EstadoAsiento estadoAsiento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFila() {
		return fila;
	}

	public void setFila(Integer fila) {
		this.fila = fila;
	}

	public Integer getColumna() {
		return columna;
	}

	public void setColumna(Integer columna) {
		this.columna = columna;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public TipoAsiento getTipoAsiento() {
		return tipoAsiento;
	}

	public void setTipoAsiento(TipoAsiento tipoAsiento) {
		this.tipoAsiento = tipoAsiento;
	}

	public EstadoAsiento getEstadoAsiento() {
		return estadoAsiento;
	}

	public void setEstadoAsiento(EstadoAsiento estadoAsiento) {
		this.estadoAsiento = estadoAsiento;
	}
}
