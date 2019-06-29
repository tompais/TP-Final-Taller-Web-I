package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service("servicioUsuario")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

    @Inject
    private UsuarioDao usuarioDao;

    @Override
    public Boolean existeUsuarioByEmailAndUsername(String email, String username) {
        return usuarioDao.getUsuarioByEmailOrUsername(email, username) != null;
    }
}
