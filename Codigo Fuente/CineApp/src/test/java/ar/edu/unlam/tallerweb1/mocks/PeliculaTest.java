package ar.edu.unlam.tallerweb1.mocks;

import ar.edu.unlam.tallerweb1.Controllers.PeliculaController;
import ar.edu.unlam.tallerweb1.Exceptions.PeliculaNoEncontradaException;
import ar.edu.unlam.tallerweb1.Models.Cine;
import ar.edu.unlam.tallerweb1.Models.Pelicula;
import ar.edu.unlam.tallerweb1.Services.ServicioPelicula;
import ar.edu.unlam.tallerweb1.SpringTest;
import org.junit.Test;
import org.springframework.security.access.method.P;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PeliculaTest extends SpringTest {

    @Test
    @Transactional
    @Rollback
    public void mostrarPeliculaActionFuncionaTest() throws PeliculaNoEncontradaException {
        Long peliculaId = 1L;

        Pelicula pelicula = new Pelicula();

        pelicula.setPeliculaActores(new ArrayList<>());
        pelicula.setPeliculaGeneroPeliculas(new ArrayList<>());

        List<Cine> cines = new ArrayList<>();

        ModelMap mm = new ModelMap();

        mm.addAttribute("pelicula", pelicula);
        mm.addAttribute("cines", cines);
        mm.addAttribute("listaGeneros", "");
        mm.addAttribute("listaActores", "");

        ModelAndView mv = new ModelAndView("Pelicula/mostrar", mm);

        PeliculaController peliculaController = new PeliculaController();

        ServicioPelicula servicioPelicula = mock(ServicioPelicula.class);

        when(servicioPelicula.getCinesDisponiblesByPeliculaId(peliculaId)).thenReturn(cines);
        when(servicioPelicula.getPeliculaById(peliculaId)).thenReturn(pelicula);

        peliculaController.setServicioPelicula(servicioPelicula);

        assertThat(peliculaController.mostrarPelicula(peliculaId)).isEqualToComparingFieldByField(mv);
    }

    @Test(expected = PeliculaNoEncontradaException.class)
    @Transactional
    @Rollback
    public void mostrarPeliculaActionNoEncuentraPeliculaTest() throws PeliculaNoEncontradaException {
        Long peliculaId = 1L;

        ServicioPelicula servicioPelicula = mock(ServicioPelicula.class);

        when(servicioPelicula.getPeliculaById(peliculaId)).thenThrow(PeliculaNoEncontradaException.class);

        PeliculaController peliculaController = new PeliculaController();

        peliculaController.setServicioPelicula(servicioPelicula);

        peliculaController.mostrarPelicula(peliculaId);
    }
}
