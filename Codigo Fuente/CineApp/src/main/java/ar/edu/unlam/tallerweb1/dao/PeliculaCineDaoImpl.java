package ar.edu.unlam.tallerweb1.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import ar.edu.unlam.tallerweb1.Models.Pelicula;
import ar.edu.unlam.tallerweb1.Models.PeliculaCine;

@Repository("peliculaCineDao")
public class PeliculaCineDaoImpl implements PeliculaCineDao{
	@Inject
    private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PeliculaCine> consultarCinesPelicula(Pelicula pelicula) {
		final Session session = sessionFactory.getCurrentSession();
		
		return (List<PeliculaCine>) session.createCriteria(PeliculaCine.class)
				.add(Restrictions.eq("pelicula", pelicula))
				.list();
	}

	@Override
	public List<Pelicula> consultarPeliculasDisponiblesEnCartelera() {
		final Session session = sessionFactory.getCurrentSession();

		List list = session.createCriteria(PeliculaCine.class, "peliculaCine")
				.createAlias("pelicula", "peliculaBuscada")
				.add(Restrictions.isNull("fechaBaja"))
				.add(Restrictions.le("peliculaBuscada.fechaEstreno", new Date(new java.util.Date().getTime())))
				.setProjection(Projections.projectionList()
				.add(Projections.distinct(Projections.property("pelicula"))))
				.list();

		List<Pelicula> peliculas = new ArrayList<>();
		for (Object item :
				list) {
			peliculas.add((Pelicula) item);
		}

		return peliculas;
	}

}
