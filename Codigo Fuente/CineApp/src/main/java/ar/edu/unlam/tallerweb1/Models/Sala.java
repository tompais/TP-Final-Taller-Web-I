package ar.edu.unlam.tallerweb1.Models;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Sala {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer numero;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Cine cine;
	
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Asiento> asientos;

	public List<Asiento> getAsientos() {
		return asientos;
	}

	public void setAsientos(List<Asiento> asientos) {
		this.asientos = asientos;
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

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
}
