package com.example.RompeSistemasHibernate.Datos;



import com.example.RompeSistemasHibernate.Modelo.Excursion;
import com.example.RompeSistemasHibernate.ModeloDAO.ExcursionDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class SQLExcursionDAO implements ExcursionDAO {
    private EntityManager em;

    public SQLExcursionDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Excursion> getAllExcursiones() {
        TypedQuery<Excursion> query = em.createQuery("SELECT e FROM Excursion e", Excursion.class);
        return query.getResultList();
    }

    @Override
    public Excursion getExcursion(String codigo) {
        TypedQuery<Excursion> query = em.createQuery("SELECT e FROM Excursion e WHERE e.codigoExcursion = :codigo", Excursion.class);
        query.setParameter("codigo", codigo);
        List<Excursion> resultados = query.getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    @Override
    public void insertarExcursion(Excursion excursion) {
        em.getTransaction().begin();
        try {
            em.persist(excursion);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void updateExcursion(Excursion excursion) {
        em.getTransaction().begin();
        try {
            em.merge(excursion);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void deleteExcursion(Excursion excursion) {
        em.getTransaction().begin();
        try {
            em.remove(em.contains(excursion) ? excursion : em.merge(excursion));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public List<Excursion> getExcursionesPorFecha(LocalDate fechaInicial, LocalDate fechaFinal) {
        TypedQuery<Excursion> query = em.createQuery("SELECT e FROM Excursion e WHERE e.fecha BETWEEN :fechaInicial AND :fechaFinal", Excursion.class);
        query.setParameter("fechaInicial", fechaInicial);
        query.setParameter("fechaFinal", fechaFinal);
        return query.getResultList();
    }

    @Override
    public String getUltimoCodigo() {
        TypedQuery<String> query = em.createQuery("SELECT e.codigoExcursion FROM Excursion e ORDER BY e.codigoExcursion DESC", String.class);
        List<String> resultados = query.setMaxResults(1).getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    @Override
    public List<Excursion> listarObjetosPorParametro(String parametro) {
        TypedQuery<Excursion> query = em.createQuery("SELECT e FROM Excursion e WHERE e.descripcion LIKE :parametro", Excursion.class);
        query.setParameter("parametro", "%" + parametro + "%");
        return query.getResultList();
    }

    @Override
    public Excursion getExcursionPorCodigo(String codigoExcursion) {
        return getExcursion(codigoExcursion);
    }
}
