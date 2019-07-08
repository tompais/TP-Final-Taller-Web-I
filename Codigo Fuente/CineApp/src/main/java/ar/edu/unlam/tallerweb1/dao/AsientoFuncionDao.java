package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.Models.AsientoFuncion;
import ar.edu.unlam.tallerweb1.Models.Funcion;

public interface AsientoFuncionDao {

	List<AsientoFuncion> consultarAsientoFuncion(Funcion funcion);

}
