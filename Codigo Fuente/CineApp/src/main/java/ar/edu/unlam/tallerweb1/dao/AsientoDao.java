package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelos.Asiento;

public interface AsientoDao {
	Asiento consultarAsiento (Asiento asiento);
	void cambiarEstado (Asiento asiento);
}
