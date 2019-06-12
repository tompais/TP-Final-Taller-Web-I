package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;

@Repository("peliculaDao")
public class PeliculaDaoImpl implements PeliculaDao{
	@Inject
	private SessionFactory sessionFactory;

	@Override
	public Pelicula consultarPelicula(Pelicula pelicula) {
		final Session session = sessionFactory.getCurrentSession();
		
		return (Pelicula) session.createCriteria(Pelicula.class)
				.add(Restrictions.eq("id", pelicula.getId()))
				.uniqueResult();
	}
}
