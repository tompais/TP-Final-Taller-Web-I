package ar.edu.unlam.tallerweb1.Services;

public interface ServicioCodigoReferido {
    void crearCodigo(String codigo);
    void darDeBajaCodigo(String codigo);
    Boolean existeCodigoReferidoInactivo(String codigo);
}
