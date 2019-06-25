package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.dao.PeliculaUsuarioDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service("servicioCalificacion")
@Transactional
public class ServicioCalificacionImpl implements ServicioCalificacion {

    @Inject
    private PeliculaUsuarioDao peliculaUsuarioDao;

    @Override
    public Double getPromedioCalificacionByPeliculaId(Long id) {
        return peliculaUsuarioDao.getPromedioCalificacionByPeliculaId(id);
    }
}
