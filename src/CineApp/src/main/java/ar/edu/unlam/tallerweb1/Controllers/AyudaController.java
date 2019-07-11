package ar.edu.unlam.tallerweb1.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AyudaController extends BaseController {
    @RequestMapping(path = "/about", method = RequestMethod.GET)
    public ModelAndView irAAbout() {
        return new ModelAndView("Ayuda/about");
    }

    @RequestMapping(path = "/faq", method = RequestMethod.GET)
    public ModelAndView irAFaq() {
        return new ModelAndView("Ayuda/faq");
    }
}
