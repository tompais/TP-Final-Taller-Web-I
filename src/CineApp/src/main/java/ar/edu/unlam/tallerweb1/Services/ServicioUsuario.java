package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.Exceptions.RegistroInvalidoException;
import ar.edu.unlam.tallerweb1.Exceptions.UsuarioInvalidoException;
import ar.edu.unlam.tallerweb1.Exceptions.UsuarioNoEncontradoException;
import ar.edu.unlam.tallerweb1.Models.Usuario;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;

public interface ServicioUsuario {
    Boolean existeUsuarioByEmailAndUsername(String email, String username);

    Usuario consultarUsuario(Usuario usuario) throws UsuarioNoEncontradoException;

    Usuario loguearUsuario(String emailOrNick, String password) throws NoSuchAlgorithmException, UsuarioInvalidoException, UsuarioNoEncontradoException;

    void realizarRegistro(Usuario usuario) throws NoSuchAlgorithmException, RegistroInvalidoException;

    boolean validarUsuario(Usuario usuario);

    boolean validarUsuarioNombre(String nombre);

    boolean validarUsuarioApellido(String apellido);

    boolean validarUsuarioUsername(String username);

    boolean validarUsuarioEmail(String email);

    boolean validarUsuarioPassword(String password);

    boolean esUsuarioMayor(Date fechaNacimiento);
}
