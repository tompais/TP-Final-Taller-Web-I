package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.Models.Funcion;
import ar.edu.unlam.tallerweb1.Models.Reserva;

import java.util.List;

public interface ReservaDao {
	List<Funcion> consultarReservasUsuario(Long usuarioId);
	void realizarReserva (Reserva reserva);
	List<Reserva> getReservasByNumeroTicket(String numeroTicket);
}
