package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.Models.Reserva;

import java.util.List;

public interface ReservaDao {
	void realizarReserva (Reserva reserva);
	List<Reserva> getReservasByNumeroTicket(String numeroTicket);
}
