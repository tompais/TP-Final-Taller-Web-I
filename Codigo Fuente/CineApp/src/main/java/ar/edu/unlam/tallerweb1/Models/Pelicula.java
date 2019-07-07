package ar.edu.unlam.tallerweb1.Models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
public class Pelicula {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Date fechaEstreno;

	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String sinopsis;
	@Column(nullable = false)
	private Integer duracion;

	@ManyToOne
	private Pais pais;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	private Clasificacion clasificacion;

	@OneToOne(cascade = CascadeType.REMOVE)
	private Poster poster;

	@OneToMany
	@JoinColumn(name = "pelicula_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<PeliculaGeneroPelicula> peliculaGeneroPeliculas;

	@OneToMany
	@JoinColumn(name = "pelicula_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<PeliculaActor> peliculaActores;

	@OneToOne
	private Trailer trailer;

	public List<PeliculaActor> getPeliculaActores() {
		return peliculaActores;
	}

	public void setPeliculaActores(List<PeliculaActor> peliculaActores) {
		this.peliculaActores = peliculaActores;
	}

	public Trailer getTrailer() {
		return trailer;
	}

	public void setTrailer(Trailer trailer) {
		this.trailer = trailer;
	}

	public List<PeliculaGeneroPelicula> getPeliculaGeneroPeliculas() {
		return peliculaGeneroPeliculas;
	}

	public void setPeliculaGeneroPeliculas(List<PeliculaGeneroPelicula> peliculaGeneroPeliculas) {
		this.peliculaGeneroPeliculas = peliculaGeneroPeliculas;
	}

	public Poster getPoster() {
		return poster;
	}

	public void setPoster(Poster poster) {
		this.poster = poster;
	}

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
