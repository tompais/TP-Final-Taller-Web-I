package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.Exceptions.*;
import ar.edu.unlam.tallerweb1.Models.*;

import java.sql.Date;
import java.util.List;

import ar.edu.unlam.tallerweb1.ViewModels.ReservaViewModel;
import ar.edu.unlam.tallerweb1.ViewModels.AsientoViewModel;

public interface ServicioReserva {
	List<Pelicula> consultarPeliculas (Date actual);
	List<PeliculaCine> consultarCinesPelicula (Pelicula pelicula);
	List<Funcion> consultarFunciones (PeliculaCine peliculaCine);
	Asiento consultaAsiento (Long asientoId);
	EstadoAsiento consultarEstadoAsiento (EstadoAsiento estadoAsiento);
	TipoAsiento consultarTipoAsiento (TipoAsiento tipoAsiento);
	TipoFuncion consultarTipoFuncion (TipoFuncion tipoFuncion);
	String reservar (Usuario usuario, ReservaViewModel reservaViewModel);
	Sala consultarSala (Sala sala);
	Funcion consultarFuncionById(Long funcionId) throws FuncionInvalidaException;
	AsientoViewModel[][] formatoSala(Long funcionId, int fil, int col);
	void actualizarEstadoAsiento(Long funcionId, Integer fila, Integer columna, Long estadoId) throws EstadoAsientoInvalidoException, PosicionAsientoInvalidoException, FuncionByIdNoEncontradaException, AsientoFuncionByFuncionIdAndPosicionNoEncontradoException, EstadoAsientoByIdNoEncontradoException, InconsistenciaCambioEstadoAsientoException;
	void validarEstadoAsiento(Long estadoId) throws EstadoAsientoInvalidoException;
	void validarPosicionAsiento(Integer fila, Integer columna) throws PosicionAsientoInvalidoException;
	List<Reserva> getReservasByNumeroTicket(String numeroTicket) throws NumeroTicketInvalidoException;
}
