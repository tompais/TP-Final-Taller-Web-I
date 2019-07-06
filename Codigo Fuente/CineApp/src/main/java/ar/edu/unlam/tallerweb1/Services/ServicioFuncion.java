package ar.edu.unlam.tallerweb1.Services;

import java.sql.Timestamp;

public interface ServicioFuncion {
    Timestamp getFechaUltimaFuncionByPeliculaAndCineId(Long peliculaId, Long cineId);
}
