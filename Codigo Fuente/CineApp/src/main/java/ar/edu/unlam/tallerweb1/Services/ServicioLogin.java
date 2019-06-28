package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.Models.Usuario;

// Interface que define los metodos del Servicio de Usuarios.
public interface ServicioLogin {

	Usuario consultarUsuario(Usuario usuario);
	Usuario loguearUsuario(String emailOrNick, String password);
}
