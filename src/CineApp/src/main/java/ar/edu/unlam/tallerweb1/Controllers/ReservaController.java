package ar.edu.unlam.tallerweb1.Controllers;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.Dto.AsientoMessageDto;
import ar.edu.unlam.tallerweb1.Exceptions.*;
import ar.edu.unlam.tallerweb1.Helpers.EncryptorHelper;
import com.google.gson.Gson;
import org.apache.taglibs.standard.tag.common.core.Util;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.Enums.EstadoAsiento;
import ar.edu.unlam.tallerweb1.Models.Asiento;
import ar.edu.unlam.tallerweb1.Models.Funcion;
import ar.edu.unlam.tallerweb1.Models.Usuario;
import ar.edu.unlam.tallerweb1.Services.ServicioReserva;
import ar.edu.unlam.tallerweb1.Services.ServicioUsuario;
import ar.edu.unlam.tallerweb1.ViewModels.ReservaViewModel;
import ar.edu.unlam.tallerweb1.ViewModels.SalaViewModel;

@Controller
public class ReservaController extends BaseController {
    @Inject
    private ServicioReserva servicioReserva;
    
    @Inject
    private ServicioUsuario servicioUsuario;

    public ServicioReserva getServicioReserva() {
        return servicioReserva;
    }

    public void setServicioReserva(ServicioReserva servicioReserva) {
        this.servicioReserva = servicioReserva;
    }

    @RequestMapping(path = "/seleccionarAsiento/{funcionId}", method = RequestMethod.GET)
    public ModelAndView seleccionarAsiento(@PathVariable Long funcionId, HttpServletRequest request) throws FuncionInvalidaException, NoSuchAlgorithmException {

        ModelAndView mv = new ModelAndView();
        if (request.getSession().getAttribute("email") != null) {
            Funcion funcion = servicioReserva.consultarFuncionById(funcionId);

            List<Asiento> asientos = funcion.getSala().getAsientos();

            AtomicInteger fil = new AtomicInteger();

            asientos.stream().map(Asiento::getFila).max(Comparator.comparingInt(o -> o)).ifPresent(fil::set);

            AtomicInteger col = new AtomicInteger();

            asientos.stream().map(Asiento::getColumna).max(Comparator.comparingInt(o -> o)).ifPresent(col::set);

            SalaViewModel[][] formatoSala = servicioReserva.formatoSala(funcionId, fil.get(), col.get());

            long asientosDisponibles = Arrays.stream(formatoSala).flatMap(Arrays::stream)
                    .collect(Collectors.toList())
                    .stream()
                    .filter(salaViewModel -> salaViewModel != null
                            && salaViewModel.getEstadoAsientoId()
                            .equals(EstadoAsiento.LIBRE.getId()))
                    .count();

            ModelMap modelo = new ModelMap();

            modelo.put("formatoSala", formatoSala);
            modelo.put("fila", fil.get() - 1);
            modelo.put("columna", col.get() - 1);
            modelo.put("libre", EstadoAsiento.LIBRE);
            modelo.put("ocupado", EstadoAsiento.OCUPADO);
            modelo.put("reservado", EstadoAsiento.RESERVADO);
            modelo.put("precio", funcion.getPrecio());
            modelo.put("asientosDisponibles", asientosDisponibles);
            modelo.put("funcionId", funcionId);
            modelo.put("sender", EncryptorHelper.encryptToMd5(request.getSession().getAttribute("username").toString()));

            mv.setViewName("Reserva/seleccionarAsiento");
            mv.addAllObjects(modelo);
        } else {
            mv.setViewName("redirect:/signin?returnUrl=" + Util.URLEncode("/seleccionarAsiento/" + funcionId, StandardCharsets.UTF_8.toString()));
        }

        return mv;
    }

    @ResponseBody
    @RequestMapping(path = "/actualizarEstadoAsiento", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String actualizarEstadoAsiento(@RequestBody AsientoMessageDto asientoMessageDto) throws AsientoFuncionByFuncionIdAndPosicionNoEncontradoException, PosicionAsientoInvalidoException, EstadoAsientoByIdNoEncontradoException, InconsistenciaCambioEstadoAsientoException, EstadoAsientoInvalidoException, FuncionByIdNoEncontradaException {
        servicioReserva.actualizarEstadoAsiento(asientoMessageDto.getFuncionId(), asientoMessageDto.getFila(), asientoMessageDto.getColumna(), asientoMessageDto.getEstadoId());
        return new Gson().toJson(asientoMessageDto);
    }

    @MessageMapping("/onAsientoSeleccionado")
    @SendTo("/topic/onReceiveAsientoSeleccionado")
    public AsientoMessageDto onAsientoSeleccionado(AsientoMessageDto asientoMessageDto) {
        return asientoMessageDto;
    }
    
    @ResponseBody
    @RequestMapping(path = "/realizarReserva", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String realizarReserva(@RequestBody ReservaViewModel reservaViewModel, HttpServletRequest request) {
    	String email = (String) request.getSession().getAttribute("email");
    	Usuario usuario = new Usuario();
    	usuario.setEmail(email);
    	Usuario usuarioBuscado = servicioUsuario.consultarUsuario(usuario);
    	
    	String numeroTicket = servicioReserva.reservar(usuarioBuscado, reservaViewModel);
    	
    	return new Gson().toJson(numeroTicket);
    }
}
