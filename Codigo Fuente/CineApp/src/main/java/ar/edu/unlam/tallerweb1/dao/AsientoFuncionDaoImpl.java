package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.Models.AsientoFuncion;
import ar.edu.unlam.tallerweb1.Models.Funcion;

@Repository("AsientoFuncionDao")
public class AsientoFuncionDaoImpl implements AsientoFuncionDao{
	@Inject
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AsientoFuncion> consultarAsientoFuncion(Funcion funcion) {
		final Session session = sessionFactory.getCurrentSession();
		
		return (List<AsientoFuncion>) session.createCriteria(AsientoFuncion.class)
				.add(Restrictions.eq("funcion", funcion))
				.list();
	}
}
