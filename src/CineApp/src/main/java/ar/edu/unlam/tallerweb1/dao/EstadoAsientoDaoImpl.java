package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.Models.EstadoAsiento;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("EstadoAsientoDao")
public class EstadoAsientoDaoImpl implements EstadoAsientoDao{
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public EstadoAsiento consultarEstadoAsiento(EstadoAsiento estadoAsiento) {
		final Session session = sessionFactory.getCurrentSession();
		
		return (EstadoAsiento) session.createCriteria(EstadoAsiento.class)
				.add(Restrictions.eq("id", estadoAsiento.getId()))
				.uniqueResult();
	}

	@Override
	public EstadoAsiento getEstadoAsientoById(Long estadoAsientoId) {
		return (EstadoAsiento) sessionFactory.getCurrentSession().createCriteria(EstadoAsiento.class, "estadoAsiento")
				.add(Restrictions.eq("id", estadoAsientoId))
				.uniqueResult();
	}

	@Override
	public void cambiarEstadoAsiento(EstadoAsiento estadoAsiento) {
		final Session session = sessionFactory.getCurrentSession();
		
		session.update(estadoAsiento);
	}

}
