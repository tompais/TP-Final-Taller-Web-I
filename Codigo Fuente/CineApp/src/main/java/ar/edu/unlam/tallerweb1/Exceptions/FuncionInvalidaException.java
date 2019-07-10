package ar.edu.unlam.tallerweb1.Exceptions;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;

public class FuncionInvalidaException extends CineAppException {
    public FuncionInvalidaException(String message, CodigoError codigoError) {
        super(message, codigoError);
    }
}
