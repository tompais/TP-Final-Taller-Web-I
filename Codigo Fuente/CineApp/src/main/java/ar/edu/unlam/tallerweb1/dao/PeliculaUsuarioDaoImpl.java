package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.Models.PeliculaUsuario;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Repository("peliculaUsuarioDao")
public class PeliculaUsuarioDaoImpl implements PeliculaUsuarioDao {

    @Inject
    private SessionFactory sessionFactory;


    @Override
    public Double getPromedioCalificacionByPeliculaId(Long id) {

        return (Double) sessionFactory.getCurrentSession().createCriteria(PeliculaUsuario.class, "peliculaUsuario")
                .createAlias("pelicula", "peliculaBuscada")
                .add(Restrictions.and(Restrictions.eq("peliculaBuscada.id", id), Restrictions.isNotNull("calificacion")))
                .setProjection(Projections.avg("calificacion")).uniqueResult();
    }
}
