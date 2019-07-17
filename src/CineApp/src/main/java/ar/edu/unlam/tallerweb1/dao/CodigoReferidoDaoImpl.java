package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.Models.CodigoReferido;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository("codigoReferidoDao")
public class CodigoReferidoDaoImpl implements CodigoReferidoDao {
    @Inject
    private SessionFactory sessionFactory;

    @Override
    public void crearCodigo(CodigoReferido codigo) {
        sessionFactory.getCurrentSession().save(codigo);
    }

    @Override
    public void actualizarCodigoReferido(CodigoReferido codigo) {
        sessionFactory.getCurrentSession().update(codigo);
    }

    @Override
    public CodigoReferido getCodigoReferidoInactivoByCodigo(String codigo) {
        return (CodigoReferido) sessionFactory.getCurrentSession().createCriteria(CodigoReferido.class, "codigoReferido")
                .add(Restrictions.and(Restrictions.ilike("codigo", codigo), Restrictions.isNotNull("fechaBaja")))
                .uniqueResult();
    }

    @Override
    public CodigoReferido getCodigoReferidoActivoByCodigo(String codigo) {
        return (CodigoReferido) sessionFactory.getCurrentSession().createCriteria(CodigoReferido.class, "codigoReferido")
                .add(Restrictions.and(Restrictions.ilike("codigo", codigo), Restrictions.isNull("fechaBaja")))
                .uniqueResult();
    }
}
