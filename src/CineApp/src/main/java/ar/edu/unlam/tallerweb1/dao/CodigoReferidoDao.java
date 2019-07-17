package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.Models.CodigoReferido;

public interface CodigoReferidoDao {
    void crearCodigo(CodigoReferido codigo);
    void actualizarCodigoReferido(CodigoReferido codigo);
    CodigoReferido getCodigoReferidoInactivoByCodigo(String codigo);
    CodigoReferido getCodigoReferidoActivoByCodigo(String codigo);
}
