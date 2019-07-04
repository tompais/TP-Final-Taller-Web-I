package ar.edu.unlam.tallerweb1.Models;

import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Funcion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(nullable = false, unique = true)
	private Date diaYHora;
	@Column(nullable = false, unique = true)
	private Double precio;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Pelicula pelicula;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private TipoFuncion tipoFuncion;
	
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
}
