package ar.edu.unlam.tallerweb1.Exceptions;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;

public class PosicionAsientoInvalidoException extends CineAppException {
    public PosicionAsientoInvalidoException(String message, CodigoError codigoError) {
        super(message, codigoError);
    }
}
