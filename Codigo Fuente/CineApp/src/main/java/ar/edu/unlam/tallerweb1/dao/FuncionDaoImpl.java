package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Cartelera;

@Repository("funcionDao")
public class FuncionDaoImpl implements FuncionDao{
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public List<Funcion> consultarFuncionesPelicula(Pelicula pelicula, Cartelera cartelera) {
		final Session session = sessionFactory.getCurrentSession();
		
		return session.createCriteria(Funcion.class)
				.add(Restrictions.eq("pelicula", pelicula))
				.add(Restrictions.eq("cartelera", cartelera)).list();
	}
	
	@Override
	public List<Funcion> consultarFunciones(Cartelera cartelera) {
		final Session session = sessionFactory.getCurrentSession();
		
		return session.createCriteria(Funcion.class)
				.add(Restrictions.eq("cartelera", cartelera)).list();
	}

	@Override
	public Funcion consultarFuncion(Funcion funcion) {
		final Session session = sessionFactory.getCurrentSession();
		
		return (Funcion) session.createCriteria(Funcion.class)
				.add(Restrictions.eq("id", funcion.getId()))
				.uniqueResult();
	}

}
