package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelos.Funcion;

import java.sql.Date;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelos.Asiento;
import ar.edu.unlam.tallerweb1.modelos.EstadoAsiento;
import ar.edu.unlam.tallerweb1.modelos.TipoAsiento;
import ar.edu.unlam.tallerweb1.modelos.TipoFuncion;
import ar.edu.unlam.tallerweb1.modelos.Usuario;
import ar.edu.unlam.tallerweb1.modelos.Pelicula;
import ar.edu.unlam.tallerweb1.modelos.Cine;
import ar.edu.unlam.tallerweb1.modelos.PeliculaCine;

public interface ServicioReserva {
	List<Pelicula> consultarPeliculas (Date actual);
	List<Cine> consultarCinesPelicula (Pelicula pelicula);
	List<Funcion> consultarFunciones (PeliculaCine peliculaCine);
	Asiento consultaAsiento (Asiento asiento);
	EstadoAsiento consultarEstadoAsiento (EstadoAsiento estadoAsiento);
	void cambiarEstadoAsiento (Asiento asiento);
	TipoAsiento consultarTipoAsiento (TipoAsiento tipoAsiento);
	TipoFuncion consultarTipoFuncion (TipoFuncion tipoFuncion);
	Integer reservar (Usuario usuario, Funcion funcion, Asiento asiento);
}
