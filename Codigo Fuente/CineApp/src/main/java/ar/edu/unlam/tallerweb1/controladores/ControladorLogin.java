package ar.edu.unlam.tallerweb1.controladores;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelos.Asiento;
import ar.edu.unlam.tallerweb1.modelos.Cine;
import ar.edu.unlam.tallerweb1.modelos.EstadoAsiento;
import ar.edu.unlam.tallerweb1.modelos.Funcion;
import ar.edu.unlam.tallerweb1.modelos.Pelicula;
import ar.edu.unlam.tallerweb1.modelos.PeliculaCine;
import ar.edu.unlam.tallerweb1.modelos.Sala;
import ar.edu.unlam.tallerweb1.modelos.TipoAsiento;
import ar.edu.unlam.tallerweb1.modelos.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioReserva;

@Controller
public class ControladorLogin {

	// La anotacion @Inject indica a Spring que en este atributo se debe setear (inyeccion de dependencias)
	// un objeto de una clase que implemente la interface ServicioLogin, dicha clase debe estar anotada como
	// @Service o @Repository y debe estar en un paquete de los indicados en applicationContext.xml
	@Inject
	private ServicioLogin servicioLogin;
	
	@Inject
	private ServicioReserva servicioReserva;

	// Este metodo escucha la URL localhost:8080/NOMBRE_APP/login si la misma es invocada por metodo http GET
	@RequestMapping("/login")
	public ModelAndView irALogin() {

		ModelMap modelo = new ModelMap();
		// Se agrega al modelos un objeto del tipo Usuario con key 'usuario' para que el mismo sea asociado
		// al model attribute del form que esta definido en la vista 'login'
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		// Se va a la vista login (el nombre completo de la lista se resuelve utilizando el view resolver definido en el archivo spring-servlet.xml)
		// y se envian los datos a la misma  dentro del modelos
		return new ModelAndView("ejemplos/login", modelo);
	}

	// Este metodo escucha la URL validar-login siempre y cuando se invoque con metodo http POST
	// El método recibe un objeto Usuario el que tiene los datos ingresados en el form correspondiente y se corresponde con el modelAttribute definido en el
	// tag form:form
	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		// invoca el metodo consultarUsuario del servicio y hace un redirect a la URL /home, esto es, en lugar de enviar a una vista
		// hace una llamada a otro action a través de la URL correspondiente a ésta
		Usuario usuarioBuscado = servicioLogin.consultarUsuario(usuario);
		if (usuarioBuscado != null) {
			//request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
			return new ModelAndView("redirect:/home");
		} else {
			// si el usuario no existe agrega un mensaje de error en el modelos.
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("ejemplos/login", model);
	}

	// Escucha la URL /home por GET, y redirige a una vista.
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		return new ModelAndView("ejemplos/home");
	}
	
	@RequestMapping(path = "/prueba", method = RequestMethod.GET)
	public ModelAndView prueba() {
		ModelMap modelo = new ModelMap();
		
		Usuario usuarioBuscado = new Usuario();
		usuarioBuscado.setEmail("ezequiel.allio@gmail.com");
		usuarioBuscado.setuPassword("ezeallio");
		
		Usuario usuario = servicioLogin.consultarUsuario(usuarioBuscado);
		
		long millis = System.currentTimeMillis();
		Date actual = new Date(millis);
		
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
		
		if(ticket != 0)
			modelo.put("mensaje", "Numero de reserva: " + ticket);
		else
			modelo.put("mensaje", "No se pudo realizar la reserva correctamente");
		
		return new ModelAndView("prueba", modelo);
	}

	// Escucha la url /, y redirige a la URL /login, es lo mismo que si se invoca la url /login directamente.
//	@RequestMapping(path = "/", method = RequestMethod.GET)
//	public ModelAndView inicio() {
//		return new ModelAndView("redirect:/login");
//	}
}
