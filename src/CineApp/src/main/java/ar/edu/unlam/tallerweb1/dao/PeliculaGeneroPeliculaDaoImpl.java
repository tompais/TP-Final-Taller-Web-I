package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.Models.GeneroPelicula;
import ar.edu.unlam.tallerweb1.Models.Pelicula;
import ar.edu.unlam.tallerweb1.Models.PeliculaGeneroPelicula;

@Repository("PeliculaGeneroPeliculaDao")
public class PeliculaGeneroPeliculaDaoImpl implements PeliculaGeneroPeliculaDao{
	@Inject
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<GeneroPelicula> consultarGeneroPelis(List<Pelicula> peliculas) {
		final Session session = sessionFactory.getCurrentSession();
		
		return session.createCriteria(PeliculaGeneroPelicula.class)
				.add(Restrictions.in("pelicula", peliculas))
				.setProjection(Projections.groupProperty("generoPelicula")).list();
				
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pelicula> consultarPelisRecomendadas(List<Pelicula> peliculas, List<GeneroPelicula> generoPelis) {
		final Session session = sessionFactory.getCurrentSession();
		
		return session.createCriteria(PeliculaGeneroPelicula.class)
				.add(Restrictions.in("generoPelicula", generoPelis))
				.add(Restrictions.not(Restrictions.in("pelicula", peliculas)))
				.setProjection(Projections.property("pelicula")).setMaxResults(4).list();
				
	}
}
