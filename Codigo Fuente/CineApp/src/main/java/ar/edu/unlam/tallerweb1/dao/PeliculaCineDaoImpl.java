package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ar.edu.unlam.tallerweb1.Models.Pelicula;
import ar.edu.unlam.tallerweb1.Models.PeliculaCine;

@Repository("peliculaCineDao")
public class PeliculaCineDaoImpl implements PeliculaCineDao{
	@Inject
    private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PeliculaCine> consultarCinesPelicula(Pelicula pelicula) {
		final Session session = sessionFactory.getCurrentSession();
		
		return (List<PeliculaCine>) session.createCriteria(PeliculaCine.class)
				.add(Restrictions.eq("pelicula", pelicula))
				.list();
	}

}
