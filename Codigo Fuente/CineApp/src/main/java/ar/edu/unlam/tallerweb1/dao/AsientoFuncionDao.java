package ar.edu.unlam.tallerweb1.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import ar.edu.unlam.tallerweb1.Models.AsientoFuncion;
import ar.edu.unlam.tallerweb1.Models.Funcion;

public interface AsientoFuncionDao {

	List<AsientoFuncion> consultarAsientoFuncion(Funcion funcion);

	List<Time> getHorariosLibresFuncion(Long peliculaId, Long cineId, Long tipoFuncionId, Date fecha);

}
