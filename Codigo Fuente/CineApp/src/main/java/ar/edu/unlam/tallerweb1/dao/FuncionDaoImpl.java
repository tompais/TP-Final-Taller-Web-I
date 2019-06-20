package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelos.Funcion;
import ar.edu.unlam.tallerweb1.modelos.PeliculaCine;

@Repository("funcionDao")
public class FuncionDaoImpl implements FuncionDao{
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public List<Funcion> consultarFunciones(PeliculaCine peliculaCine) {
		final Session session = sessionFactory.getCurrentSession();
		
		return session.createCriteria(Funcion.class)
				.add(Restrictions.eq("pelicula", peliculaCine.getPelicula()))
				.add(Restrictions.eq("cine", peliculaCine.getCine()))
				.list();
	}

	@Override
	public Funcion consultarFuncion(Funcion funcion) {
		final Session session = sessionFactory.getCurrentSession();
		
		return (Funcion) session.createCriteria(Funcion.class)
				.add(Restrictions.eq("id", funcion.getId()))
				.uniqueResult();
	}

}
