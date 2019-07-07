package ar.edu.unlam.tallerweb1.Controllers;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;
import ar.edu.unlam.tallerweb1.Enums.Rol;
import ar.edu.unlam.tallerweb1.Exceptions.*;
import ar.edu.unlam.tallerweb1.Models.Usuario;
import ar.edu.unlam.tallerweb1.Services.ServicioUsuario;
import ar.edu.unlam.tallerweb1.ViewModels.LoginViewModel;
import ar.edu.unlam.tallerweb1.ViewModels.RegistrarViewModel;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

@Controller
public class SeguridadController {
	@Inject
	private ServicioUsuario servicioUsuario;
	
    @RequestMapping(path = "/signin", method = RequestMethod.GET)
    public ModelAndView irALogin(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();

    	if(request.getSession().getAttribute("email") != null)
    		mv.setViewName("redirect:/");
    	else
    	    mv.setViewName("Seguridad/signin");

        return mv;
    }

    private void setSessionUsuario(HttpServletRequest request, Usuario usuario) {
		request.getSession().setAttribute("email", usuario.getEmail());
		request.getSession().setAttribute("rol", usuario.getRol().getId());
		request.getSession().setAttribute("username", usuario.getUsername());
	}

    @ResponseBody
    @RequestMapping(path = "/loguearUsuario", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String loguearUsuario(@ModelAttribute @Validated LoginViewModel loginViewModel, HttpServletRequest request) throws NoSuchAlgorithmException, RecursoNoEncontradoException, UsuarioInvalidoException, UsuarioNoEncontradoException {

		Usuario usuarioBuscado = servicioUsuario.loguearUsuario(loginViewModel.getEmailOrNick(), loginViewModel.getPassword());

		setSessionUsuario(request, usuarioBuscado);

		return new Gson().toJson(usuarioBuscado);
	}
    
    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public ModelAndView irARegistrar(HttpServletRequest request) {

    	ModelAndView mv = new ModelAndView();

    	if(request.getSession().getAttribute("email") != null)
    		mv.setViewName("redirect:/");
    	else
    		mv.setViewName("Seguridad/signup");
    	
        return mv;
    }

    @ResponseBody
    @RequestMapping(path = "/registrarUsuario", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String registrarUsuario(@ModelAttribute @Validated RegistrarViewModel registrarViewModel, HttpServletRequest request) throws UsuarioDuplicadoException, NoSuchAlgorithmException, RegistroInvalidoException {

		if(servicioUsuario.existeUsuarioByEmailAndUsername(registrarViewModel.getEmail(), registrarViewModel.getUsername()))
			throw new UsuarioDuplicadoException("Ya se encuentra registrado un usuario con el correo electrónico '" + registrarViewModel.getEmail() + "' y/o nombre de usuario '" + registrarViewModel.getUsername() + "'", CodigoError.USUARIODUPLICADO);

		Usuario modelo = new Usuario();

		modelo.setEmail(registrarViewModel.getEmail());
		modelo.setuPassword(registrarViewModel.getuPassword());
		ar.edu.unlam.tallerweb1.Models.Rol rol = new ar.edu.unlam.tallerweb1.Models.Rol();
		rol.setId(Rol.USUARIO.getId());
		rol.setNombre(Rol.USUARIO.getNombre());
		modelo.setRol(rol);

		modelo.setApellido(registrarViewModel.getApellido());
		modelo.setUsername(registrarViewModel.getUsername());
		modelo.setNombre(registrarViewModel.getNombre());

		modelo.setFechaNacimiento(registrarViewModel.getFechaNacimiento());

		servicioUsuario.realizarRegistro(modelo);

		setSessionUsuario(request, modelo);

		return new Gson().toJson(modelo);
    }

    @RequestMapping(path = "/signout", method = RequestMethod.GET)
    public ModelAndView cerrarSesion(HttpServletRequest request) {
    	
    	if(request.getSession().getAttribute("email") != null)
    		request.getSession().invalidate();
    	
    	return new ModelAndView("redirect:/");
    }
}
