package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.Models.Asiento;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("AsientoDao")
public class AsientoDaoImpl implements AsientoDao{
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public Asiento consultarAsiento(Long asientoId) {
		final Session session = sessionFactory.getCurrentSession();
		
		return (Asiento) session.createCriteria(Asiento.class)
				.add(Restrictions.eq("id", asientoId))
				.uniqueResult();
	}
}
