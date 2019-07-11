package ar.edu.unlam.tallerweb1.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ar.edu.unlam.tallerweb1.Models.Pelicula;

@Repository("peliculaDao")
public class PeliculaDaoImpl implements PeliculaDao{
	@Inject
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Pelicula> consultarPeliculas(Date fechaActual) {
		final Session session = sessionFactory.getCurrentSession();
		
		return (List<Pelicula>) session.createCriteria(Pelicula.class)
				.add(Restrictions.ge("fechaEstreno", fechaActual))
				.list();
	}

	@Override
	public List<Pelicula> getProximosEstrenos() {

		List<Pelicula> peliculas = new ArrayList<>();

		List list = sessionFactory.getCurrentSession().createCriteria(Pelicula.class, "pelicula")
				.add(Restrictions.between("fechaEstreno", Date.valueOf(new Date(new java.util.Date().getTime()).toLocalDate().plusDays(1)), Date.valueOf(new Date(new java.util.Date().getTime()).toLocalDate().plusDays(1).plusMonths(1))))
				.list();

		for (Object obj:
			 list) {
			peliculas.add((Pelicula) obj);
		}

		return peliculas;
	}

	@Override
	public Pelicula getPeliculaById(Long peliculaId) {
		return (Pelicula) sessionFactory.getCurrentSession().createCriteria(Pelicula.class, "pelicula")
				.add(Restrictions.eq("id", peliculaId))
				.uniqueResult();
	}
}
