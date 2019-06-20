package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelos.EstadoAsiento;

public interface EstadoAsientoDao {
	EstadoAsiento consultarEstadoAsiento (EstadoAsiento estadoAsiento);
	void cambiarEstadoAsiento (EstadoAsiento estadoAsiento);
}
