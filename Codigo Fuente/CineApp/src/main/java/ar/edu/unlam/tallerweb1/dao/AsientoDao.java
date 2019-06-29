package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.Models.Asiento;

public interface AsientoDao {
	Asiento consultarAsiento (Asiento asiento);
	void cambiarEstado (Asiento asiento);
}
