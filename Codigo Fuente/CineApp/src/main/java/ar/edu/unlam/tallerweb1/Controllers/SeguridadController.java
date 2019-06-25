package ar.edu.unlam.tallerweb1.Controllers;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.Models.Usuario;
import ar.edu.unlam.tallerweb1.Services.ServicioLogin;

@Controller
public class SeguridadController {
	@Inject
	private ServicioLogin servicioLogin;
	
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
		ModelMap model = new ModelMap();

		Usuario usuarioBuscado = servicioLogin.consultarUsuario(usuario);
		
		if (usuarioBuscado != null)
		{
			request.getSession().setAttribute("username", usuarioBuscado.getUsername());
			request.getSession().setAttribute("rol", usuarioBuscado.getRol().getNombre());
			request.getSession().setAttribute("id", usuarioBuscado.getId());
			return new ModelAndView("redirect:/prueba2");
		}
		else
			model.put("error", "Usuario o clave incorrecta");
		
		return new ModelAndView("Seguridad/signin", model);
	}
    
    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public ModelAndView irARegistrar() {
        return new ModelAndView("Seguridad/signup");
    }
    
    @RequestMapping(path = "/signout", method = RequestMethod.GET)
    public ModelAndView cerrarSesion(HttpServletRequest request) {
    	
    	if(request.getSession().getAttribute("username") != null)
    		request.getSession().invalidate();
    	
    	return new ModelAndView("redirect:/");
    }
}
