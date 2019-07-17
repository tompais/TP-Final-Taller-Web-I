package ar.edu.unlam.tallerweb1.Exceptions;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;

public class UsuarioDuplicadoException extends CineAppException {
    public UsuarioDuplicadoException(String message, CodigoError codigoError) {
        super(message, codigoError);
    }
}
