package ar.edu.unlam.tallerweb1.Exceptions;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;

public class FechaUltimaFuncionMenorAFechaActualException extends CineAppException {
    public FechaUltimaFuncionMenorAFechaActualException(String message, CodigoError codigoError) {
        super(message, codigoError);
    }
}
