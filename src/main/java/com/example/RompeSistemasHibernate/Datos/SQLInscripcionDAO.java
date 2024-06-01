package com.example.RompeSistemasHibernate.Datos;

import com.example.RompeSistemasHibernate.Modelo.Inscripcion;
import com.example.RompeSistemasHibernate.ModeloDAO.InscripcionDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class SQLInscripcionDAO implements InscripcionDAO {
    private EntityManager em;

    public SQLInscripcionDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insertarInscripcion(Inscripcion inscripcion) {
        em.getTransaction().begin();
        try {
            em.persist(inscripcion);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void modificarInscripcion(Inscripcion inscripcion) {
        em.getTransaction().begin();
        try {
            em.merge(inscripcion);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void eliminarInscripcion(Inscripcion inscripcion) {
        em.getTransaction().begin();
        try {
            Inscripcion inscripcionToDelete = em.contains(inscripcion) ? inscripcion : em.merge(inscripcion);
            em.remove(inscripcionToDelete);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Inscripcion buscarInscripcion(String id) {
        return em.find(Inscripcion.class, id);
    }

    @Override
    public List<Inscripcion> listarInscripciones() {
        TypedQuery<Inscripcion> query = em.createQuery("SELECT i FROM Inscripcion i", Inscripcion.class);
        return query.getResultList();
    }

    @Override
    public List<Inscripcion> getInscripcionesPorSocio(String idSocio) {
        TypedQuery<Inscripcion> query = em.createQuery("SELECT i FROM Inscripcion i WHERE i.socio.numero = :idSocio", Inscripcion.class);
        query.setParameter("idSocio", idSocio);
        return query.getResultList();
    }

    @Override
    public List<Inscripcion> getInscripcionesPorFecha(LocalDate fechaInicial, LocalDate fechaFinal) {
        TypedQuery<Inscripcion> query = em.createQuery("SELECT i FROM Inscripcion i WHERE i.fechaInscripcion BETWEEN :fechaInicial AND :fechaFinal", Inscripcion.class);
        query.setParameter("fechaInicial", fechaInicial);
        query.setParameter("fechaFinal", fechaFinal);
        return query.getResultList();
    }

    @Override
    public Inscripcion getInscripcion(String id) {
        return em.find(Inscripcion.class, id);
    }

    @Override
    public List<Inscripcion> getAllInscripciones() {
        TypedQuery<Inscripcion> query = em.createQuery("SELECT i FROM Inscripcion i", Inscripcion.class);
        return query.getResultList();
    }
}
