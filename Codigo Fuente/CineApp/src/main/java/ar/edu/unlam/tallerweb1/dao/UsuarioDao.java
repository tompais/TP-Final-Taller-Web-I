package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelos.Usuario;

// Interface que define los metodos del DAO de Usuarios.
public interface UsuarioDao {
	
	Usuario consultarUsuario (Usuario usuario);
}
