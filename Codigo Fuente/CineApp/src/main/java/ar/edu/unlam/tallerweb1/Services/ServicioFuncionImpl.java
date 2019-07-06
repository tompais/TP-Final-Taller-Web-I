package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.dao.FuncionDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.Timestamp;

@Service("servicioFuncion")
@Transactional
public class ServicioFuncionImpl implements ServicioFuncion {

    @Inject
    private FuncionDao funcionDao;

    @Override
    public Timestamp getFechaUltimaFuncionByPeliculaAndCineId(Long peliculaId, Long cineId) {
        return funcionDao.getFechaUltimaFuncionByPeliculaAndCineId(peliculaId, cineId);
    }
}
