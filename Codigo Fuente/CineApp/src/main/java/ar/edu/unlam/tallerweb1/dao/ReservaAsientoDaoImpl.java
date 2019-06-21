package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.Models.ReservaAsiento;

@Repository("reservaAsientoDao")
public class ReservaAsientoDaoImpl implements ReservaAsientoDao{
	@Inject
	private SessionFactory sessionFactory;

	@Override
	public Boolean realizarReservaAsiento(ReservaAsiento reservaAsiento) {
		final Session session = sessionFactory.getCurrentSession();
		
		session.save(reservaAsiento);
		
		return true;
	}

}
