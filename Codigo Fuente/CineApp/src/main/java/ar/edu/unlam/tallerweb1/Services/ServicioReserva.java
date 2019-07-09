package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.Models.Funcion;

import java.sql.Date;
import java.util.List;

import ar.edu.unlam.tallerweb1.Models.Asiento;
import ar.edu.unlam.tallerweb1.Models.EstadoAsiento;
import ar.edu.unlam.tallerweb1.Models.TipoAsiento;
import ar.edu.unlam.tallerweb1.Models.TipoFuncion;
import ar.edu.unlam.tallerweb1.Models.Usuario;
import ar.edu.unlam.tallerweb1.ViewModels.SalaViewModel;
import ar.edu.unlam.tallerweb1.Models.Pelicula;
import ar.edu.unlam.tallerweb1.Models.PeliculaCine;
import ar.edu.unlam.tallerweb1.Models.Sala;

public interface ServicioReserva {
	List<Pelicula> consultarPeliculas (Date actual);
	List<PeliculaCine> consultarCinesPelicula (Pelicula pelicula);
	List<Funcion> consultarFunciones (PeliculaCine peliculaCine);
	Asiento consultaAsiento (Asiento asiento);
	EstadoAsiento consultarEstadoAsiento (EstadoAsiento estadoAsiento);
	void cambiarEstadoAsiento (Asiento asiento);
	TipoAsiento consultarTipoAsiento (TipoAsiento tipoAsiento);
	TipoFuncion consultarTipoFuncion (TipoFuncion tipoFuncion);
	Integer reservar (Usuario usuario, Funcion funcion, Asiento asiento);
	Sala consultarSala (Sala sala);
	Funcion consultarFuncion(Funcion funcion);
	SalaViewModel[][] formatoSala(Funcion funcion, int fil, int col);
}
