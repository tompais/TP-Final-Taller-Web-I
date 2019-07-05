package ar.edu.unlam.tallerweb1.Controllers;

import ar.edu.unlam.tallerweb1.Exceptions.PeliculaNoEncontradaException;
import ar.edu.unlam.tallerweb1.Models.Pelicula;
import ar.edu.unlam.tallerweb1.Models.PeliculaActor;
import ar.edu.unlam.tallerweb1.Models.PeliculaGeneroPelicula;
import ar.edu.unlam.tallerweb1.Services.ServicioPelicula;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

@Controller
public class PeliculaController {

    @Inject
    private ServicioPelicula servicioPelicula;

    @RequestMapping(path = "/pelicula/{peliculaId}", method = RequestMethod.GET)
    public ModelAndView mostrarPelicula(@PathVariable Long peliculaId) throws PeliculaNoEncontradaException {
        Pelicula pelicula = servicioPelicula.getPeliculaById(peliculaId);

        StringBuilder listaGeneros = new StringBuilder();
        StringBuilder listaActores = new StringBuilder();

        for (PeliculaGeneroPelicula peliculaGeneroPelicula :
                pelicula.getPeliculaGeneroPeliculas()) {
            listaGeneros.append(peliculaGeneroPelicula.getGeneroPelicula().getNombre()).append(pelicula.getPeliculaGeneroPeliculas().indexOf(peliculaGeneroPelicula) != pelicula.getPeliculaGeneroPeliculas().size() - 1 ? ", " : "");
        }

        for (PeliculaActor peliculaActor :
                pelicula.getPeliculaActores()) {
            listaActores.append(peliculaActor.getActor().getNombre()).append(" ").append(peliculaActor.getActor().getApellido()).append(pelicula.getPeliculaActores().indexOf(peliculaActor) != pelicula.getPeliculaActores().size() - 1 ? ", " : "");
        }

        ModelMap mm = new ModelMap();
        mm.addAttribute("pelicula", pelicula);
        mm.addAttribute("listaGeneros", listaGeneros.toString());
        mm.addAttribute("listaActores", listaActores.toString());
        return new ModelAndView("Pelicula/mostrar", mm);
    }
}
