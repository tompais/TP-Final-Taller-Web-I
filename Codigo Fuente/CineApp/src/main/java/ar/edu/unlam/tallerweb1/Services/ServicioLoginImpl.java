package ar.edu.unlam.tallerweb1.Services;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.Helpers.EncryptorHelper;
import ar.edu.unlam.tallerweb1.Models.Usuario;

// Implelemtacion del Servicio de usuarios, la anotacion @Service indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.Services
// para encontrar esta clase.
// La anotacion @Transactional indica que se debe iniciar una transaccion de base de datos ante la invocacion de cada metodo del servicio,
// dicha transaccion esta asociada al transaction manager definido en el archivo spring-servlet.xml y el mismo asociado al session factory definido
// en hibernateCOntext.xml. De esta manera todos los metodos de cualquier dao invocados dentro de un servicio se ejecutan en la misma transaccion
@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

	@Inject
	private UsuarioDao servicioLoginDao;

	@Override
	public Usuario consultarUsuario (Usuario usuario) {
		return servicioLoginDao.consultarUsuario(usuario);
	}

	@Override
	public Usuario loguearUsuario(String emailOrNick, String password) throws NoSuchAlgorithmException {
		
		final Pattern VALIDAR_REGEX_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		final Pattern VALIDAR_REGEX_LETRAS_Y_NUMEROS = Pattern.compile("^[a-zA-Z0-9]+$", Pattern.CASE_INSENSITIVE);
		
		Matcher matcherEmail = VALIDAR_REGEX_EMAIL.matcher(emailOrNick);
		Matcher matcherLetrasYNumeros = VALIDAR_REGEX_LETRAS_Y_NUMEROS.matcher(emailOrNick);
		
		if(emailOrNick == null || emailOrNick.length() == 0 || emailOrNick == "")
			return null;
	    else if(!matcherEmail.find() && !matcherLetrasYNumeros.find())
	    	return null;
		
		matcherLetrasYNumeros = VALIDAR_REGEX_LETRAS_Y_NUMEROS.matcher(password);
		
		if (password == null || password.length() == 0 || password == "")
			return null;
	    else if(password.length() < 6 || password.length() > 15)
	    	return null;
	    else if(!matcherLetrasYNumeros.find())
	    	return null;
		
		return servicioLoginDao.loguearUsuario(emailOrNick, EncryptorHelper.encryptToSha1(password));
	}

}
