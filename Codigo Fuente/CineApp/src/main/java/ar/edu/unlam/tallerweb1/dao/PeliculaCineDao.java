package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.Models.Pelicula;

import java.util.List;

import ar.edu.unlam.tallerweb1.Models.Cine;

public interface PeliculaCineDao {
	List<Cine> consultarCinesPelicula (Pelicula pelicula);
}
