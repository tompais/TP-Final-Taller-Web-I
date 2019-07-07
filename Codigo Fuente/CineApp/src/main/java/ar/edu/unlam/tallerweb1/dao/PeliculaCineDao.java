package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.Models.Cine;
import ar.edu.unlam.tallerweb1.Models.Pelicula;
import ar.edu.unlam.tallerweb1.Models.PeliculaCine;

import java.util.List;

public interface PeliculaCineDao {
	List<PeliculaCine> consultarCinesPelicula (Pelicula pelicula);

	List<Pelicula> consultarPeliculasDisponiblesEnCartelera();

	List<Cine> getCinesDisponiblesByPeliculaId(Long peliculaId);
}
