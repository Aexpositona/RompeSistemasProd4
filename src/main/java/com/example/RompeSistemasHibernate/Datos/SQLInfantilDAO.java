package com.example.RompeSistemasHibernate.Datos;

import com.example.RompeSistemasHibernate.Modelo.Infantil;
import com.example.RompeSistemasHibernate.ModeloDAO.InfantilDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SQLInfantilDAO implements InfantilDAO {
    private EntityManager em;

    public SQLInfantilDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Infantil> listarInfantiles() {
        String query = "SELECT i FROM Infantil i JOIN FETCH i.numSocioTutor"; // Asumiendo que Infantil tiene una relación con el tutor
        TypedQuery<Infantil> tq = em.createQuery(query, Infantil.class);
        return tq.getResultList();
    }

    @Override
    public Infantil getInfantil(String codigo) {
        String query = "SELECT i FROM Infantil i JOIN FETCH i.numSocioTutor WHERE i.numero = :codigo"; // Asumiendo que Infantil tiene una relación con el tutor
        TypedQuery<Infantil> tq = em.createQuery(query, Infantil.class);
        tq.setParameter("codigo", codigo);
        List<Infantil> resultados = tq.getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    @Override
    public void modificarInfantil(Infantil infantil) {
        em.getTransaction().begin();
        try {
            em.merge(infantil);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void eliminarInfantil(Infantil infantil) {
        em.getTransaction().begin();
        try {
            Infantil infantilToDelete = em.contains(infantil) ? infantil : em.merge(infantil);
            em.remove(infantilToDelete);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void insertarInfantil(Infantil infantil) {
        em.getTransaction().begin();
        try {
            em.persist(infantil);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
