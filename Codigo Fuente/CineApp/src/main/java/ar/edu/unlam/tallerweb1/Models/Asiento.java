package ar.edu.unlam.tallerweb1.Models;

import javax.persistence.*;

@Entity
public class Asiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer fila;
	@Column(nullable = false)
	private Integer columna;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Sala sala;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private TipoAsiento tipoAsiento;

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
}
