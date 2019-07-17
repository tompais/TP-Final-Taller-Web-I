package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.Models.Funcion;
import ar.edu.unlam.tallerweb1.Models.Reserva;

public interface ReservaDao {
	Boolean realizarReserva (Reserva reserva);

	List<Funcion> consultarReservasUsuario(Long usuarioId);
}
