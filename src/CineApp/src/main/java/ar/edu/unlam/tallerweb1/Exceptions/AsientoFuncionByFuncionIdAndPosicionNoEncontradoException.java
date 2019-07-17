package ar.edu.unlam.tallerweb1.Exceptions;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;

public class AsientoFuncionByFuncionIdAndPosicionNoEncontradoException extends CineAppException {
    public AsientoFuncionByFuncionIdAndPosicionNoEncontradoException(String message, CodigoError codigoError) {
        super(message, codigoError);
    }
}
