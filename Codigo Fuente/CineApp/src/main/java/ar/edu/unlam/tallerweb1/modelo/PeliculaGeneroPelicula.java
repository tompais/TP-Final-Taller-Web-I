package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PeliculaGeneroPelicula {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	private Pelicula pelicula;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	private GeneroPelicula generoPelicula;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public GeneroPelicula getGeneroPelicula() {
		return generoPelicula;
	}

	public void setGeneroPelicula(GeneroPelicula generoPelicula) {
		this.generoPelicula = generoPelicula;
	}
}
