package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.Models.EstadoAsiento;

public interface EstadoAsientoDao {
	EstadoAsiento consultarEstadoAsiento (EstadoAsiento estadoAsiento);
	EstadoAsiento getEstadoAsientoById(Long estadoAsientoId);
	void cambiarEstadoAsiento (EstadoAsiento estadoAsiento);
}
