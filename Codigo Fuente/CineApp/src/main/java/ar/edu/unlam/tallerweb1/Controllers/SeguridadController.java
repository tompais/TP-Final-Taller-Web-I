package ar.edu.unlam.tallerweb1.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SeguridadController {
    @RequestMapping(path = "/signin", method = RequestMethod.GET)
    public ModelAndView irALogin() {
        return new ModelAndView("Seguridad/signin");
    }

    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public ModelAndView irARegistrar() {
        return new ModelAndView("Seguridad/signup");
    }
}
