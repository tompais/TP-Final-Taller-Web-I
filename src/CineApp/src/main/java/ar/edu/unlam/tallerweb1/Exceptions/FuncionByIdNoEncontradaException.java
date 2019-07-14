package ar.edu.unlam.tallerweb1.Exceptions;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;

public class FuncionByIdNoEncontradaException extends CineAppException {
    public FuncionByIdNoEncontradaException(String message, CodigoError codigoError) {
        super(message, codigoError);
    }
}
