package ar.edu.unlam.tallerweb1.Controllers;

import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.Exceptions.FuncionInvalidaException;
import org.apache.taglibs.standard.tag.common.core.Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.Enums.EstadoAsiento;
import ar.edu.unlam.tallerweb1.Models.Asiento;
import ar.edu.unlam.tallerweb1.Models.Funcion;
import ar.edu.unlam.tallerweb1.Services.ServicioReserva;
import ar.edu.unlam.tallerweb1.ViewModels.SalaViewModel;

@Controller
public class ReservaController extends BaseController {
	@Inject
	private ServicioReserva servicioReserva;

	@RequestMapping(path = "/seleccionarAsiento/{funcionId}", method = RequestMethod.GET)
	public ModelAndView seleccionarAsiento(@PathVariable Long funcionId, HttpServletRequest request) throws FuncionInvalidaException {

		ModelAndView mv = new ModelAndView();
		if(request.getSession().getAttribute("email") != null)
    	{
			Funcion funcion = servicioReserva.consultarFuncionById(funcionId);

			List<Asiento> asientos = funcion.getSala().getAsientos();

			AtomicInteger fil = new AtomicInteger();

			asientos.stream().map(Asiento::getFila).max(Comparator.comparingInt(o -> o)).ifPresent(fil::set);

			AtomicInteger col = new AtomicInteger();

			asientos.stream().map(Asiento::getColumna).max(Comparator.comparingInt(o -> o)).ifPresent(col::set);

			SalaViewModel[][] formatoSala = servicioReserva.formatoSala(funcionId, fil.get(), col.get());

			ModelMap modelo = new ModelMap();

			modelo.put("formatoSala", formatoSala);
			modelo.put("fila", fil.get() - 1);
			modelo.put("columna", col.get() - 1);
			modelo.put("libre", EstadoAsiento.LIBRE);
			modelo.put("ocupado", EstadoAsiento.OCUPADO);
			modelo.put("reservado", EstadoAsiento.RESERVADO);
			modelo.put("precio", funcion.getPrecio());

			mv.setViewName("Reserva/seleccionarAsiento");
			mv.addAllObjects(modelo);
        } else {
			mv.setViewName("redirect:/signin?returnUrl=" + Util.URLEncode("/seleccionarAsiento/" + funcionId, StandardCharsets.UTF_8.toString()));
		}

		return mv;
	}
}
