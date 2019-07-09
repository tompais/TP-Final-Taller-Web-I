package ar.edu.unlam.tallerweb1.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ar.edu.unlam.tallerweb1.Models.TipoFuncion;
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
	public Funcion consultarFuncionById(Long funcionId) {
		final Session session = sessionFactory.getCurrentSession();
		
		return (Funcion) session.createCriteria(Funcion.class)
				.add(Restrictions.eq("id", funcionId))
				.uniqueResult();
	}

	@Override
	public Date getFechaUltimaFuncionByPeliculaCineAndTipoFuncionId(Long peliculaId, Long cineId, Long tipoFuncionId) {
		return (Date) sessionFactory.getCurrentSession().createCriteria(Funcion.class, "funcion")
				.createAlias("pelicula", "peliculaBuscada")
				.createAlias("cine", "cineBuscado")
				.createAlias("tipoFuncion", "tipoFuncionBuscada")
				.add(Restrictions.and(Restrictions.eq("peliculaBuscada.id", peliculaId), Restrictions.eq("cineBuscado.id", cineId), Restrictions.eq("tipoFuncionBuscada.id", tipoFuncionId)))
				.addOrder(Order.desc("fecha"))
				.setProjection(Projections.property("fecha"))
		.setFirstResult(0)
		.uniqueResult();
	}

	@Override
	public List<TipoFuncion> getTipoFuncionesDisponiblesByPeliculaAndCineId(Long peliculaId, Long cineId) {
		List list = sessionFactory.getCurrentSession().createCriteria(Funcion.class, "funcion")
				.createAlias("pelicula", "peliculaBuscada")
				.createAlias("cine", "cineBuscado")
				.createAlias("tipoFuncion", "tipoFuncionBuscada")
				.add(Restrictions.and(Restrictions.eq("peliculaBuscada.id", peliculaId), Restrictions.eq("cineBuscado.id", cineId)))
				.setProjection(Projections.distinct(Projections.property("tipoFuncion")))
				.list();

		List<TipoFuncion> tipoFunciones = new ArrayList<>();
		for (Object obj :
				list) {
			tipoFunciones.add((TipoFuncion) obj);
		}

		return tipoFunciones;
	}

	@Override
	public Funcion getFuncionByConfiguracion(Long peliculaId, Long cineId, Long tipoFuncionId, Date dia, Time hora) {
		return (Funcion) sessionFactory.getCurrentSession().createCriteria(Funcion.class, "funcion")
				.createAlias("pelicula", "peliculaBuscada")
				.createAlias("cine", "cineBuscado")
				.createAlias("tipoFuncion", "tipoFuncionBuscada")
				.add(Restrictions.and(Restrictions.eq("peliculaBuscada.id", peliculaId)
						, Restrictions.eq("cineBuscado.id", cineId)
						, Restrictions.eq("tipoFuncionBuscada.id", tipoFuncionId)
						, Restrictions.eq("fecha", dia)
						, Restrictions.eq("hora", hora)))
				.uniqueResult();
	}

}
