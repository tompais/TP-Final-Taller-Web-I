package ar.edu.unlam.tallerweb1.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ar.edu.unlam.tallerweb1.Enums.EstadoAsiento;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
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

	@Override
	public List<Time> getHorariosLibresFuncion(Long peliculaId, Long cineId, Long tipoFuncionId, Date fecha) {
		List list = sessionFactory.getCurrentSession().createCriteria(AsientoFuncion.class, "asientoFuncion")
				.createAlias("funcion", "funcionBuscada")
				.createAlias("funcionBuscada.pelicula", "peliculaBuscada")
				.createAlias("funcionBuscada.cine", "cineBuscado")
				.createAlias("funcionBuscada.tipoFuncion", "tipoFuncionBuscada")
				.createAlias("estadoAsiento", "estadoAsientoBuscado")
				.add(Restrictions.and(Restrictions.eq("peliculaBuscada.id", peliculaId)
						, Restrictions.eq("cineBuscado.id", cineId)
						, Restrictions.eq("tipoFuncionBuscada.id", tipoFuncionId)
						, Restrictions.eq("funcionBuscada.fecha", fecha)
						, Restrictions.eq("estadoAsientoBuscado.id", EstadoAsiento.LIBRE.getId())))
				.setProjection(Projections.distinct(Projections.property("funcionBuscada.hora")))
				.list();

		List<Time> times = new ArrayList<>();
		for (Object obj :
				list) {
			times.add((Time) obj);
		}

		return times;
	}
}
