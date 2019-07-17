package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.Models.Funcion;
import ar.edu.unlam.tallerweb1.Models.Reserva;

import java.util.List;

public interface ReservaDao {
<<<<<<< HEAD
	Boolean realizarReserva (Reserva reserva);

	List<Funcion> consultarReservasUsuario(Long usuarioId);
=======
	void realizarReserva (Reserva reserva);
	List<Reserva> getReservasByNumeroTicket(String numeroTicket);
>>>>>>> be824af473b7ed9a09cde82b906a91fce6fd858a
}
