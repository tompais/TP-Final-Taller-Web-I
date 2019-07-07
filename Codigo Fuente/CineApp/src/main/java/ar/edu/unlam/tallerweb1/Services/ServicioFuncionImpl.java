package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;
import ar.edu.unlam.tallerweb1.Enums.TipoFuncion;
import ar.edu.unlam.tallerweb1.Exceptions.FechaUltimaFuncionMenorAFechaActualException;
import ar.edu.unlam.tallerweb1.Exceptions.TipoFuncionInvalidaException;
import ar.edu.unlam.tallerweb1.Models.Funcion;
import ar.edu.unlam.tallerweb1.dao.AsientoFuncionDao;
import ar.edu.unlam.tallerweb1.dao.FuncionDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service("servicioFuncion")
@Transactional
public class ServicioFuncionImpl implements ServicioFuncion {

    @Inject
    private FuncionDao funcionDao;

    @Inject
    private AsientoFuncionDao asientoFuncionDao;

    @Override
    public Date getFechaUltimaFuncionByPeliculaCineAndTipoFuncionId(Long peliculaId, Long cineId, Long tipoFuncionId) throws FechaUltimaFuncionMenorAFechaActualException, TipoFuncionInvalidaException {

        validarTipoFuncion(tipoFuncionId);

        Date maxDate = funcionDao.getFechaUltimaFuncionByPeliculaCineAndTipoFuncionId(peliculaId, cineId, tipoFuncionId);

        if (Period.between(LocalDate.now(), maxDate.toLocalDate()).isNegative())
            throw new FechaUltimaFuncionMenorAFechaActualException("La fecha de la última función es menor a la fecha actual", CodigoError.FECHAULTIMAFUNCIONMENORAFECHAACTUAL);

        return maxDate;
    }

    @Override
    public List<ar.edu.unlam.tallerweb1.Models.TipoFuncion> getTipoFuncionesDisponiblesByPeliculaAndCineId(Long peliculaId, Long cineId) {
        return funcionDao.getTipoFuncionesDisponiblesByPeliculaAndCineId(peliculaId, cineId);
    }

    @Override
    public List<LocalTime> getHorariosLibresFuncion(Long peliculaId, Long cineId, Long tipoFuncionId, Date fecha) throws TipoFuncionInvalidaException {

        validarTipoFuncion(tipoFuncionId);

        List<Time> times = asientoFuncionDao.getHorariosLibresFuncion(peliculaId, cineId, tipoFuncionId, fecha);

        List<LocalTime> localTimes = new ArrayList<>();
        for (Time time :
             times) {
            localTimes.add(time.toLocalTime());
        }

        return localTimes;
    }

    @Override
    public void validarTipoFuncion(Long tipoFuncionId) throws TipoFuncionInvalidaException {
        if (!TipoFuncion.DOSD.getId().equals(tipoFuncionId) && !TipoFuncion.TRESD.getId().equals(tipoFuncionId) && !TipoFuncion.CUATROD.getId().equals(tipoFuncionId))
            throw new TipoFuncionInvalidaException("No existe un tipo de función con id " + tipoFuncionId, CodigoError.TIPOFUNCIONINVALIDA);
    }

    @Override
    public Funcion getFuncionByConfiguracion(Long peliculaId, Long cineId, Long tipoFuncionId, Date dia, Time hora) {
        return funcionDao.getFuncionByConfiguracion(peliculaId, cineId, tipoFuncionId, dia, hora);
    }
}
