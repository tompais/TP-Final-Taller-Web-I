package ar.edu.unlam.tallerweb1.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import ar.edu.unlam.tallerweb1.Models.Funcion;
import ar.edu.unlam.tallerweb1.Models.PeliculaCine;
import ar.edu.unlam.tallerweb1.Models.TipoFuncion;

public interface FuncionDao {
	List<Funcion> consultarFunciones (PeliculaCine peliculaCine);
	Funcion consultarFuncion (Funcion funcion);
	Date getFechaUltimaFuncionByPeliculaCineAndTipoFuncionId(Long peliculaId, Long cineId, Long tipoFuncionId);
	List<TipoFuncion> getTipoFuncionesDisponiblesByPeliculaAndCineId(Long peliculaId, Long cineId);
	Funcion getFuncionByConfiguracion(Long peliculaId, Long cineId, Long tipoFuncionId, Date dia, Time hora);
}
