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
	public List<Pelicula> getPeliculasEstrenadas() {
		final Session session = sessionFactory.getCurrentSession();

		List<Pelicula> peliculas = new ArrayList<>();


		List peliculasBuscadas = session.createCriteria(Pelicula.class)
				.add(Restrictions.le("fechaEstreno", new Date(new java.util.Date().getTime())))
				.list();

		for (Object peliculaBuscada :
			 peliculasBuscadas) {
			peliculas.add((Pelicula) peliculaBuscada);
		}
		return peliculas;
	}
}
