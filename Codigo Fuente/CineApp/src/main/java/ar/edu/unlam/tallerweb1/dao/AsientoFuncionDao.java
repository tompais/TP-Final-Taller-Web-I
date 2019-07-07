package ar.edu.unlam.tallerweb1.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import ar.edu.unlam.tallerweb1.Models.AsientoFuncion;

public interface AsientoFuncionDao {

	List<AsientoFuncion> consultarAsientoFuncion(int idFuncion);

	List<Time> getHorariosLibresFuncion(Long peliculaId, Long cineId, Long tipoFuncionId, Date fecha);

}
