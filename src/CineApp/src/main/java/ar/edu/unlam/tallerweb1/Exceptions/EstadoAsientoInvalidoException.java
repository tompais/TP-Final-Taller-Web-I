package ar.edu.unlam.tallerweb1.Exceptions;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;

public class EstadoAsientoInvalidoException extends CineAppException {
    public EstadoAsientoInvalidoException(String message, CodigoError codigoError) {
        super(message, codigoError);
    }
}
