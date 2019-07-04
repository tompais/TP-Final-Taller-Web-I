package ar.edu.unlam.tallerweb1.Models;

import java.util.List;

import javax.persistence.*;

@Entity
public class Cine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Direccion direccion;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Sala> salas;

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
}
