package ar.edu.unlam.tallerweb1.Exceptions;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;

public class FuncionByConfiguracionNoEncontradaException extends CineAppException {
    public FuncionByConfiguracionNoEncontradaException(String message, CodigoError codigoError) {
        super(message, codigoError);
    }
}
