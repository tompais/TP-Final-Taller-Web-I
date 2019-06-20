package ar.edu.unlam.tallerweb1.excepciones;

import ar.edu.unlam.tallerweb1.enumerables.CodigoError;

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
