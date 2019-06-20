package ar.edu.unlam.tallerweb1.dao;

import java.sql.Date;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelos.Pelicula;

public interface PeliculaDao {
	List<Pelicula> consultarPeliculas (Date actual);
}
