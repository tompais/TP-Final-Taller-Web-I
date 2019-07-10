package ar.edu.unlam.tallerweb1.Controllers;

import java.sql.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.Services.ServicioUsuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.Models.Usuario;
import ar.edu.unlam.tallerweb1.Services.ServicioReserva;

@Controller
public class PruebaController extends BaseController{
	@Inject
	private ServicioUsuario servicioLogin;
	
	@Inject
	private ServicioReserva servicioReserva;
	
	@RequestMapping(path = "/prueba", method = RequestMethod.GET)
	public ModelAndView prueba() {
		ModelMap modelo = new ModelMap();
		
		Usuario usuarioBuscado = new Usuario();
		usuarioBuscado.setEmail("ezequiel.allio@gmail.com");
		usuarioBuscado.setuPassword("ezeallio");
		
		Usuario usuario = servicioLogin.consultarUsuario(usuarioBuscado);
		modelo.put("mensaje", usuario.getEmail());
		long millis = System.currentTimeMillis();
		Date actual = new Date(millis);

		/*List<Pelicula> peliculas = servicioReserva.consultarPeliculas(actual);
		
		System.out.println("Pelicula rescatada: " + peliculas.get(0).getNombre());
		
		List<PeliculaCine> peliculaCines = servicioReserva.consultarCinesPelicula(peliculas.get(0));
		
		System.out.println("Cine rescatada: " + peliculaCines.get(0).getCine().getNombre());
		
		List<Funcion> funciones = servicioReserva.consultarFunciones(peliculaCines.get(0));
		
		System.out.println("Funcion rescatada: " + funciones.get(0).getId());
		
		EstadoAsiento estadoAsientoBuscado = new EstadoAsiento();
		estadoAsientoBuscado.setId((long)2);
		
		EstadoAsiento estadoAsiento = servicioReserva.consultarEstadoAsiento(estadoAsientoBuscado);
		
		System.out.println("Estado asiento: " + estadoAsiento.getEstado());
		
		Asiento asientoBuscado = new Asiento();
		asientoBuscado.setId((long)1);
		
		Asiento asiento = servicioReserva.consultaAsiento(asientoBuscado);
		
		asiento.setEstadoAsientoId(estadoAsiento);
		servicioReserva.cambiarEstadoAsiento(asiento);
		
		asiento = servicioReserva.consultaAsiento(asientoBuscado);

		System.out.println("Asiento: " + asiento.getId());
		
		Integer ticket = servicioReserva.reservar(usuario, funciones.get(0), asiento);
		
		modelo.put("mensaje", "Numero de ticket: " + ticket);*/
		
		return new ModelAndView("Prueba/prueba", modelo);
	}
	
	@RequestMapping(path = "/prueba2", method = RequestMethod.GET)
	public ModelAndView prueba2(HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		
		modelo.put("mensaje", request.getSession().getAttribute("username"));
		
		return new ModelAndView("Prueba/prueba", modelo);
	}
}
