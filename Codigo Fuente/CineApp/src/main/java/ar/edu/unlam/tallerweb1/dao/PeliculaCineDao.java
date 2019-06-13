package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Cine;

public interface PeliculaCineDao {
	List<Cine> consultarCinesPelicula (Pelicula pelicula);
}
