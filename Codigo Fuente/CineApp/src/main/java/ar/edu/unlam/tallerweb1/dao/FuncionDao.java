package ar.edu.unlam.tallerweb1.dao;

import java.sql.Timestamp;
import java.util.List;

import ar.edu.unlam.tallerweb1.Models.Funcion;
import ar.edu.unlam.tallerweb1.Models.PeliculaCine;

public interface FuncionDao {
	List<Funcion> consultarFunciones (PeliculaCine peliculaCine);
	Funcion consultarFuncion (Funcion funcion);
	Timestamp getFechaUltimaFuncionByPeliculaAndCineId(Long peliculaId, Long cineId);
}
