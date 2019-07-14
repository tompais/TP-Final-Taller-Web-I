package ar.edu.unlam.tallerweb1.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ar.edu.unlam.tallerweb1.Enums.EstadoAsiento;
import ar.edu.unlam.tallerweb1.Models.Asiento;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.Models.AsientoFuncion;

@Repository("AsientoFuncionDao")
public class AsientoFuncionDaoImpl implements AsientoFuncionDao {
    @Inject
    private SessionFactory sessionFactory;

    @Override
    public List<AsientoFuncion> consultarDistribucionAsientosEnFuncion(Long funcionId) {
        final Session session = sessionFactory.getCurrentSession();

        List list = session.createCriteria(AsientoFuncion.class)
                .createAlias("funcion", "funcionBuscada")
                .add(Restrictions.eq("funcionBuscada.id", funcionId))
                .list();

        List<AsientoFuncion> asientoFunciones = new ArrayList<>();
        for (Object obj :
                list) {
            asientoFunciones.add((AsientoFuncion) obj);
        }

        return asientoFunciones;
    }
    
    @Override
    public List<Time> getHorariosLibresFuncion(Long peliculaId, Long cineId, Long tipoFuncionId, Date fecha) {
        List list = sessionFactory.getCurrentSession().createCriteria(AsientoFuncion.class, "asientoFuncion")
                .createAlias("funcion", "funcionBuscada")
                .createAlias("funcionBuscada.pelicula", "peliculaBuscada")
                .createAlias("funcionBuscada.cine", "cineBuscado")
                .createAlias("funcionBuscada.tipoFuncion", "tipoFuncionBuscada")
                .createAlias("estadoAsiento", "estadoAsientoBuscado")
                .add(Restrictions.and(Restrictions.eq("peliculaBuscada.id", peliculaId)
                        , Restrictions.eq("cineBuscado.id", cineId)
                        , Restrictions.eq("tipoFuncionBuscada.id", tipoFuncionId)
                        , Restrictions.eq("funcionBuscada.fecha", fecha)
                        , Restrictions.eq("estadoAsientoBuscado.id", EstadoAsiento.LIBRE.getId())))
                .setProjection(Projections.distinct(Projections.property("funcionBuscada.hora")))
                .list();

        List<Time> times = new ArrayList<>();
        for (Object obj :
                list) {
            times.add((Time) obj);
        }

        return times;
    }
    
    @Override
    public AsientoFuncion consultarAsientoFuncion(Long funcionId, Long asientoId) {
    	final Session session = sessionFactory.getCurrentSession();
    	
    	return (AsientoFuncion) session.createCriteria(AsientoFuncion.class)
    			.createAlias("funcion", "funcionBuscada")
    			.createAlias("asiento", "asientoBuscado")
    			.add(Restrictions.eq("funcionBuscada.id", funcionId))
    			.add(Restrictions.eq("asientoBuscado.id", asientoId))
    			.uniqueResult();
    }

    @Override
    public AsientoFuncion getAsientoFuncionByFuncionIdAndPosicion(Long funcionId, Integer fila, Integer columna) {
        return (AsientoFuncion) sessionFactory.getCurrentSession().createCriteria(AsientoFuncion.class, "asientoFuncion")
                .createAlias("funcion", "funcionBuscada")
                .createAlias("asiento", "asientoBuscado")
                .add(Restrictions.and(Restrictions.eq("funcionBuscada.id", funcionId),
                        Restrictions.eq("asientoBuscado.fila", fila),
                        Restrictions.eq("asientoBuscado.columna", columna)))
                .uniqueResult();
    }

    @Override
    public void cambiarEstadoAsiento(AsientoFuncion asientoFuncion) {
    	final Session session = sessionFactory.getCurrentSession();
    	
    	session.update(asientoFuncion);
    }
}
