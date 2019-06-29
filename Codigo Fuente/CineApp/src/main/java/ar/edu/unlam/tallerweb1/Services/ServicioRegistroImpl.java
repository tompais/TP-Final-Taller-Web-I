package ar.edu.unlam.tallerweb1.Services;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.Models.Usuario;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;

@Service("servicioRegistro")
@Transactional
public class ServicioRegistroImpl implements ServicioRegistro{

	@Inject
	private UsuarioDao servicioRegistroDao;
	
	@Override
	public void realizarRegistro(Usuario usuario) {
		servicioRegistroDao.realizarRegistro(usuario);		
	}

}
