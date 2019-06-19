package ar.edu.unlam.tallerweb1.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
	public List<Pelicula> consultarPeliculas(LocalDate actual) {
		final Session session = sessionFactory.getCurrentSession();
		
		return session.createCriteria(Pelicula.class)
				.add(Restrictions.ge("fechaEstreno", actual))
				.list();
	}
}
