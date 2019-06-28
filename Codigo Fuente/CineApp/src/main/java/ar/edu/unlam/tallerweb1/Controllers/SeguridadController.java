package ar.edu.unlam.tallerweb1.Controllers;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;
import ar.edu.unlam.tallerweb1.Exceptions.RecursoNoEncontradoException;
import ar.edu.unlam.tallerweb1.Helpers.EncryptorHelper;
import ar.edu.unlam.tallerweb1.Models.Rol;
import ar.edu.unlam.tallerweb1.Models.Usuario;
import ar.edu.unlam.tallerweb1.Services.ServicioLogin;
import ar.edu.unlam.tallerweb1.Services.ServicioRegistro;
import ar.edu.unlam.tallerweb1.ViewModels.LoginViewModel;
import com.google.gson.Gson;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class SeguridadController {
	@Inject
	private ServicioLogin servicioLogin;
	
	@Inject
	private ServicioRegistro servicioRegistro;
	
    @RequestMapping(path = "/signin", method = RequestMethod.GET)
    public ModelAndView irALogin(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();

    	if(request.getSession().getAttribute("username") != null)
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
			request.getSession().setAttribute("username", usuarioBuscado.getUsername());
			request.getSession().setAttribute("rol", usuarioBuscado.getRol().getNombre());
			request.getSession().setAttribute("id", usuarioBuscado.getId());
		}

		return new Gson().toJson(usuarioBuscado);
	}
    
    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public ModelAndView irARegistrar(HttpServletRequest request) {
    	ModelMap modelo = new ModelMap();
    	
    	if(request.getSession().getAttribute("username") != null)
    		return new ModelAndView("redirect:/");
    	
    	Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
    	
        return new ModelAndView("Seguridad/signup", modelo);
    }
    
    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }
    
    @RequestMapping(path = "/registro", method = RequestMethod.POST)
    public ModelAndView registro(@ModelAttribute("usuario") Usuario usuario, BindingResult result, HttpServletRequest request) {
    	
    	if(servicioLogin.consultarUsuario(usuario) == null) {
    		System.out.println("Resultado: " + result);
    		Rol rol = new Rol();
    		rol.setId((long)1);
    		rol.setNombre("Usuario");
    		usuario.setRol(rol);
    		
    		servicioRegistro.realizarRegistro(usuario);
    		
    		request.getSession().setAttribute("username", usuario.getUsername());
			request.getSession().setAttribute("rol", usuario.getRol().getNombre());
			request.getSession().setAttribute("id", usuario.getId());
    	}
    	
        return new ModelAndView("Home/inicio");
    }
    
    @RequestMapping(path = "/signout", method = RequestMethod.GET)
    public ModelAndView cerrarSesion(HttpServletRequest request) {
    	
    	if(request.getSession().getAttribute("username") != null)
    		request.getSession().invalidate();
    	
    	return new ModelAndView("redirect:/");
    }
}
