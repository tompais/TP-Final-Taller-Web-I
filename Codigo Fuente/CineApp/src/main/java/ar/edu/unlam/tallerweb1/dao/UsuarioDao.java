package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.Models.Usuario;

// Interface que define los metodos del DAO de Usuarios.
public interface UsuarioDao {
	
	Usuario consultarUsuario (Usuario usuario);
	void realizarRegistro (Usuario usuario);
	Usuario loguearUsuario(String emailOrNick, String password);
	Usuario getUsuarioByEmailOrUsername(String email, String username);
}
