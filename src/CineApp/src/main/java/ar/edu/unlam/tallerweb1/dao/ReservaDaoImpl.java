package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.Models.Funcion;
import ar.edu.unlam.tallerweb1.Models.Reserva;

@Repository("reservaDao")
public class ReservaDaoImpl implements ReservaDao{
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public Boolean realizarReserva(Reserva reserva) {
		final Session session = sessionFactory.getCurrentSession();
		
		session.save(reserva);
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Funcion> consultarReservasUsuario(Long usuarioId) {
		final Session session = sessionFactory.getCurrentSession();
		
		return session.createCriteria(Reserva.class)
				.createAlias("usuario", "usuarioBuscado")
				.add(Restrictions.eq("usuarioBuscado.id", usuarioId))
				.setProjection(Projections.groupProperty("funcion")).list();
	}

}
