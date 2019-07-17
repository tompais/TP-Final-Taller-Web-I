package ar.edu.unlam.tallerweb1.Services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;
import ar.edu.unlam.tallerweb1.Enums.EstadoAsiento;
import ar.edu.unlam.tallerweb1.Exceptions.*;
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
import ar.edu.unlam.tallerweb1.ViewModels.ReservaViewModel;
import ar.edu.unlam.tallerweb1.ViewModels.AsientoViewModel;
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
public class ServicioReservaImpl implements ServicioReserva {

    @Inject
    private PeliculaDao servicioPeliculaDao;

    @Inject
    private PeliculaCineDao servicioPeliculaCineDao;

    @Inject
    private FuncionDao funcionDao;

    @Inject
    private AsientoDao servicioAsientoDao;

    @Inject
    private EstadoAsientoDao estadoAsientoDao;

    @Inject
    private TipoAsientoDao servicioTipoAsientoDao;

    @Inject
    private TipoFuncionDao servicioTipoFuncionDao;

    @Inject
    private ReservaDao reservaDao;

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
        return estadoAsientoDao.consultarEstadoAsiento(estadoAsiento);
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
    public String reservar(Usuario usuario, ReservaViewModel reservaViewModel) {
        
    	long millis = System.currentTimeMillis();
        java.util.Date fecha = new java.util.Date(millis);

        String ticket = TokenHelper.getSecureRandomString(10).toUpperCase();

        for (int i = 0; i < reservaViewModel.getFilas().length; i++) {
        	Reserva reserva = new Reserva();

            reserva.setUsuario(usuario);
            
            reserva.setFechaCompra(fecha);
            
            reserva.setNumeroTicket(ticket);
            
            reserva.setFuncion(funcionDao.consultarFuncionById(reservaViewModel.getFuncionId()));
        	
        	AsientoFuncion asientoFuncion = asientoFuncionDao.getAsientoFuncionByFuncionIdAndPosicion(reservaViewModel.getFuncionId(), reservaViewModel.getFilas()[i], reservaViewModel.getColumnas()[i]);

            reserva.setAsiento(asientoFuncion.getAsiento());

            reservaDao.realizarReserva(reserva);
        }

        return ticket;
    }

    @Override
    public Sala consultarSala(Sala sala) {
        return servicioSalaDao.cosultarSala(sala);
    }

    @Override
    public Funcion consultarFuncionById(Long funcionId) throws FuncionInvalidaException {
        Funcion funcion = funcionDao.consultarFuncionById(funcionId);
        if (funcion == null)
            throw new FuncionInvalidaException("No se ha encontrado una función con el id " + funcionId, CodigoError.FUNCIONINVALIDA);
        return funcion;
    }


    @Override
    public AsientoViewModel[][] formatoSala(Long funcionId, int fil, int col) {
        List<AsientoFuncion> asientosFuncion = asientoFuncionDao.consultarDistribucionAsientosEnFuncion(funcionId);

        AsientoViewModel[][] sala = new AsientoViewModel[fil][col];

        int cont = 0;

        for (AsientoFuncion asientoFuncion : asientosFuncion) {

            if (asientoFuncion.getAsiento().getColumna() - 1 == 0)
                cont = 0;

            AsientoViewModel modelo = new AsientoViewModel();

            modelo.setId(asientoFuncion.getAsiento().getId());
            modelo.setEstadoAsientoId(asientoFuncion.getEstadoAsiento().getId());
            modelo.setTipoAsientoId(asientoFuncion.getAsiento().getTipoAsiento().getId());

            modelo.setColumna(ConstanteHelper.ABECEDARIO.charAt(cont++));

            sala[asientoFuncion.getAsiento().getFila() - 1][asientoFuncion.getAsiento().getColumna() - 1] = modelo;
        }

        return sala;
    }

    @Override
    public void actualizarEstadoAsiento(Long funcionId, Integer fila, Integer columna, Long estadoId) throws EstadoAsientoInvalidoException, PosicionAsientoInvalidoException, FuncionByIdNoEncontradaException, AsientoFuncionByFuncionIdAndPosicionNoEncontradoException, EstadoAsientoByIdNoEncontradoException, InconsistenciaCambioEstadoAsientoException {
        validarPosicionAsiento(fila, columna);
        validarEstadoAsiento(estadoId);

        if (funcionDao.consultarFuncionById(funcionId) == null)
            throw new FuncionByIdNoEncontradaException("No se ha encontrado una función con el id " + funcionId, CodigoError.FUNCIONBYIDNOENCONTRADA);

        ar.edu.unlam.tallerweb1.Models.EstadoAsiento estadoAsiento = estadoAsientoDao.getEstadoAsientoById(estadoId);

        if (estadoAsiento == null)
            throw new EstadoAsientoByIdNoEncontradoException("No se ha encontrado un asiento con el id " + estadoId, CodigoError.ESTADOASIENTOBYIDNOENCONTRADO);

        AsientoFuncion asientoFuncion = asientoFuncionDao.getAsientoFuncionByFuncionIdAndPosicion(funcionId, fila, columna);

        if (asientoFuncion == null)
            throw new AsientoFuncionByFuncionIdAndPosicionNoEncontradoException("No se ha encontrado un asiento para la función con id " + funcionId + " en la posición " + fila + " - " + columna, CodigoError.ASIENTOFUNCIONBYFUNCIONIDANDPOSICIONNOENCONTRADA);

        if (asientoFuncion.getEstadoAsiento().getId().equals(estadoAsiento.getId()))
            throw new InconsistenciaCambioEstadoAsientoException("El asiento ya se encuentra " + estadoAsiento.getEstado(), CodigoError.INCONSISTENCIACAMBIOESTADOASIENTO);

        asientoFuncion.setEstadoAsiento(estadoAsiento);
        asientoFuncionDao.cambiarEstadoAsiento(asientoFuncion);
    }

    @Override
    public void validarEstadoAsiento(Long estadoId) throws EstadoAsientoInvalidoException {
        if (!EstadoAsiento.LIBRE.getId().equals(estadoId) && !EstadoAsiento.OCUPADO.getId().equals(estadoId) && !EstadoAsiento.RESERVADO.getId().equals(estadoId))
            throw new EstadoAsientoInvalidoException("No se ha encontrado un estado de asiento con id " + estadoId, CodigoError.ESTADOASIENTOINVALIDO);
    }

    @Override
    public void validarPosicionAsiento(Integer fila, Integer columna) throws PosicionAsientoInvalidoException {
        if (fila <= 0 || columna <= 0)
            throw new PosicionAsientoInvalidoException("La posición del asiento es inválido", CodigoError.POSICIONASIENTOINVALIDO);
    }
    
    @Override
    public List<Pelicula> consultarPelisReservadasUsuario(Long usuarioId) {
    	List<Funcion> funciones = reservaDao.consultarReservasUsuario(usuarioId);
    	
    	if(funciones != null && funciones.size() > 0) {
    		List<Pelicula> pelis = new ArrayList<>();
    		
    		for(Funcion funcion : funciones) {
    			if(!pelis.contains(funcion.getPelicula()))
    				pelis.add(funcion.getPelicula());
    		}
    		
    		return pelis;
    	}
    	
    	return null;
    }

    @Override
    public List<Reserva> getReservasByNumeroTicket(String numeroTicket) throws NumeroTicketInvalidoException {
        List<Reserva> reservas = reservaDao.getReservasByNumeroTicket(numeroTicket);

        if(reservas == null || reservas.size() == 0)
            throw new NumeroTicketInvalidoException("No se han encontrado reservas para el número de ticket " + numeroTicket, CodigoError.NUMEROTICKETINVALIDO);

        return reservas;
    }

}
