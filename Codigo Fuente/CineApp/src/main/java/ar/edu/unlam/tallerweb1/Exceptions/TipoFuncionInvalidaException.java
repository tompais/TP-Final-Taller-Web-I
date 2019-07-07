package ar.edu.unlam.tallerweb1.Exceptions;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;

public class TipoFuncionInvalidaException extends CineAppException {
    public TipoFuncionInvalidaException(String message, CodigoError codigoError) {
        super(message, codigoError);
    }
}
