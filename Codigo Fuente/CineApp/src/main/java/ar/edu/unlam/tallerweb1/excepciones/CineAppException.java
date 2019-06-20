package ar.edu.unlam.tallerweb1.excepciones;

import ar.edu.unlam.tallerweb1.enumerables.CodigoError;

public abstract class CineAppException extends Exception {
    protected CodigoError codigoError;

    public CineAppException(String message, CodigoError codigoError) {
        super("Error " + codigoError + ": " + message);
        this.codigoError = codigoError;
    }

    public CodigoError getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(CodigoError codigoError) {
        this.codigoError = codigoError;
    }
}
