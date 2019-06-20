package ar.edu.unlam.tallerweb1.servicios;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Asiento;
import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.EstadoAsiento;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.TipoAsiento;
import ar.edu.unlam.tallerweb1.modelo.TipoFuncion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.dao.FuncionDao;
import ar.edu.unlam.tallerweb1.dao.AsientoDao;
import ar.edu.unlam.tallerweb1.dao.EstadoAsientoDao;
import ar.edu.unlam.tallerweb1.dao.TipoAsientoDao;
import ar.edu.unlam.tallerweb1.dao.TipoFuncionDao;
import ar.edu.unlam.tallerweb1.dao.ReservaAsientoDao;
import ar.edu.unlam.tallerweb1.dao.PeliculaDao;
import ar.edu.unlam.tallerweb1.dao.PeliculaCineDao;
import ar.edu.unlam.tallerweb1.modelo.ReservaAsiento;
import ar.edu.unlam.tallerweb1.modelo.PeliculaCine;

@Service("servicioReserva")
@Transactional
public class ServicioReservaImpl implements ServicioReserva{

	@Inject
	private PeliculaDao servicioPeliculaDao;
	
	@Inject
	private PeliculaCineDao servicioPeliculaCineDao;
	
	@Inject
	private FuncionDao servicioFuncionDao;
	
	@Inject
	private AsientoDao servicioAsientoDao;
	
	@Inject
	private EstadoAsientoDao servicioEstadoAsientoDao;
	
	@Inject
	private TipoAsientoDao servicioTipoAsientoDao;
	
	@Inject
	private TipoFuncionDao servicioTipoFuncionDao;
	
	@Inject
	private ReservaAsientoDao servicioReservaAsientoDao;
	
	@Override
	public List<Pelicula> consultarPeliculas(Date actual) {
		return servicioPeliculaDao.consultarPeliculas(actual);
	}	
	
	@Override
	public List<Cine> consultarCinesPelicula(Pelicula pelicula) {
		return servicioPeliculaCineDao.consultarCinesPelicula(pelicula);
	}
	
	@Override
	public List<Funcion> consultarFunciones(PeliculaCine peliculaCine) {
		return servicioFuncionDao.consultarFunciones(peliculaCine);
	}

	@Override
	public Asiento consultaAsiento(Asiento asiento) {
		return servicioAsientoDao.consultarAsiento(asiento);
	}

	
	
	@Override
	public EstadoAsiento consultarEstadoAsiento(EstadoAsiento estadoAsiento) {
		return servicioEstadoAsientoDao.consultarEstadoAsiento(estadoAsiento);
	}

	
	
	@Override
	public void cambiarEstadoAsiento(Asiento asiento) {
		servicioAsientoDao.cambiarEstado(asiento);
	}



	@Override
	public TipoAsiento consultarTipoAsiento(TipoAsiento tipoAsiento) {
		return servicioTipoAsientoDao.consultarTipoAsiento(tipoAsiento);
	}



	@Override
	public TipoFuncion consultarTipoFuncion(TipoFuncion tipoFuncion) {
		return servicioTipoFuncionDao.consultarTipoFuncion(tipoFuncion);
	}



	@Override
	public Integer reservar(Usuario usuario, Funcion funcion, Asiento asiento) {
		Reserva reserva = new Reserva();
		
		reserva.setUsuario(usuario);
		reserva.setFuncion(funcion);
		
		long millis = System.currentTimeMillis();
		java.util.Date fecha = new java.util.Date(millis);
		
		reserva.setFechaCompra(fecha);
		reserva.setNumeroTicket((int)Math.random());
		
		ReservaAsiento reservaAsiento = new ReservaAsiento();
		
		reservaAsiento.setAsiento(asiento);
		reservaAsiento.setReserva(reserva);
		
		if(servicioReservaAsientoDao.realizarReservaAsiento(reservaAsiento))
			return 0;
		
		return reserva.getNumeroTicket();
	}	
	
}
