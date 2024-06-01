package com.example.RompeSistemasHibernate.Datos;

import com.example.RompeSistemasHibernate.Modelo.Estandar;
import com.example.RompeSistemasHibernate.ModeloDAO.EstandarDAO;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SQLEstandarDAO implements EstandarDAO {
    private EntityManager em;

    public SQLEstandarDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Estandar> listarEstandares() {
        String query = "SELECT e FROM Estandar e JOIN FETCH e.seguro";
        TypedQuery<Estandar> tq = em.createQuery(query, Estandar.class);
        return tq.getResultList();
    }

    @Override
    public Estandar getEstandar(String codigo) {
        String query = "SELECT e FROM Estandar e JOIN FETCH e.seguro WHERE e.codigoSocio = :codigo";
        TypedQuery<Estandar> tq = em.createQuery(query, Estandar.class);
        tq.setParameter("codigo", codigo);
        List<Estandar> resultados = tq.getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    @Override
    public void modificarEstandar(Estandar estandar) {
        em.getTransaction().begin();
        try {
            em.merge(estandar);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void eliminarEstandar(Estandar estandar) {
        em.getTransaction().begin();
        try {
            Estandar estandarToDelete = em.contains(estandar) ? estandar : em.merge(estandar);
            em.remove(estandarToDelete);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void insertarEstandar(Estandar estandar) {
        em.getTransaction().begin();
        try {
            em.persist(estandar);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}