package ar.edu.unlam.tallerweb1.Services;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.Models.GeneroPelicula;
import ar.edu.unlam.tallerweb1.Models.Pelicula;
import ar.edu.unlam.tallerweb1.dao.PeliculaGeneroPeliculaDao;

@Service("servicioPeliculaGeneroPelicula")
@Transactional
public class ServicioPeliculaGeneroPeliculaImpl implements ServicioPeliculaGeneroPelicula{
	@Inject
	private PeliculaGeneroPeliculaDao peliculaGeneroPeliculaDao;
	
	@Override
	public List<GeneroPelicula> consultarGeneroPelis(List<Pelicula> peliculas){
		return peliculaGeneroPeliculaDao.consultarGeneroPelis(peliculas);
	}
	
	@Override
	public List<Pelicula> consultarPelisRecomendadas(List<Pelicula> peliculas, List<GeneroPelicula> generoPelis){
		return peliculaGeneroPeliculaDao.consultarPelisRecomendadas(peliculas, generoPelis);
	}
}
