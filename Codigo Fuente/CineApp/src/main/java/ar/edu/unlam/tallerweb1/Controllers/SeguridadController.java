package ar.edu.unlam.tallerweb1.Controllers;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.Models.Rol;
import ar.edu.unlam.tallerweb1.Models.Usuario;
import ar.edu.unlam.tallerweb1.Services.ServicioLogin;
import ar.edu.unlam.tallerweb1.Services.ServicioRegistro;

@Controller
public class SeguridadController {
	@Inject
	private ServicioLogin servicioLogin;
	
	@Inject
	private ServicioRegistro servicioRegistro;
	
    @RequestMapping(path = "/signin", method = RequestMethod.GET)
    public ModelAndView irALogin(HttpServletRequest request) {
    	ModelMap modelo = new ModelMap();
    	
    	if(request.getSession().getAttribute("username") != null)
    		return new ModelAndView("redirect:/");
    	
    	Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
    	
        return new ModelAndView("Seguridad/signin", modelo);
    }

    @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		Usuario usuarioBuscado = servicioLogin.consultarUsuario(usuario);
		
		if (usuarioBuscado != null)
		{
			request.getSession().setAttribute("username", usuarioBuscado.getUsername());
			request.getSession().setAttribute("rol", usuarioBuscado.getRol().getNombre());
			request.getSession().setAttribute("id", usuarioBuscado.getId());
			return new ModelAndView("redirect:/");
		}
		
		return new ModelAndView("Seguridad/signin");
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
    	
    	if(servicioLogin.consultarUsuario(usuario) == null)
    	{
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
