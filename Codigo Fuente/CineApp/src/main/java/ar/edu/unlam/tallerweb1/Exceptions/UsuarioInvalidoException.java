package ar.edu.unlam.tallerweb1.Exceptions;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;

public class UsuarioInvalidoException extends CineAppException {
    public UsuarioInvalidoException(String message, CodigoError codigoError) {
        super(message, codigoError);
    }
}
