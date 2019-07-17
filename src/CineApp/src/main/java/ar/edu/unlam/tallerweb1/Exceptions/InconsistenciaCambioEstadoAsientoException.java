package ar.edu.unlam.tallerweb1.Exceptions;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;

public class InconsistenciaCambioEstadoAsientoException extends CineAppException {
    public InconsistenciaCambioEstadoAsientoException(String message, CodigoError codigoError) {
        super(message, codigoError);
    }
}
