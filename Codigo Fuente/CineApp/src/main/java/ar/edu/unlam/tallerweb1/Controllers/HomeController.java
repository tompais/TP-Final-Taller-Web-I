package ar.edu.unlam.tallerweb1.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView irAInicio() {
        return new ModelAndView("Home/inicio");
    }
}