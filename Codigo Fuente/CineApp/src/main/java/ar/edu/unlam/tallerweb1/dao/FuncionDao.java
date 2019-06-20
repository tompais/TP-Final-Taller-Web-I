package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelos.Funcion;
import ar.edu.unlam.tallerweb1.modelos.PeliculaCine;

public interface FuncionDao {
	List<Funcion> consultarFunciones (PeliculaCine peliculaCine);
	Funcion consultarFuncion (Funcion funcion);
}
