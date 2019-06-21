package ar.edu.unlam.tallerweb1.Models;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Pelicula {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date fechaEstreno;
	
	private String nombre;
	private String sinopsis;
	private Integer duracion;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Pais pais;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Clasificacion clasificacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(Date fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Clasificacion getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(Clasificacion clasificacion) {
		this.clasificacion = clasificacion;
	}
}
