package com.example.RompeSistemasHibernate.Datos;

import com.example.RompeSistemasHibernate.Modelo.Socio;
import com.example.RompeSistemasHibernate.ModeloDAO.SocioDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SQLSocioDAO implements SocioDAO {
    private EntityManager em;

    public SQLSocioDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Socio> listarSocios() {
        String query = "SELECT s FROM Socio s";
        TypedQuery<Socio> tq = em.createQuery(query, Socio.class);
        return tq.getResultList();
    }

    @Override
    public Socio getSocio(String codigo) {
        TypedQuery<Socio> query = em.createQuery("SELECT s FROM Socio s WHERE s.numero = :codigo", Socio.class);
        query.setParameter("codigo", codigo);
        List<Socio> resultados = query.getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    @Override
    public void modificarSocio(Socio socio) {
        em.getTransaction().begin();
        try {
            em.merge(socio);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void eliminarSocio(Socio socio) {
        em.getTransaction().begin();
        try {
            Socio socioToDelete = em.contains(socio) ? socio : em.merge(socio);
            em.remove(socioToDelete);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void insertarSocio(Socio socio) {
        em.getTransaction().begin();
        try {
            em.persist(socio);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Socio buscarSocio(int id) {
        return em.find(Socio.class, id);
    }

    @Override
    public Socio buscarSocio(String codigo) {
        return getSocio(codigo);
    }
}
