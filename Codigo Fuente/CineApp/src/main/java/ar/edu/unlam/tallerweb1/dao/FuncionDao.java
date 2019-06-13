package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.PeliculaCine;

public interface FuncionDao {
	List<Funcion> consultarFunciones (PeliculaCine peliculaCine);
	Funcion consultarFuncion (Funcion funcion);
}
