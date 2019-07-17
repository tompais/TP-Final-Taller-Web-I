package ar.edu.unlam.tallerweb1.Controllers;

import ar.edu.unlam.tallerweb1.Exceptions.UsuarioNoEncontradoException;
import ar.edu.unlam.tallerweb1.Models.GeneroPelicula;
import ar.edu.unlam.tallerweb1.Models.Pelicula;
import ar.edu.unlam.tallerweb1.Models.Usuario;
import ar.edu.unlam.tallerweb1.Services.ServicioPelicula;
import ar.edu.unlam.tallerweb1.Services.ServicioPeliculaGeneroPelicula;
import ar.edu.unlam.tallerweb1.Services.ServicioReserva;
import ar.edu.unlam.tallerweb1.Services.ServicioUsuario;
import ar.edu.unlam.tallerweb1.ViewModels.PublicacionViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController extends BaseController {

    @Inject
    private ServicioPelicula servicioPelicula;
    
    @Inject
    private ServicioReserva servicioReserva;
    
    @Inject
    private ServicioPeliculaGeneroPelicula servicioPeliculaGeneroPelicula;
    
    @Inject
    private ServicioUsuario servicioUsuario;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView irAInicio(HttpServletRequest request) throws UsuarioNoEncontradoException {
        List<PublicacionViewModel> publicaciones = new ArrayList<>();


        List<Pelicula> peliculas = servicioPelicula.getPeliculasDisponiblesEnCartelera();

        List<Pelicula> proximosEstrenos = servicioPelicula.getProximosEstrenos();

        List<Pelicula> estrenos = peliculas.stream().filter(p -> ChronoUnit.WEEKS.between(p.getFechaEstreno().toLocalDate(), LocalDate.now()) <= 1).collect(Collectors.toList());

        Comparator<Pelicula> compareByFechaEstreno = Comparator.comparing(Pelicula::getFechaEstreno).reversed();

        estrenos.sort(compareByFechaEstreno);

        for (Pelicula pelicula :
                peliculas) {
            publicaciones.add(new PublicacionViewModel(pelicula, servicioPelicula.getPromedioCalificacionByPeliculaId(pelicula.getId())));
        }

        Comparator<PublicacionViewModel> compareByPromedioCalificacion = Comparator.comparing(PublicacionViewModel::getPromedioCalificacion).reversed();

        publicaciones = publicaciones.subList(0, publicaciones.size() < 6 ? publicaciones.size() : 6);

        publicaciones.sort(compareByPromedioCalificacion);
        
        ModelMap mm = new ModelMap();

        mm.addAttribute("publicaciones", publicaciones);

        mm.addAttribute("estrenos", estrenos);

        mm.addAttribute("proximosEstrenos", proximosEstrenos);
        
        if(request.getSession().getAttribute("email") != null)
        {
        	Usuario usuario = new Usuario();
        	usuario.setEmail(request.getSession().getAttribute("email").toString());
        	Usuario usuarioBuscado = servicioUsuario.consultarUsuario(usuario);

        	List<Pelicula> pelis = servicioReserva.consultarPelisReservadasUsuario(usuarioBuscado.getId());
        	
        	if(pelis != null) {
        		List<GeneroPelicula> generoPelis = servicioPeliculaGeneroPelicula.consultarGeneroPelis(pelis);
        		List<Pelicula> pelisRecomendadas = servicioPeliculaGeneroPelicula.consultarPelisRecomendadas(pelis, generoPelis);
        		mm.addAttribute("recomendaciones", pelisRecomendadas);
        	}
        }
        
        Cookie[] cookies = request.getCookies();
        
        if(cookies != null && request.getSession().getAttribute("email") == null) {        	
        	for(Cookie cookie : cookies){
        		if(cookie.getName().equals("sesion")) {
        			String[] datos = cookie.getValue().split("|");
        			request.getSession().setAttribute("email", datos[0]);
        			request.getSession().setAttribute("rol", datos[1]);
        			request.getSession().setAttribute("username", datos[2]);
        			break;
        		}
        	}
        }

        return new ModelAndView("Home/inicio", mm);
    }
}
