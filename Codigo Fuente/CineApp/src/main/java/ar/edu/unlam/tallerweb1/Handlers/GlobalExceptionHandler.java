package ar.edu.unlam.tallerweb1.Handlers;

import ar.edu.unlam.tallerweb1.Exceptions.CineAppException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CineAppException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public final String handleControlledExceptions(CineAppException ex) {
        logger.error("Error " + ex.getCodigoError().getValor() + ": '" + ex.getMessage() + "' en la línea " + ex.getStackTrace()[0].getLineNumber() + " en el archivo " + ex.getStackTrace()[0].getFileName() + "\nStackTrace: " + Arrays.toString(ex.getStackTrace()) + "\n\n");
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public final String handleNotControlledExceptions(Exception ex) {
        logger.error("Excepción no controlada: '" + ex.getMessage() + "' en la línea " + ex.getStackTrace()[0].getLineNumber() + " en el archivo " + ex.getStackTrace()[0].getFileName() + "\n\nStackTrace: " + Arrays.toString(ex.getStackTrace())  + "\n\n");
        return ex.getMessage();
    }
}
