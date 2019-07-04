package ar.edu.unlam.tallerweb1.Exceptions;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;

public class UsuarioNoEncontradoException extends CineAppException {
    public UsuarioNoEncontradoException(String message, CodigoError codigoError) {
        super(message, codigoError);
    }
}
