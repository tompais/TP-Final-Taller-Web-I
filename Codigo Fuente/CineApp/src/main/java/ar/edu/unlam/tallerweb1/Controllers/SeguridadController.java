package ar.edu.unlam.tallerweb1.Controllers;

import ar.edu.unlam.tallerweb1.Models.Rol;
import ar.edu.unlam.tallerweb1.Models.Usuario;
import ar.edu.unlam.tallerweb1.Services.ServicioLogin;
import ar.edu.unlam.tallerweb1.Services.ServicioRegistro;
import ar.edu.unlam.tallerweb1.ViewModels.UsuarioViewModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SeguridadController {
	@Inject
	private ServicioLogin servicioLogin;
	
	@Inject
	private ServicioRegistro servicioRegistro;
	
    @RequestMapping(path = "/signin", method = RequestMethod.GET)
    public ModelAndView irALogin(HttpServletRequest request) {
    	ModelMap modelo = new ModelMap();
    	
    	if(request.getSession().getAttribute("email") != null)
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
			request.getSession().setAttribute("email", usuarioBuscado.getEmail());
			request.getSession().setAttribute("rol", usuarioBuscado.getRol().getNombre());
			System.out.println("ENTREEEEE!!!");
			return new ModelAndView("redirect:/");
		}
		
		return new ModelAndView("Seguridad/signin");
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
