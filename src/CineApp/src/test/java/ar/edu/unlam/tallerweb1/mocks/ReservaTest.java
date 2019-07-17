package ar.edu.unlam.tallerweb1.mocks;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.Exceptions.FuncionInvalidaException;
import ar.edu.unlam.tallerweb1.Models.Funcion;
import ar.edu.unlam.tallerweb1.Services.ServicioReservaImpl;
import ar.edu.unlam.tallerweb1.dao.FuncionDao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

public class ReservaTest extends SpringTest{
	
	@SuppressWarnings("unchecked")
	@Test(expected = FuncionInvalidaException.class)
    @Transactional
    @Rollback
    public void mostrarExcepcionFuncionInvalida() throws FuncionInvalidaException {
		Long funcionId = 4L;
		
		FuncionDao funcionDao = mock(FuncionDao.class);
		
		when(funcionDao.consultarFuncionById(funcionId)).thenThrow(FuncionInvalidaException.class);
		
		ServicioReservaImpl servicioReserva = new ServicioReservaImpl();
		
		servicioReserva.setFuncionDao(funcionDao);
		
		servicioReserva.consultarFuncionById(funcionId);
	}
	
	@Test
    @Transactional
    @Rollback
    public void consultarFuncionExitoso() throws FuncionInvalidaException {
		Long funcionId = 1L;
		
		Funcion funcion = new Funcion();
		
		funcion.setId(1L);
		
		FuncionDao funcionDao = mock(FuncionDao.class);
		
		when(funcionDao.consultarFuncionById(funcionId)).thenReturn(funcion);
		
		ServicioReservaImpl servicioReserva = new ServicioReservaImpl();
		
		servicioReserva.setFuncionDao(funcionDao);
		
		assertThat(servicioReserva.consultarFuncionById(funcionId)).isEqualTo(funcion);
	}
}
