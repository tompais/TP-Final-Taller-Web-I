package ar.edu.unlam.tallerweb1.Services;

import java.sql.Date;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.Helpers.ConstanteHelper;
import ar.edu.unlam.tallerweb1.Models.Asiento;
import ar.edu.unlam.tallerweb1.Models.AsientoFuncion;
import ar.edu.unlam.tallerweb1.Models.EstadoAsiento;
import ar.edu.unlam.tallerweb1.Models.Funcion;
import ar.edu.unlam.tallerweb1.Models.Pelicula;
import ar.edu.unlam.tallerweb1.Models.TipoAsiento;
import ar.edu.unlam.tallerweb1.Models.TipoFuncion;
import ar.edu.unlam.tallerweb1.Models.Usuario;
import ar.edu.unlam.tallerweb1.ViewModels.SalaViewModel;
import ar.edu.unlam.tallerweb1.Models.Reserva;
import ar.edu.unlam.tallerweb1.dao.FuncionDao;
import ar.edu.unlam.tallerweb1.dao.AsientoDao;
import ar.edu.unlam.tallerweb1.dao.AsientoFuncionDao;
import ar.edu.unlam.tallerweb1.dao.EstadoAsientoDao;
import ar.edu.unlam.tallerweb1.dao.TipoAsientoDao;
import ar.edu.unlam.tallerweb1.dao.TipoFuncionDao;
import ar.edu.unlam.tallerweb1.dao.ReservaAsientoDao;
import ar.edu.unlam.tallerweb1.dao.ReservaDao;
import ar.edu.unlam.tallerweb1.dao.SalaDao;
import ar.edu.unlam.tallerweb1.dao.PeliculaDao;
import ar.edu.unlam.tallerweb1.dao.PeliculaCineDao;
import ar.edu.unlam.tallerweb1.Models.ReservaAsiento;
import ar.edu.unlam.tallerweb1.Models.Sala;
import ar.edu.unlam.tallerweb1.Models.PeliculaCine;

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
	
	@Inject
	private ReservaDao servicioReservaDao;
	
	@Inject
	private SalaDao servicioSalaDao;
	
	@Inject
	private AsientoFuncionDao asientoFuncionDao;
	
	@Override
	public List<Pelicula> consultarPeliculas(Date actual) {
		return servicioPeliculaDao.consultarPeliculas(actual);
	}	
	
	@Override
	public List<PeliculaCine> consultarCinesPelicula(Pelicula pelicula) {
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
		
		Random random = new Random();
		
		reserva.setNumeroTicket(random.nextInt(5000) + 1);
		
		ReservaAsiento reservaAsiento = new ReservaAsiento();
		
		reservaAsiento.setAsiento(asiento);
		reservaAsiento.setReserva(reserva);
		
		servicioReservaDao.realizarReserva(reserva);
		
		servicioReservaAsientoDao.realizarReservaAsiento(reservaAsiento);
		
		return reserva.getNumeroTicket();
	}

	@Override
	public Sala consultarSala(Sala sala) {
		return servicioSalaDao.cosultarSala(sala);
	}
	
	@Override
	public Funcion consultarFuncion(Funcion funcion) {
		return servicioFuncionDao.consultarFuncion(funcion);
	}
	
	@Override
	public SalaViewModel[][] formatoSala(Funcion funcion, int fil, int col) {
		List<AsientoFuncion> asientosFuncion = asientoFuncionDao.consultarAsientoFuncion(funcion);
		
		SalaViewModel[][] sala = new SalaViewModel[fil][col];
		
		//Arrays.fill(sala, 0); //inicializo todas las posiciones de la matriz en null
		
		SalaViewModel modelo;
		
		for(AsientoFuncion asientoFuncion : asientosFuncion) {
			modelo = new SalaViewModel();
			
			modelo.setEstadoAsiento(asientoFuncion.getEstadoAsiento().getId());
			modelo.setTipoAsiento(asientoFuncion.getAsiento().getTipoAsiento().getId());
			modelo.setColumna(ConstanteHelper.ABECEDARIO.charAt(asientoFuncion.getAsiento().getColumna() - 1));
			
			sala[asientoFuncion.getAsiento().getFila() - 1][asientoFuncion.getAsiento().getColumna() - 1] = modelo;
			
			modelo = null;
		}
		
		return sala;
	}
	
}
