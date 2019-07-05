package ar.edu.unlam.tallerweb1.Exceptions;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;

public class PeliculaNoEncontradaException extends CineAppException {
    public PeliculaNoEncontradaException(String message, CodigoError codigoError) {
        super(message, codigoError);
    }
}
