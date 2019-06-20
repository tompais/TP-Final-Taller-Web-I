package ar.edu.unlam.tallerweb1.modelos;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Funcion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date diaYHora;
	private Double precio;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Pelicula pelicula;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private TipoFuncion tipoFuncion;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Sala sala;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Cine cine;

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

	public Date getDiaYHora() {
		return diaYHora;
	}

	public void setDiaYHora(Date diaYHora) {
		this.diaYHora = diaYHora;
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

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
}
