package ar.edu.unlam.tallerweb1.Exceptions;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;

public class RecursoNoEncontradoException extends CineAppException {

    public RecursoNoEncontradoException(String message, CodigoError codigoError) {
        super(message, codigoError);
    }
}
