package ar.edu.unlam.tallerweb1.Controllers;

import ar.edu.unlam.tallerweb1.Helpers.EncryptorHelper;
import ar.edu.unlam.tallerweb1.Models.Rol;
import ar.edu.unlam.tallerweb1.Models.Usuario;
import ar.edu.unlam.tallerweb1.Services.ServicioLogin;
import ar.edu.unlam.tallerweb1.Services.ServicioRegistro;
import ar.edu.unlam.tallerweb1.ViewModels.LoginViewModel;
import ar.edu.unlam.tallerweb1.ViewModels.UsuarioViewModel;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
	private ServicioLogin servicioLogin;
	
	@Inject
	private ServicioRegistro servicioRegistro;
	
    @RequestMapping(path = "/signin", method = RequestMethod.GET)
    public ModelAndView irALogin(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();

    	if(request.getSession().getAttribute("email") != null)
    		mv.setViewName("redirect:/");
    	else
    	    mv.setViewName("Seguridad/signin");

        return mv;
    }

    @ResponseBody
    @RequestMapping(path = "/loguearUsuario", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String loguearUsuario(@ModelAttribute LoginViewModel loginViewModel, HttpServletRequest request) throws NoSuchAlgorithmException {

		Usuario usuarioBuscado = servicioLogin.loguearUsuario(loginViewModel.getEmailOrNick(), EncryptorHelper.encryptToSha1(loginViewModel.getPassword()));

		if (usuarioBuscado != null) {
			request.getSession().setAttribute("email", usuarioBuscado.getEmail());
			request.getSession().setAttribute("rol", usuarioBuscado.getRol().getNombre());
			request.getSession().setAttribute("username", usuarioBuscado.getUsername());
		}

		return new Gson().toJson(usuarioBuscado);
	}
    
    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public ModelAndView irARegistrar(HttpServletRequest request) {
    	ModelMap modelo = new ModelMap();
    	
    	if(request.getSession().getAttribute("email") != null)
    		return new ModelAndView("redirect:/");
    	
    	UsuarioViewModel usuario = new UsuarioViewModel();
		modelo.put("usuario", usuario);
    	
        return new ModelAndView("Seguridad/signup", modelo);
    }
    
    @RequestMapping(path = "/registro", method = RequestMethod.POST)
    public ModelAndView registro(@ModelAttribute @Validated UsuarioViewModel usuario, HttpServletRequest request) {
    	
    	Usuario modelo = new Usuario();
    	
    	modelo.setEmail(usuario.getEmail());
    	modelo.setuPassword(usuario.getuPassword());
    	
    	if(servicioLogin.consultarUsuario(modelo) == null)
    	{
    		Rol rol = new Rol();
    		rol.setId((long)1);
    		rol.setNombre("Usuario");
    		modelo.setRol(rol);
    		
    		modelo.setApellido(usuario.getApellido());
    		modelo.setUsername(usuario.getUsername());
    		modelo.setNombre(usuario.getNombre());
    		modelo.setFechaNacimiento(usuario.getFechaNacimiento());
    		
    		servicioRegistro.realizarRegistro(modelo);
    		
    		request.getSession().setAttribute("email", modelo.getEmail());
			request.getSession().setAttribute("rol", modelo.getRol().getNombre());
    	}
    	
        return new ModelAndView("Home/inicio");
    }
    
    @RequestMapping(path = "/signout", method = RequestMethod.GET)
    public ModelAndView cerrarSesion(HttpServletRequest request) {
    	
    	if(request.getSession().getAttribute("email") != null)
    		request.getSession().invalidate();
    	
    	return new ModelAndView("redirect:/");
    }
}
