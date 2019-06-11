package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Calificacion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Id
	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;
	
	@Id
	@OneToOne(cascade = CascadeType.ALL)
	private Pelicula pelicula;

	private TipoCalificacion tipoCalificacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public TipoCalificacion getTipoCalificacion() {
		return tipoCalificacion;
	}

	public void setTipoCalificacion(TipoCalificacion tipoCalificacion) {
		this.tipoCalificacion = tipoCalificacion;
	}
}
