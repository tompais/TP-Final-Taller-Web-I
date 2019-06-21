package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.Models.Cine;

@Repository("cineDao")
public class CineDaoImpl implements CineDao{
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public Cine consultarCine(Cine cine) {
		final Session session = sessionFactory.getCurrentSession();

		return (Cine) session.createCriteria(Cine.class)
				.add(Restrictions.eq("id", cine.getId()))
				.uniqueResult();
	}

}
