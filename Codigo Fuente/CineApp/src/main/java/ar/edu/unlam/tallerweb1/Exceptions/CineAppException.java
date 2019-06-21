package ar.edu.unlam.tallerweb1.Exceptions;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;

public abstract class CineAppException extends Exception {

    private CodigoError codigoError;

    CineAppException(String message, CodigoError codigoError) {
        super(message);
        this.codigoError = codigoError;
    }

    public CodigoError getCodigoError() {
        return codigoError;
    }
}
