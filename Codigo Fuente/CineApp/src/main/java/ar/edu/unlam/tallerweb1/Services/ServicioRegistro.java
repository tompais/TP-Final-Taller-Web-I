package ar.edu.unlam.tallerweb1.Services;

import java.security.NoSuchAlgorithmException;

import ar.edu.unlam.tallerweb1.Models.Usuario;

public interface ServicioRegistro {
	boolean realizarRegistro (Usuario usuario) throws NoSuchAlgorithmException;
}
