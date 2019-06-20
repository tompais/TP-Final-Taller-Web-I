package ar.edu.unlam.tallerweb1.reserva;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.modelo.Pelicula;
import ar.edu.unlam.tallerweb1.modelo.Cine;
import ar.edu.unlam.tallerweb1.modelo.PeliculaCine;
import ar.edu.unlam.tallerweb1.modelo.Funcion;
import ar.edu.unlam.tallerweb1.modelo.Asiento;
import ar.edu.unlam.tallerweb1.modelo.Sala;
import ar.edu.unlam.tallerweb1.modelo.TipoAsiento;
import ar.edu.unlam.tallerweb1.modelo.EstadoAsiento;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioReserva;
import ar.edu.unlam.tallerweb1.servicios.ServicioReservaImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioLoginImpl;
import ar.edu.unlam.tallerweb1.SpringTest;

public class ReservaTest extends SpringTest{
	
	@Test
	@Transactional
	@Rollback(true)
	public void traerUsuario()
	{
		ServicioLogin servicioLogin = new ServicioLoginImpl();
		
		Usuario modelo = new Usuario();
		modelo.setEmail("ezequiel.allio@gmail.com");
		modelo.setuPassword("ezeallio");
		
		Usuario usuario = servicioLogin.consultarUsuario(modelo);
		System.out.println("VALOR RECIBIDO DE LA TABLA: " + usuario.getEmail());
		
		assertThat(modelo.getEmail()).isEqualTo(usuario.getEmail());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void TestReserva()
	{
		
		ServicioReserva servicioReserva = new ServicioReservaImpl();
		ServicioLogin servicioLogin = new ServicioLoginImpl();
		
		Usuario modelo = new Usuario();
		modelo.setEmail("ezequiel.allio@gmail.com");
		modelo.setuPassword("ezeallio");
		
		Usuario usuario = servicioLogin.consultarUsuario(modelo);
		
		LocalDate actual = LocalDate.now();
		
		List<Pelicula> peliculas = servicioReserva.consultarPeliculas(actual);
		
		List<Cine> cines = servicioReserva.consultarCinesPelicula(peliculas.get(0));
		
		PeliculaCine peliculaCine = new PeliculaCine();
		
		peliculaCine.setCine(cines.get(0));
		peliculaCine.setPelicula(peliculas.get(0));
		
		List<Funcion> funciones = servicioReserva.consultarFunciones(peliculaCine);
		
		Sala sala = new Sala();
		sala.setId((long)1);
		sala.setCine(cines.get(0));
		sala.setNumero(1);
		
		TipoAsiento tipoAsiento = new TipoAsiento();
		tipoAsiento.setId((long)1);
		tipoAsiento.setTipo("Standard");
		
		EstadoAsiento estadoAsiento = new EstadoAsiento();
		estadoAsiento.setId((long)1);
		estadoAsiento.setEstado("Libre");
		
		Asiento asiento = new Asiento();
		asiento.setId((long)1);
		asiento.setFila(1);
		asiento.setColumna(1);
		asiento.setSala(sala);
		asiento.setTipoAsiento(tipoAsiento);
		asiento.setEstadoAsiento(estadoAsiento);
		
		Integer ticket = servicioReserva.reservar(usuario, funciones.get(0), asiento);
		assertThat(ticket).isNotEqualTo(0);
	}
}
