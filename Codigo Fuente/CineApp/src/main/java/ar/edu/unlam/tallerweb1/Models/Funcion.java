package ar.edu.unlam.tallerweb1.Models;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
public class Funcion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Date fecha;

	@Column(nullable = false)
	private Time hora;

	@Column(nullable = false, unique = true)
	private Double precio;
	
	@ManyToOne
	private Pelicula pelicula;
	
	@ManyToOne
	private TipoFuncion tipoFuncion;
	
	@ManyToOne
	private Cine cine;

	@ManyToOne
	private Sala sala;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Cine getCine() {
		return cine;
	}

	public void setCine(Cine cine) {
		this.cine = cine;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public TipoFuncion getTipoFuncion() {
		return tipoFuncion;
	}

	public void setTipoFuncion(TipoFuncion tipoFuncion) {
		this.tipoFuncion = tipoFuncion;
	}
}
