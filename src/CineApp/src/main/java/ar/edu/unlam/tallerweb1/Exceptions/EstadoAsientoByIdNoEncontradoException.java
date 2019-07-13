package ar.edu.unlam.tallerweb1.Exceptions;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;

public class EstadoAsientoByIdNoEncontradoException extends CineAppException {
    public EstadoAsientoByIdNoEncontradoException(String message, CodigoError codigoError) {
        super(message, codigoError);
    }
}
