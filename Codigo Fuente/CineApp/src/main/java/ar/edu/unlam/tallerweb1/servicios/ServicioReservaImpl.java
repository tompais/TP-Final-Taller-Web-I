package ar.edu.unlam.tallerweb1.servicios;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Asiento;
import ar.edu.unlam.tallerweb1.modelo.EstadoAsiento;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Sala;
import ar.edu.unlam.tallerweb1.modelo.TipoAsiento;
import ar.edu.unlam.tallerweb1.modelo.TipoFuncion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Reserva;
import ar.edu.unlam.tallerweb1.dao.FuncionDao;
import ar.edu.unlam.tallerweb1.dao.SalaDao;
import ar.edu.unlam.tallerweb1.dao.AsientoDao;
import ar.edu.unlam.tallerweb1.dao.EstadoAsientoDao;
import ar.edu.unlam.tallerweb1.dao.TipoAsientoDao;
import ar.edu.unlam.tallerweb1.dao.TipoFuncionDao;
import ar.edu.unlam.tallerweb1.dao.ReservaDao;

@Service("servicioReserva")
@Transactional
public class ServicioReservaImpl implements ServicioReserva{

	@Inject
	private FuncionDao servicioFuncionDao;
	
	@Inject
	private SalaDao servicioSalaDao;
	
	@Inject
	private AsientoDao servicioAsientoDao;
	
	@Inject
	private EstadoAsientoDao servicioEstadoAsientoDao;
	
	@Inject
	private TipoAsientoDao servicioTipoAsientoDao;
	
	@Inject
	private TipoFuncionDao servicioTipoFuncionDao;
	
	@Inject
	private ReservaDao servicioReservaDao;
	
	@Override
	public Funcion consultarFuncion(Funcion funcion) {
		return servicioFuncionDao.consultarFuncion(funcion);
	}



	@Override
	public Sala consultarSala(Sala sala) {
		return servicioSalaDao.cosultarSala(sala);
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
	public void cambiarEstadoAsiento(EstadoAsiento estadoAsiento) {
		servicioAsientoDao.cambiarEstado(estadoAsiento);
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
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		reserva.setFechaCompra(formatter.format(date));
		reserva.setNumeroTicket((int)Math.random());
		
		if(servicioReservaDao.realizarReserva(reserva))
			return 0;
		
		return reserva.getNumeroTicket();
	}	
	
	
}
