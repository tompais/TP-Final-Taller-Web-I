package ar.edu.unlam.tallerweb1.dao;

import java.util.Date;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Cartelera;

@Repository("carteleraDao")
public class CarteleraDaoImpl implements CarteleraDao{
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public Cartelera consultarCartelera(Date fechaActual) {
		final Session session = sessionFactory.getCurrentSession();
		
		return (Cartelera) session.createCriteria(Cartelera.class)
				.add(Restrictions.le("fechaDesde", fechaActual))
				.add(Restrictions.ge("fechaHasta", fechaActual))
				.uniqueResult();
	}

}
