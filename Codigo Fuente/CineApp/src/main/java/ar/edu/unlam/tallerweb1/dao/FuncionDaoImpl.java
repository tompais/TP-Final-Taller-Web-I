package ar.edu.unlam.tallerweb1.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.Models.Funcion;
import ar.edu.unlam.tallerweb1.Models.PeliculaCine;

@Repository("funcionDao")
public class FuncionDaoImpl implements FuncionDao{
	@Inject
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Funcion> consultarFunciones(PeliculaCine peliculaCine) {
		final Session session = sessionFactory.getCurrentSession();
		
		return (List<Funcion>) session.createCriteria(Funcion.class)
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

	@Override
	public Timestamp getFechaUltimaFuncionByPeliculaAndCineId(Long peliculaId, Long cineId) {
		return (Timestamp) sessionFactory.getCurrentSession().createCriteria(Funcion.class, "funcion")
				.createAlias("pelicula", "peliculaBuscada")
				.createAlias("cine", "cineBuscado")
				.add(Restrictions.and(Restrictions.eq("peliculaBuscada.id", peliculaId), Restrictions.eq("cineBuscado.id", cineId)))
				.addOrder(Order.desc("diaYHora"))
				.setProjection(Projections.property("diaYHora"))
		.setFirstResult(0)
		.uniqueResult();
	}

}
