package com.example.RompeSistemasHibernate.Datos;

import com.example.RompeSistemasHibernate.Modelo.Federacion;
import com.example.RompeSistemasHibernate.ModeloDAO.FederacionDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SQLFederacionDAO implements FederacionDAO {
    private EntityManager em;

    public SQLFederacionDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertarFederacion(Federacion federacion) {
        em.getTransaction().begin();
        try {
            em.persist(federacion);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void modificarFederacion(Federacion federacion) {
        em.getTransaction().begin();
        try {
            em.merge(federacion);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void eliminarFederacion(Federacion federacion) {
        em.getTransaction().begin();
        try {
            Federacion federacionToDelete = em.contains(federacion) ? federacion : em.merge(federacion);
            em.remove(federacionToDelete);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Federacion buscarFederacion(int id) {
        return em.find(Federacion.class, id);
    }

    @Override
    public Federacion buscarFederacion(String nombre) {
        String query = "SELECT f FROM Federacion f WHERE f.nombre = :nombre";
        TypedQuery<Federacion> tq = em.createQuery(query, Federacion.class);
        tq.setParameter("nombre", nombre);
        List<Federacion> resultados = tq.getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    @Override
    public Federacion getFederacion(String codigo) {
        return em.find(Federacion.class, codigo);
    }

    @Override
    public List<Federacion> listarFederaciones() {
        TypedQuery<Federacion> query = em.createQuery("SELECT f FROM Federacion f", Federacion.class);
        return query.getResultList();
    }
}
