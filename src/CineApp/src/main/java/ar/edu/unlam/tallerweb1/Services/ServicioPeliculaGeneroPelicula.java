package ar.edu.unlam.tallerweb1.Services;

import java.util.List;

import ar.edu.unlam.tallerweb1.Models.GeneroPelicula;
import ar.edu.unlam.tallerweb1.Models.Pelicula;

public interface ServicioPeliculaGeneroPelicula {

	List<GeneroPelicula> consultarGeneroPelis(List<Pelicula> peliculas);

	List<Pelicula> consultarPelisRecomendadas(List<Pelicula> peliculas, List<GeneroPelicula> generoPelis);

}
