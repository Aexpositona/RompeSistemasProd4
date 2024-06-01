package com.example.RompeSistemasHibernate.Datos;

import com.example.RompeSistemasHibernate.Modelo.Federado;
import com.example.RompeSistemasHibernate.ModeloDAO.FederadoDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SQLFederadoDAO implements FederadoDAO {
    private EntityManager em;

    public SQLFederadoDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Federado> listarFederados() {
        String query = "SELECT f FROM Federado f JOIN FETCH f.federacion";
        TypedQuery<Federado> tq = em.createQuery(query, Federado.class);
        return tq.getResultList();
    }

    @Override
    public Federado getFederado(String codigo) {
        String query = "SELECT f FROM Federado f JOIN FETCH f.federacion WHERE f.codigoSocio = :codigo";
        TypedQuery<Federado> tq = em.createQuery(query, Federado.class);
        tq.setParameter("codigo", codigo);
        List<Federado> resultados = tq.getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    @Override
    public void modificarFederado(Federado federado) {
        em.getTransaction().begin();
        try {
            em.merge(federado);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void eliminarFederado(Federado federado) {
        em.getTransaction().begin();
        try {
            Federado federadoToDelete = em.contains(federado) ? federado : em.merge(federado);
            em.remove(federadoToDelete);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void insertarFederado(Federado federado) {
        em.getTransaction().begin();
        try {
            em.persist(federado);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
