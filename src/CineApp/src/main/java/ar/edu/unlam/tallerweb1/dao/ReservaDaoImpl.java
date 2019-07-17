package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
<<<<<<< HEAD
import org.hibernate.criterion.Projections;
=======
>>>>>>> be824af473b7ed9a09cde82b906a91fce6fd858a
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.Models.Funcion;
import ar.edu.unlam.tallerweb1.Models.Reserva;

import java.util.ArrayList;
import java.util.List;

@Repository("reservaDao")
public class ReservaDaoImpl implements ReservaDao{
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public void realizarReserva(Reserva reserva) {
		final Session session = sessionFactory.getCurrentSession();
		
		session.save(reserva);

    }

	@Override
	public List<Reserva> getReservasByNumeroTicket(String numeroTicket) {
		List list = sessionFactory.getCurrentSession().createCriteria(Reserva.class, "reserva")
				.add(Restrictions.ilike("numeroTicket", numeroTicket))
				.list();

		List<Reserva> reservas = new ArrayList<>();
		for (Object obj :
				list) {
			reservas.add((Reserva) obj);
		}

		return reservas;
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
