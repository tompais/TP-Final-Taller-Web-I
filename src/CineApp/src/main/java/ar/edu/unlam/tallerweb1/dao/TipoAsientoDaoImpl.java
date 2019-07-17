package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.Models.TipoAsiento;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("TipoAsientoDao")
public class TipoAsientoDaoImpl implements TipoAsientoDao{
	@Inject
	private SessionFactory sessionFactory;	
	
	@Override
	public TipoAsiento consultarTipoAsiento(TipoAsiento tipoAsiento) {
		final Session session = sessionFactory.getCurrentSession();
		
		return (TipoAsiento) session.createCriteria(TipoAsiento.class)
				.add(Restrictions.eq("id", tipoAsiento.getId()))
				.uniqueResult();
	}

}
