package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelos.Pelicula;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelos.Cine;

public interface PeliculaCineDao {
	List<Cine> consultarCinesPelicula (Pelicula pelicula);
}
