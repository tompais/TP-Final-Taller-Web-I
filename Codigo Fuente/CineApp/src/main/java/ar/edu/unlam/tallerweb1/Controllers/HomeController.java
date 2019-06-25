package ar.edu.unlam.tallerweb1.Controllers;

import ar.edu.unlam.tallerweb1.Models.Pelicula;
import ar.edu.unlam.tallerweb1.Models.PeliculaGeneroPelicula;
import ar.edu.unlam.tallerweb1.Services.ServicioPelicula;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.sql.Date;
import java.util.List;

@Controller
public class HomeController extends BaseController {

    @Inject
    private ServicioPelicula servicioPelicula;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView irAInicio() {
        List<Pelicula> peliculas = servicioPelicula.getPeliculasDisponiblesEnCartelera();

        ModelMap mm = new ModelMap();

        mm.addAttribute("peliculas", peliculas);

        return new ModelAndView("Home/inicio", mm);
    }
}
