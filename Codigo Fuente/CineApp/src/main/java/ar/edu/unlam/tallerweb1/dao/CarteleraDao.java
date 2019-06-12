package ar.edu.unlam.tallerweb1.dao;

import java.util.Date;

import ar.edu.unlam.tallerweb1.modelo.Cartelera;

public interface CarteleraDao {
	Cartelera consultarCartelera (Date fechaActual);
}
