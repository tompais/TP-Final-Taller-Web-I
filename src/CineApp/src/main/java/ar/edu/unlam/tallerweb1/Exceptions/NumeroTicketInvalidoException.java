package ar.edu.unlam.tallerweb1.Exceptions;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;

public class NumeroTicketInvalidoException extends CineAppException {
    public NumeroTicketInvalidoException(String message, CodigoError codigoError) {
        super(message, codigoError);
    }
}
