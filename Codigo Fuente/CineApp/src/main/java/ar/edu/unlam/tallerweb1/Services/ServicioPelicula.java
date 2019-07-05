package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.Exceptions.PeliculaNoEncontradaException;
import ar.edu.unlam.tallerweb1.Models.Pelicula;

import java.util.List;

public interface ServicioPelicula {

    List<Pelicula> getPeliculasDisponiblesEnCartelera();
    List<Pelicula> getProximosEstrenos();
    Pelicula getPeliculaById(Long peliculaId) throws PeliculaNoEncontradaException;
}
