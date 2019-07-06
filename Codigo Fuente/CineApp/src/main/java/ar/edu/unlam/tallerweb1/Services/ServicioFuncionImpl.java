package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.Models.TipoFuncion;
import ar.edu.unlam.tallerweb1.dao.FuncionDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.Date;
import java.util.List;

@Service("servicioFuncion")
@Transactional
public class ServicioFuncionImpl implements ServicioFuncion {

    @Inject
    private FuncionDao funcionDao;

    @Override
    public Date getFechaUltimaFuncionByPeliculaCineAndTipoFuncionId(Long peliculaId, Long cineId, Long tipoFuncionId) {
        return funcionDao.getFechaUltimaFuncionByPeliculaCineAndTipoFuncionId(peliculaId, cineId, tipoFuncionId);
    }

    @Override
    public List<TipoFuncion> getTipoFuncionesDisponiblesByPeliculaAndCineId(Long peliculaId, Long cineId) {
        return funcionDao.getTipoFuncionesDisponiblesByPeliculaAndCineId(peliculaId, cineId);
    }
}
