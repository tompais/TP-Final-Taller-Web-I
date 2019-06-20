package ar.edu.unlam.tallerweb1.capturadores;

import ar.edu.unlam.tallerweb1.excepciones.CineAppException;
import ar.edu.unlam.tallerweb1.modelos.Cine;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CineAppException.class)
    public final ModelAndView handleAllExceptions(CineAppException ex) {
        logger.error("Error " + ex.getCodigoError().getValor() + ": '" + ex.getMessage() + "' en la línea " + ex.getStackTrace()[0].getLineNumber() + " en el archivo " + ex.getStackTrace()[0].getFileName() + "\n\nStackTrace: " + Arrays.toString(ex.getStackTrace()));
        ModelMap mm = new ModelMap();
        mm.put("error", ex.getMessage());
        return new ModelAndView("Error/404", mm);
    }
}
