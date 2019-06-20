package ar.edu.unlam.tallerweb1.reserva;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.modelos.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioLoginImpl;
import ar.edu.unlam.tallerweb1.SpringTest;

public class ReservaTest extends SpringTest{
	
	@Test
	@Transactional
	@Rollback()
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
	
//	@Test
//	@Transactional
//	@Rollback()
//	public void TestReserva()
//	{
//
//		ServicioReserva servicioReserva = new ServicioReservaImpl();
//		ServicioLogin servicioLogin = new ServicioLoginImpl();
//
//		Usuario modelos = new Usuario();
//		modelos.setEmail("ezequiel.allio@gmail.com");
//		modelos.setuPassword("ezeallio");
//
//		Usuario usuario = servicioLogin.consultarUsuario(modelos);
//
//		LocalDate actual = LocalDate.now();
//
//		List<Pelicula> peliculas = servicioReserva.consultarPeliculas(actual);
//
//		List<Cine> cines = servicioReserva.consultarCinesPelicula(peliculas.get(0));
//
//		PeliculaCine peliculaCine = new PeliculaCine();
//
//		peliculaCine.setCine(cines.get(0));
//		peliculaCine.setPelicula(peliculas.get(0));
//
//		List<Funcion> funciones = servicioReserva.consultarFunciones(peliculaCine);
//
//		Sala sala = new Sala();
//		sala.setId((long)1);
//		sala.setCine(cines.get(0));
//		sala.setNumero(1);
//
//		TipoAsiento tipoAsiento = new TipoAsiento();
//		tipoAsiento.setId((long)1);
//		tipoAsiento.setTipo("Standard");
//
//		EstadoAsiento estadoAsiento = new EstadoAsiento();
//		estadoAsiento.setId((long)1);
//		estadoAsiento.setEstado("Libre");
//
//		Asiento asiento = new Asiento();
//		asiento.setId((long)1);
//		asiento.setFila(1);
//		asiento.setColumna(1);
//		asiento.setSala(sala);
//		asiento.setTipoAsiento(tipoAsiento);
//		asiento.setEstadoAsiento(estadoAsiento);
//
//		Integer ticket = servicioReserva.reservar(usuario, funciones.get(0), asiento);
//		assertThat(ticket).isNotEqualTo(0);
//	}
}
