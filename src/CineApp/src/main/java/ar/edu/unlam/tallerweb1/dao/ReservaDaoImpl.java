package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

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

}
