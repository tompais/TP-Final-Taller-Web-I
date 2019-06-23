package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.Models.Pelicula;

import java.sql.Date;
import java.util.List;

public interface ServicioPelicula {

    List<Pelicula> getPeliculasEnCartelera();

    List<Pelicula> getPeliculasDisponiblesEnCartelera();
}
