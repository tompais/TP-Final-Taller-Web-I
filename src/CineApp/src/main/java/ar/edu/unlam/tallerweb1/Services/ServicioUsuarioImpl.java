package ar.edu.unlam.tallerweb1.Services;

import ar.edu.unlam.tallerweb1.Enums.CodigoError;
import ar.edu.unlam.tallerweb1.Exceptions.RegistroInvalidoException;
import ar.edu.unlam.tallerweb1.Exceptions.UsuarioInvalidoException;
import ar.edu.unlam.tallerweb1.Exceptions.UsuarioNoEncontradoException;
import ar.edu.unlam.tallerweb1.Helpers.ConstanteHelper;
import ar.edu.unlam.tallerweb1.Helpers.EncryptorHelper;
import ar.edu.unlam.tallerweb1.Models.Usuario;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("servicioUsuario")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

    @Inject
    private UsuarioDao usuarioDao;

    @Override
    public Boolean existeUsuarioByEmailAndUsername(String email, String username) {
        return usuarioDao.getUsuarioByEmailOrUsername(email, username) != null;
    }

    @Override
    public Usuario consultarUsuario(Usuario usuario) throws UsuarioNoEncontradoException {
        Usuario u = usuarioDao.consultarUsuario(usuario);

        if (u == null)
            throw new UsuarioNoEncontradoException("No se ha podido encontrar su usuario en nuestro sistema para realizar la reserva", CodigoError.USUARIONOENCONTRADO);

        return u;
    }

    @Override
    public Usuario loguearUsuario(String emailOrNick, String password) throws NoSuchAlgorithmException, UsuarioInvalidoException, UsuarioNoEncontradoException {
        if((!validarUsuarioEmail(emailOrNick) && !validarUsuarioUsername(emailOrNick)) || !validarUsuarioPassword(password))
            throw new UsuarioInvalidoException("Usuario y/o contraseña inválido/s. Por favor, revise sus datos y vuelva a intentarlo", CodigoError.USUARIOINVALIDO);

        Usuario usuario = usuarioDao.loguearUsuario(emailOrNick, EncryptorHelper.encryptToSha1(password));

        if(usuario == null)
            throw new UsuarioNoEncontradoException("No se ha encontrado un usuario registrado con los datos ingresados", CodigoError.USUARIONOENCONTRADO);

        return usuario;
    }

    @Override
    public void realizarRegistro(Usuario usuario) throws RegistroInvalidoException {
        if(!validarUsuario(usuario))
            throw new RegistroInvalidoException("Error al realizar el registro. Verifique que sus datos estén correctos", CodigoError.REGISTROINVALIDO);

        usuarioDao.realizarRegistro(usuario);
    }

    @Override
    public boolean validarUsuario(Usuario usuario) {
        return validarUsuarioNombre(usuario.getNombre().trim())
                && validarUsuarioApellido(usuario.getApellido().trim())
                && validarUsuarioUsername(usuario.getUsername().trim())
                && validarUsuarioEmail(usuario.getEmail().trim())
                && validarUsuarioPassword(usuario.getuPassword().trim())
                && esUsuarioMayor(usuario.getFechaNacimiento());
    }

    @Override
    public boolean validarUsuarioNombre(String nombre) {
        return nombre != null && nombre.length() >= 3 && nombre.length() <= 10 && Pattern.compile(ConstanteHelper.REGEXLETRASYESPACIOS, Pattern.CASE_INSENSITIVE).matcher(nombre).find();
    }

    @Override
    public boolean validarUsuarioApellido(String apellido) {
        return apellido != null && apellido.length() >= 3 && apellido.length() <= 15 && Pattern.compile(ConstanteHelper.REGEXLETRASYESPACIOS, Pattern.CASE_INSENSITIVE).matcher(apellido).find();
    }

    @Override
    public boolean validarUsuarioUsername(String username) {
        return username != null && username.length() >= 3 && username.length() <= 15 && Pattern.compile(ConstanteHelper.REGEXLETRASYNUMEROS, Pattern.CASE_INSENSITIVE).matcher(username).find();
    }

    @Override
    public boolean validarUsuarioEmail(String email) {
        return email != null && Pattern.compile(ConstanteHelper.REGEXEMAIL, Pattern.CASE_INSENSITIVE).matcher(email).find();
    }

    @Override
    public boolean validarUsuarioPassword(String password) {
        return password != null && password.length() >= 6 && password.length() <= 15 && Pattern.compile(ConstanteHelper.REGEXLETRASYNUMEROS, Pattern.CASE_INSENSITIVE).matcher(password).find();
    }

    @Override
    public boolean esUsuarioMayor(Date fechaNacimiento) {
        return ChronoUnit.YEARS.between(fechaNacimiento.toLocalDate(), LocalDate.now()) >= 18;
    }
}
