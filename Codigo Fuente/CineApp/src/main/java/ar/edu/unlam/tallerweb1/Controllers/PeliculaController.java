package ar.edu.unlam.tallerweb1.Controllers;

import ar.edu.unlam.tallerweb1.Dto.FuncionDto;
import ar.edu.unlam.tallerweb1.Exceptions.PeliculaNoEncontradaException;
import ar.edu.unlam.tallerweb1.Models.Cine;
import ar.edu.unlam.tallerweb1.Models.Pelicula;
import ar.edu.unlam.tallerweb1.Models.PeliculaActor;
import ar.edu.unlam.tallerweb1.Models.PeliculaGeneroPelicula;
import ar.edu.unlam.tallerweb1.Services.ServicioFuncion;
import ar.edu.unlam.tallerweb1.Services.ServicioPelicula;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.List;

@Controller
public class PeliculaController {

    @Inject
    private ServicioPelicula servicioPelicula;

    @Inject
    private ServicioFuncion servicioFuncion;

    public ServicioPelicula getServicioPelicula() {
        return servicioPelicula;
    }

    public void setServicioPelicula(ServicioPelicula servicioPelicula) {
        this.servicioPelicula = servicioPelicula;
    }

    @RequestMapping(path = "/pelicula/{peliculaId}", method = RequestMethod.GET)
    public ModelAndView mostrarPelicula(@PathVariable Long peliculaId) throws PeliculaNoEncontradaException {
        Pelicula pelicula = servicioPelicula.getPeliculaById(peliculaId);

        List<Cine> cines = servicioPelicula.getCinesDisponiblesByPeliculaId(peliculaId);

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
        mm.addAttribute("cines", cines);
        mm.addAttribute("listaGeneros", listaGeneros.toString());
        mm.addAttribute("listaActores", listaActores.toString());
        return new ModelAndView("Pelicula/mostrar", mm);
    }

    @ResponseBody
    @RequestMapping(path = "/getRangoFechaCompra", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getRangoFechaCompra(@RequestBody FuncionDto funcion) {
        return new Gson().toJson(servicioFuncion.getFechaUltimaFuncionByPeliculaAndCineId(funcion.getPeliculaId(), funcion.getCineId()).toLocalDateTime().toString());
    }
}
