package ar.edu.unlam.tallerweb1.Controllers;

import ar.edu.unlam.tallerweb1.Models.Pelicula;
import ar.edu.unlam.tallerweb1.Services.ServicioCalificacion;
import ar.edu.unlam.tallerweb1.Services.ServicioPelicula;
import ar.edu.unlam.tallerweb1.ViewModels.PublicacionViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController extends BaseController {

    @Inject
    private ServicioPelicula servicioPelicula;

    @Inject
    private ServicioCalificacion servicioCalificacion;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView irAInicio() {
        List<PublicacionViewModel> publicaciones = new ArrayList<>();


        List<Pelicula> peliculas = servicioPelicula.getPeliculasDisponiblesEnCartelera();

        List<Pelicula> estrenos = peliculas.stream().filter(p -> Period.between(p.getFechaEstreno().toLocalDate(), LocalDate.now()).getDays() <= 7).collect(Collectors.toList());

        Comparator<Pelicula> compareByFechaEstreno = Comparator.comparing(Pelicula::getFechaEstreno).reversed();

        estrenos.sort(compareByFechaEstreno);

        for (Pelicula pelicula :
                peliculas) {
            publicaciones.add(new PublicacionViewModel(pelicula, servicioCalificacion.getPromedioCalificacionByPeliculaId(pelicula.getId())));
        }

        Comparator<PublicacionViewModel> compareByPromedioCalificacion = Comparator.comparing(PublicacionViewModel::getPromedioCalificacion).reversed();

        publicaciones = publicaciones.subList(0, publicaciones.size() < 6 ? publicaciones.size() : 6);

        publicaciones.sort(compareByPromedioCalificacion);

        ModelMap mm = new ModelMap();

        mm.addAttribute("publicaciones", publicaciones);

        mm.addAttribute("estrenos", estrenos);

        return new ModelAndView("Home/inicio", mm);
    }
}
