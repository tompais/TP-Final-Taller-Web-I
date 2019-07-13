package ar.edu.unlam.tallerweb1.Services;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;
import ar.edu.unlam.tallerweb1.Enums.EstadoAsiento;
import ar.edu.unlam.tallerweb1.Exceptions.FuncionInvalidaException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.Helpers.ConstanteHelper;
import ar.edu.unlam.tallerweb1.Helpers.TokenHelper;
import ar.edu.unlam.tallerweb1.Models.Asiento;
import ar.edu.unlam.tallerweb1.Models.AsientoFuncion;
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
import ar.edu.unlam.tallerweb1.dao.ReservaDao;
import ar.edu.unlam.tallerweb1.dao.SalaDao;
import ar.edu.unlam.tallerweb1.dao.PeliculaDao;
import ar.edu.unlam.tallerweb1.dao.PeliculaCineDao;
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
	private FuncionDao funcionDao;
	
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
	
	@Inject
	private SalaDao servicioSalaDao;
	
	@Inject
	private AsientoFuncionDao asientoFuncionDao;
	
	public FuncionDao getFuncionDao() {
		return funcionDao;
	}

	public void setFuncionDao(FuncionDao funcionDao) {
		this.funcionDao = funcionDao;
	}

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
		return funcionDao.consultarFunciones(peliculaCine);
	}

	@Override
	public Asiento consultaAsiento(Long asientoId) {
		return servicioAsientoDao.consultarAsiento(asientoId);
	}

	
	
	@Override
	public ar.edu.unlam.tallerweb1.Models.EstadoAsiento consultarEstadoAsiento(ar.edu.unlam.tallerweb1.Models.EstadoAsiento estadoAsiento) {
		return servicioEstadoAsientoDao.consultarEstadoAsiento(estadoAsiento);
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
	public String reservar(Usuario usuario, Long funcionId, Long[] asientos) {
		Reserva reserva = new Reserva();
		
		reserva.setUsuario(usuario);
		
		ar.edu.unlam.tallerweb1.Models.EstadoAsiento estadoAsiento = new ar.edu.unlam.tallerweb1.Models.EstadoAsiento();
		estadoAsiento.setId(EstadoAsiento.OCUPADO.getId());

		long millis = System.currentTimeMillis();
		java.util.Date fecha = new java.util.Date(millis);
		
		reserva.setFechaCompra(fecha);
		
		reserva.setNumeroTicket(TokenHelper.getSecureRandomString(10));
		
		reserva.setFuncion(funcionDao.consultarFuncionById(funcionId));
		
		for(Long asiento : asientos) {
			AsientoFuncion asientoFuncion = asientoFuncionDao.consultarAsientoFuncion(funcionId, asiento);
			
			asientoFuncion.setEstadoAsiento(estadoAsiento);
			
			asientoFuncionDao.cambiarEstadoAsiento(asientoFuncion);
			
			reserva.setAsiento(asientoFuncion.getAsiento());
			
			servicioReservaDao.realizarReserva(reserva);
		}
		
		return reserva.getNumeroTicket();
	}

	@Override
	public Sala consultarSala(Sala sala) {
		return servicioSalaDao.cosultarSala(sala);
	}
	
	@Override
	public Funcion consultarFuncionById(Long funcionId) throws FuncionInvalidaException {
		Funcion funcion = funcionDao.consultarFuncionById(funcionId);
		if(funcion == null)
			throw new FuncionInvalidaException("No se ha encontrado una función con el id " + funcionId, CodigoError.FUNCIONINVALIDA);
		return funcion;
	}
	
	
	
	@Override
	public SalaViewModel[][] formatoSala(Long funcionId, int fil, int col) {
		List<AsientoFuncion> asientosFuncion = asientoFuncionDao.consultarDistribucionAsientosEnFuncion(funcionId);
		
		SalaViewModel[][] sala = new SalaViewModel[fil][col];
		
		int cont = 0;
		
		for(AsientoFuncion asientoFuncion : asientosFuncion) {
			
			if(asientoFuncion.getAsiento().getColumna() - 1 == 0)
				cont = 0;

			SalaViewModel modelo = new SalaViewModel();
			
			modelo.setId(asientoFuncion.getAsiento().getId());
			modelo.setEstadoAsientoId(asientoFuncion.getEstadoAsiento().getId());
			modelo.setTipoAsientoId(asientoFuncion.getAsiento().getTipoAsiento().getId());
			
			modelo.setColumna(ConstanteHelper.ABECEDARIO.charAt(cont++));
			
			sala[asientoFuncion.getAsiento().getFila() - 1][asientoFuncion.getAsiento().getColumna() - 1] = modelo;
		}
		
		return sala;
	}
	
}
