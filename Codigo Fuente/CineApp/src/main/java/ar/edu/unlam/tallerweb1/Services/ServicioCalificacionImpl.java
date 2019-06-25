package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.dao.PeliculaUsuarioDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service("servicioCalificacion")
@Transactional
public class ServicioCalificacionImpl implements ServicioCalificacion {

    @Inject
    private PeliculaUsuarioDao peliculaUsuarioDao;

    @Override
    public Double getPromedioCalificacionByPeliculaId(Long id) {
        Double promedioCalificacion = peliculaUsuarioDao.getPromedioCalificacionByPeliculaId(id);

        return promedioCalificacion == null ? 0 : new BigDecimal(promedioCalificacion).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }
}
