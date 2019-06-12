package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Cartelera;

public interface FuncionDao {
	List<Funcion> consultarFuncionesPelicula (Pelicula pelicula,Cartelera cartelera);
	List<Funcion> consultarFunciones (Cartelera cartelera);
	Funcion consultarFuncion (Funcion funcion);
}
