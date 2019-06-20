package ar.edu.unlam.tallerweb1.excepciones;

import ar.edu.unlam.tallerweb1.enumerables.CodigoError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecursoNoEncontradoException extends CineAppException {
    public RecursoNoEncontradoException(String message, CodigoError codigoError) {
        super(message, codigoError);
    }
}
