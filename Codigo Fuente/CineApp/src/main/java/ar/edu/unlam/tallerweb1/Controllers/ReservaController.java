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
import ar.edu.unlam.tallerweb1.Models.Asiento;
import ar.edu.unlam.tallerweb1.Models.Funcion;
import ar.edu.unlam.tallerweb1.Models.Sala;
import ar.edu.unlam.tallerweb1.Services.ServicioReserva;
import ar.edu.unlam.tallerweb1.ViewModels.SalaViewModel;

@Controller
public class ReservaController extends BaseController {
	@Inject
	private ServicioReserva servicioReserva;
	
	@RequestMapping(path = "/sala", method = RequestMethod.GET)
	public ModelAndView mostrarSala(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		if(request.getSession().getAttribute("email") != null)
    		mv.setViewName("redirect:/");
		else {
			Sala aux = new Sala();
			aux.setId((long)1);
			
			Sala sala = servicioReserva.consultarSala(aux);
			
			List<Asiento> asientos = sala.getAsientos();
			
			Collections.sort(asientos, new OrdenarPorFilaMayor());
			
			int fil = asientos.get(0).getFila();
			
			Collections.sort(asientos, new OrdenarPorColumnaMayor());
			
			int col = asientos.get(0).getColumna();
			
			SalaViewModel[][] formatoSala = servicioReserva.formatoSala(1, fil, col);
			
			ModelMap modelo = new ModelMap();
			
			modelo.put("formatoSala", formatoSala);
			
			return new ModelAndView("Reserva/sala", modelo);
		}
		
		return mv;
	}
}
