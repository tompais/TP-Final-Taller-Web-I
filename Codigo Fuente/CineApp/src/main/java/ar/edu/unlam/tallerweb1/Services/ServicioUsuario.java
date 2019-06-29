package ar.edu.unlam.tallerweb1.Services;

public interface ServicioUsuario {
    Boolean existeUsuarioByEmailAndUsername(String email, String username);
}
