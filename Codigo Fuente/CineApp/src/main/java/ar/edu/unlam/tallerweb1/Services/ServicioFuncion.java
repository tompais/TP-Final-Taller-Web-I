package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.Exceptions.FechaUltimaFuncionMenorAFechaActualException;
import ar.edu.unlam.tallerweb1.Exceptions.TipoFuncionInvalidaException;
import ar.edu.unlam.tallerweb1.Models.Funcion;
import ar.edu.unlam.tallerweb1.Models.TipoFuncion;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

public interface ServicioFuncion {
    Date getFechaUltimaFuncionByPeliculaCineAndTipoFuncionId(Long peliculaId, Long cineId, Long tipoFuncionId) throws FechaUltimaFuncionMenorAFechaActualException, TipoFuncionInvalidaException;
    List<TipoFuncion> getTipoFuncionesDisponiblesByPeliculaAndCineId(Long peliculaId, Long cineId);
    List<LocalTime> getHorariosLibresFuncion(Long peliculaId, Long cineId, Long tipoFuncionId, Date fecha) throws TipoFuncionInvalidaException;
    void validarTipoFuncion(Long tipoFuncionId) throws TipoFuncionInvalidaException;
    Funcion getFuncionByConfiguracion(Long peliculaId, Long cineId, Long tipoFuncionId, Date dia, Time hora);
}
