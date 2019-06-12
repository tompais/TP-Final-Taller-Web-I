package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Asiento;
import ar.edu.unlam.tallerweb1.modelo.EstadoAsiento;

public interface AsientoDao {
	Asiento consultarAsiento (Asiento asiento);
	void cambiarEstado (EstadoAsiento estadoAsiento);
}
