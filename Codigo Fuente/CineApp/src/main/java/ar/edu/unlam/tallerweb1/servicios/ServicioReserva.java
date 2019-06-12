package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Sala;
import ar.edu.unlam.tallerweb1.modelo.Asiento;
import ar.edu.unlam.tallerweb1.modelo.EstadoAsiento;
import ar.edu.unlam.tallerweb1.modelo.TipoAsiento;
import ar.edu.unlam.tallerweb1.modelo.TipoFuncion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioReserva {
	Funcion consultarFuncion (Funcion funcion);
	Sala consultarSala (Sala sala);
	Asiento consultaAsiento (Asiento asiento);
	EstadoAsiento consultarEstadoAsiento (EstadoAsiento estadoAsiento);
	void cambiarEstadoAsiento (EstadoAsiento estadoAsiento);
	TipoAsiento consultarTipoAsiento (TipoAsiento tipoAsiento);
	TipoFuncion consultarTipoFuncion (TipoFuncion tipoFuncion);
	Integer reservar (Usuario usuario, Funcion funcion, Asiento asiento);
}
