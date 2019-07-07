package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.Exceptions.PeliculaNoEncontradaException;
import ar.edu.unlam.tallerweb1.Models.Cine;
import ar.edu.unlam.tallerweb1.Models.Pelicula;

import java.util.List;

public interface ServicioPelicula {

    List<Pelicula> getPeliculasDisponiblesEnCartelera();
    List<Pelicula> getProximosEstrenos();
    Pelicula getPeliculaById(Long peliculaId) throws PeliculaNoEncontradaException;
    List<Cine> getCinesDisponiblesByPeliculaId(Long peliculaId);
    Double getPromedioCalificacionByPeliculaId(Long id);
}
