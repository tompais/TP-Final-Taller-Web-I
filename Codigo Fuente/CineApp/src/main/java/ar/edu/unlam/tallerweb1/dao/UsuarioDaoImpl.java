package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.Models.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

// implelemtacion del DAO de usuarios, la anotacion @Repository indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.dao
// para encontrar esta clase.
@Repository("usuarioDao")
public class UsuarioDaoImpl implements UsuarioDao {

	// Como todo dao maneja acciones de persistencia, normalmente estará inyectado el session factory de hibernate
	// el mismo está difinido en el archivo hibernateContext.xml
	@Inject
    private SessionFactory sessionFactory;

	@Override
	public Usuario consultarUsuario(Usuario usuario) {

		// Se obtiene la sesion asociada a la transaccion iniciada en el servicio que invoca a este metodo y se crea un criterio
		// de busqueda de Usuario donde el email y password sean iguales a los del objeto recibido como parametro
		// uniqueResult da error si se encuentran más de un resultado en la busqueda.
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("email", usuario.getEmail()))
				.add(Restrictions.eq("uPassword", usuario.getuPassword()))
				.uniqueResult();
	}

	@Override
	public void realizarRegistro(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		
		session.save(usuario);		
	}

	@Override
	public Usuario loguearUsuario(String emailOrNick, String password) {
		return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class, "usuario")
				.add(Restrictions.and(Restrictions.or(Restrictions.like("username", emailOrNick), Restrictions.like("email", emailOrNick)), Restrictions.like("uPassword", password)))
				.uniqueResult();
	}

	@Override
	public Usuario getUsuarioByEmailOrUsername(String email, String username) {
		return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class, "usuario")
				.add(Restrictions.or(Restrictions.ilike("email", email), Restrictions.ilike("username", username)))
				.uniqueResult();
	}

}
