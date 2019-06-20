package ar.edu.unlam.tallerweb1.excepciones;

import ar.edu.unlam.tallerweb1.enumerables.CodigoError;

public class RecursoNoEncontradoException extends CineAppException {

    public RecursoNoEncontradoException(String message, CodigoError codigoError) {
        super(message, codigoError);
    }
}
