package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.Models.CodigoReferido;
import ar.edu.unlam.tallerweb1.dao.CodigoReferidoDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service("servicioCodigoReferido")
@Transactional
public class ServicioCodigoReferidoImpl implements ServicioCodigoReferido {
    @Inject
    CodigoReferidoDao codigoReferidoDao;

    @Override
    public void crearCodigo(String codigo) {
        CodigoReferido codigoReferido = new CodigoReferido();
        codigoReferido.setCodigo(codigo);
        codigoReferido.setFechaBaja(null);
        codigoReferidoDao.crearCodigo(codigoReferido);
    }

    @Override
    public void darDeBajaCodigo(String codigo) {
        CodigoReferido codigoReferido = codigoReferidoDao.getCodigoReferidoActivoByCodigo(codigo);
        codigoReferido.setFechaBaja(Timestamp.valueOf(LocalDateTime.now()));
        codigoReferidoDao.actualizarCodigoReferido(codigoReferido);
    }

    @Override
    public Boolean existeCodigoReferidoInactivo(String codigo) {
        return codigoReferidoDao.getCodigoReferidoInactivoByCodigo(codigo) != null;
    }
}
