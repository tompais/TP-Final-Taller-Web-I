package ar.edu.unlam.tallerweb1.Exceptions;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;

public class RegistroInvalidoException extends CineAppException{
	public RegistroInvalidoException(String message, CodigoError codigoError) {
		super(message, codigoError);
	}

}
