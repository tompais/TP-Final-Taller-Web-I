package ar.edu.unlam.tallerweb1.Controllers;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.Comparators.OrdenarPorColumnaMayor;
import ar.edu.unlam.tallerweb1.Comparators.OrdenarPorFilaMayor;
import ar.edu.unlam.tallerweb1.Enums.EstadoAsiento;
import ar.edu.unlam.tallerweb1.Models.Asiento;
import ar.edu.unlam.tallerweb1.Models.Funcion;
import ar.edu.unlam.tallerweb1.Services.ServicioReserva;
import ar.edu.unlam.tallerweb1.ViewModels.SalaViewModel;

@Controller
public class ReservaController extends BaseController {
	@Inject
	private ServicioReserva servicioReserva;
	
	@RequestMapping(path = "/sala", method = RequestMethod.GET)
	public ModelAndView mostrarSala(HttpServletRequest request) {
		
		if(request.getSession().getAttribute("email") != null)
    	{
			Funcion aux = new Funcion();
			aux.setId((long)1);
			
			Funcion funcion = servicioReserva.consultarFuncion(aux);
			
			List<Asiento> asientos = funcion.getSala().getAsientos();
			
			Collections.sort(asientos, new OrdenarPorFilaMayor());
			
			int fil = asientos.get(0).getFila();
			
			Collections.sort(asientos, new OrdenarPorColumnaMayor());
			
			int col = asientos.get(0).getColumna();
			
			SalaViewModel[][] formatoSala = servicioReserva.formatoSala(aux, fil, col);
			
			ModelMap modelo = new ModelMap();
			
			modelo.put("formatoSala", formatoSala);
			modelo.put("fila", fil - 1);
			modelo.put("columna", col - 1);
			modelo.put("libre", EstadoAsiento.LIBRE.getEstadoAsiento());
			modelo.put("ocupado", EstadoAsiento.OCUPADO.getEstadoAsiento());
			modelo.put("reservado", EstadoAsiento.RESERVADO.getEstadoAsiento());
			modelo.put("precio", funcion.getPrecio());
			
			return new ModelAndView("Reserva/sala", modelo);
		}
		
		return new ModelAndView("redirect:/");
	}
}
