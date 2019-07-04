package ar.edu.unlam.tallerweb1.Services;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.Helpers.EncryptorHelper;
import ar.edu.unlam.tallerweb1.Models.Usuario;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;

@Service("servicioRegistro")
@Transactional
public class ServicioRegistroImpl implements ServicioRegistro{

	@Inject
	private UsuarioDao servicioRegistroDao;
	
	@Override
	public boolean realizarRegistro(Usuario usuario) throws NoSuchAlgorithmException {
		
		final Pattern VALIDAR_REGEX_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		final Pattern VALIDAR_REGEX_LETRAS_Y_NUMEROS = Pattern.compile("^[a-zA-Z0-9]+$", Pattern.CASE_INSENSITIVE);
		final Pattern VALIDAR_REGEX_LETRAS_Y_ESPACIOSNOMB = Pattern.compile("^[a-zA-Z\\s]{3,10}$", Pattern.CASE_INSENSITIVE);
		final Pattern VALIDAR_REGEX_LETRAS_Y_ESPACIOSAPE = Pattern.compile("^[a-zA-Z\\s]{3,15}$", Pattern.CASE_INSENSITIVE);
		
		Matcher matcherEmail = VALIDAR_REGEX_EMAIL.matcher(usuario.getEmail());
		Matcher matcherLetrasYNumeros = VALIDAR_REGEX_LETRAS_Y_NUMEROS.matcher(usuario.getUsername());
		Matcher matcherLetrasYEspaciosNombre = VALIDAR_REGEX_LETRAS_Y_ESPACIOSNOMB.matcher(usuario.getNombre());
		Matcher matcherLetrasYEspaciosApellido = VALIDAR_REGEX_LETRAS_Y_ESPACIOSAPE.matcher(usuario.getApellido());
		
		if(usuario.getEmail() == null || usuario.getEmail().length() == 0 || usuario.getEmail() == "")
			return false;
	    else if(!matcherEmail.find())
	    	return false;
		
		if(usuario.getUsername() == null || usuario.getUsername().length() == 0 || usuario.getUsername() == "")
			return false;
	    else if(!matcherLetrasYNumeros.find())
	    	return false;
		
		if(usuario.getNombre() == null || usuario.getNombre().length() == 0 || usuario.getNombre() == "")
			return false;
	    else if(!matcherLetrasYEspaciosNombre.find())
	    	return false;
		
		if(usuario.getApellido() == null || usuario.getApellido().length() == 0 || usuario.getApellido() == "")
			return false;
	    else if(!matcherLetrasYEspaciosApellido.find())
	    	return false;
		
		matcherLetrasYNumeros = VALIDAR_REGEX_LETRAS_Y_NUMEROS.matcher(usuario.getuPassword());
		
		if (usuario.getuPassword() == null || usuario.getuPassword().length() == 0 || usuario.getuPassword() == "")
			return false;
	    else if(usuario.getuPassword().length() < 6 || usuario.getuPassword().length() > 15)
	    	return false;
	    else if(!matcherLetrasYNumeros.find())
	    	return false;
		
		usuario.setuPassword(EncryptorHelper.encryptToSha1(usuario.getuPassword()));
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(usuario.getFechaNacimiento());
		
		int anio = cal.get(Calendar.YEAR);
		int anioActual = Calendar.getInstance().get(Calendar.YEAR);
		
		if((anioActual - anio) < 18)
			return false;
		
		servicioRegistroDao.realizarRegistro(usuario);
		return true;
	}

}
