package ar.edu.unlam.tallerweb1.Controllers;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.Dto.AsientoDto;
import ar.edu.unlam.tallerweb1.Dto.AsientoMessageDto;
import ar.edu.unlam.tallerweb1.Enums.CodigoError;
import ar.edu.unlam.tallerweb1.Exceptions.*;
import ar.edu.unlam.tallerweb1.Helpers.EncoDecoderHelper;
import ar.edu.unlam.tallerweb1.Helpers.EncryptorHelper;
import ar.edu.unlam.tallerweb1.Helpers.MailHelper;
import ar.edu.unlam.tallerweb1.Models.Reserva;
import ar.edu.unlam.tallerweb1.Services.ServicioCodigoReferido;
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
import ar.edu.unlam.tallerweb1.ViewModels.AsientoViewModel;

@Controller
public class ReservaController extends BaseController {
    @Inject
    private ServicioReserva servicioReserva;

    @Inject
    private ServicioUsuario servicioUsuario;

    @Inject
    private ServicioCodigoReferido servicioCodigoReferido;

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

            AsientoViewModel[][] formatoSala = servicioReserva.formatoSala(funcionId, fil.get(), col.get());

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

    private Usuario getUsuarioByEmail(String email) throws UsuarioNoEncontradoException {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        return servicioUsuario.consultarUsuario(usuario);
    }

    @ResponseBody
    @RequestMapping(path = "/realizarReserva", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String realizarReserva(@RequestBody ReservaViewModel reservaViewModel, HttpServletRequest request) throws UsuarioNoEncontradoException {
        String email = request.getSession().getAttribute("email").toString();
        Usuario usuarioBuscado = getUsuarioByEmail(email);

        if (usuarioBuscado == null)
            throw new UsuarioNoEncontradoException("No se ha encontrado al usuario que intenta realizar la reserva", CodigoError.USUARIONOENCONTRADO);

        String numeroTicket = servicioReserva.reservar(usuarioBuscado, reservaViewModel);

        return new Gson().toJson(numeroTicket);
    }

    @RequestMapping(path = "/reservaExitosa", method = RequestMethod.GET)
    public ModelAndView irAReservaExitosa(@RequestParam String nt, HttpServletRequest request) throws MessagingException, UsuarioNoEncontradoException {
        ModelAndView mv = new ModelAndView();

        if (servicioCodigoReferido.existeCodigoReferidoInactivo(nt)) {
            mv.setViewName("redirect:/linkExpirado");
        } else if (request.getSession().getAttribute("email") == null) {
            mv.setViewName("redirect:/signin?returnUrl=" + Util.URLEncode("/reservaExitosa?nt=" + EncoDecoderHelper.base64Encode(URLEncoder.encode(nt, StandardCharsets.UTF_8)), StandardCharsets.UTF_8.toString()));
        } else {
            servicioCodigoReferido.crearCodigo(nt);
            servicioCodigoReferido.darDeBajaCodigo(nt);

            String numeroTicket = URLDecoder.decode(EncoDecoderHelper.base64Decode(nt), StandardCharsets.UTF_8);
            ModelMap mm = new ModelMap();

            mm.addAttribute("numeroTicket", numeroTicket);
            MailHelper.enviarMail(request.getSession().getAttribute("email").toString(), "Reserva #" + numeroTicket, "Acceda al siguiente link para obtener los detalles de su reserva: " + (request.isSecure() ? "https" : "http") + "://" + request.getHeader("host") + request.getContextPath() + "/detalleReserva/" + numeroTicket);

            mv.addAllObjects(mm);
            mv.setViewName("Reserva/exito");
        }

        return mv;
    }

    @RequestMapping(path = "/detalleReserva/{numeroTicket}", method = RequestMethod.GET)
    public ModelAndView irADetalleReserva(@PathVariable String numeroTicket, HttpServletRequest request) throws MessagingException, NumeroTicketInvalidoException {
        ModelAndView mv = new ModelAndView();

        if (request.getSession().getAttribute("email") == null) {
            mv.setViewName("redirect:/signin?returnUrl=" + Util.URLEncode("/detalleReserva/" + numeroTicket, StandardCharsets.UTF_8.toString()));
        } else {
            ModelMap mm = new ModelMap();

            List<Reserva> reservas = servicioReserva.getReservasByNumeroTicket(numeroTicket);

            Double precioTotal = reservas.stream().map(Reserva::getFuncion).map(Funcion::getPrecio).mapToDouble(Double::doubleValue).sum();

            List<Asiento> asientosReservados = reservas.stream().map(Reserva::getAsiento).collect(Collectors.toList());

            Funcion funcion = reservas.stream().map(Reserva::getFuncion).distinct().findFirst().orElse(new Funcion());

            List<Asiento> asientosTotales = funcion.getSala().getAsientos();

            Integer fila = asientosTotales.stream().map(Asiento::getFila).max(Comparator.comparingInt(o -> o)).orElse(0);

            Integer columna = asientosTotales.stream().map(Asiento::getColumna).max(Comparator.comparingInt(o -> o)).orElse(0);

            AsientoViewModel[][] asientoViewModels = servicioReserva.formatoSala(funcion.getId(), fila, columna);

            List<AsientoDto> asientosReservadosDto = new ArrayList<>();
            for (Asiento asientoReservado :
                    asientosReservados) {
                AsientoViewModel asientoViewModel = Arrays.stream(asientoViewModels).flatMap(Arrays::stream)
                        .collect(Collectors.toList())
                        .stream()
                        .filter(avm -> avm != null && avm.getId().equals(asientoReservado.getId()))
                        .findFirst().orElse(new AsientoViewModel());

                AsientoDto asientoDto = new AsientoDto();
                asientoDto.setFila(asientoReservado.getFila());
                asientoDto.setColumna(asientoViewModel.getColumna());
                asientosReservadosDto.add(asientoDto);
            }

            mm.addAttribute("numeroTicket", numeroTicket);
            mm.addAttribute("precioTotal", precioTotal);
            mm.addAttribute("asientos", asientosReservadosDto);
            mm.addAttribute("funcion", funcion);

            mv.addAllObjects(mm);
            mv.setViewName("Reserva/detalle");
        }

        return mv;
    }
}
