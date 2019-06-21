package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.Models.Sala;

@Repository("salaDao")
public class SalaDaoImpl implements SalaDao{
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public Sala cosultarSala(Sala sala) {
		final Session session = sessionFactory.getCurrentSession();
		
		return (Sala) session.createCriteria(Sala.class)
				.add(Restrictions.eq("id", sala.getId()))
				.uniqueResult();
	}

}
