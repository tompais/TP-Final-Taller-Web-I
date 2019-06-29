package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.Models.Pelicula;
import ar.edu.unlam.tallerweb1.dao.PeliculaCineDao;
import ar.edu.unlam.tallerweb1.dao.PeliculaDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;

@Service("servicioPelicula")
@Transactional
public class ServicioPeliculaImpl implements ServicioPelicula {

    @Inject
    private PeliculaDao peliculaDao;

    @Inject
    private PeliculaCineDao peliculaCineDao;

    @Override
    public List<Pelicula> getPeliculasDisponiblesEnCartelera() {
        return peliculaCineDao.consultarPeliculasDisponiblesEnCartelera();
    }

    @Override
    public List<Pelicula> getProximosEstrenos() {
        List<Pelicula> proximosEstrenos = peliculaDao.getProximosEstrenos();

        Comparator<Pelicula> compareByFechaEstreno = Comparator.comparing(Pelicula::getFechaEstreno);

        proximosEstrenos.sort(compareByFechaEstreno);

        return peliculaDao.getProximosEstrenos();
    }
}
