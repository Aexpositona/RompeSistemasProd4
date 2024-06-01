package com.example.RompeSistemasHibernate.Datos;

import com.example.RompeSistemasHibernate.Modelo.Seguro;
import com.example.RompeSistemasHibernate.ModeloDAO.SeguroDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SQLSeguroDAO implements SeguroDAO {
    private EntityManager em;

    public SQLSeguroDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public Seguro buscarSeguro(int id) {
        return em.find(Seguro.class, id);
    }

    @Override
    public List<Seguro> listarSeguros() {
        TypedQuery<Seguro> query = em.createQuery("SELECT s FROM Seguro s", Seguro.class);
        return query.getResultList();
    }

    @Override
    public Seguro getSeguro(int id) {
        return em.find(Seguro.class, id);
    }
}
