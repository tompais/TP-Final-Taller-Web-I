package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelos.Cine;
import ar.edu.unlam.tallerweb1.modelos.Pelicula;

@Repository("peliculaCineDao")
public class PeliculaCineDaoImpl implements PeliculaCineDao{
	@Inject
    private SessionFactory sessionFactory;
	
	@Override
	public List<Cine> consultarCinesPelicula(Pelicula pelicula) {
		final Session session = sessionFactory.getCurrentSession();
		
		return session.createCriteria(Cine.class)
				.add(Restrictions.eq("peliculaId", pelicula.getId()))
				.list();
	}

}
