package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.Models.TipoFuncion;

import java.sql.Date;
import java.util.List;

public interface ServicioFuncion {
    Date getFechaUltimaFuncionByPeliculaCineAndTipoFuncionId(Long peliculaId, Long cineId, Long tipoFuncionId);
    List<TipoFuncion> getTipoFuncionesDisponiblesByPeliculaAndCineId(Long peliculaId, Long cineId);
}
