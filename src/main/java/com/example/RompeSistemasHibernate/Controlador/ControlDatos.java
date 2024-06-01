package com.example.RompeSistemasHibernate.Controlador;

import com.example.RompeSistemasHibernate.Modelo.*;
import com.example.RompeSistemasHibernate.Datos.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class ControlDatos {
    private EntityManager entityManager;

    public ControlDatos(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addSocio(Socio socio) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(socio);
        transaction.commit();
    }

    public Socio getSocio(String codigo) {
        return entityManager.find(Socio.class, codigo);
    }

    public Federacion getFederacion(String codigo) {
        return entityManager.find(Federacion.class, codigo);
    }

    public void modificarSocio(Socio socio) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(socio);
        transaction.commit();
    }

    public void modificarFederacion(Federacion federacion) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(federacion);
        transaction.commit();
    }

    public Excursion getExcursion(String codigo) {
        return entityManager.find(Excursion.class, codigo);
    }

    public void eliminarSocio(Socio socio) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(entityManager.contains(socio) ? socio : entityManager.merge(socio));
        transaction.commit();
    }

    public String getSiguienteCodigo(int tipoObjeto) {
        TypedQuery<String> query;
        String prefix;

        switch (tipoObjeto) {
            case 1:
                query = entityManager.createQuery("SELECT e.codigoExcursion FROM Excursion e ORDER BY e.codigoExcursion DESC", String.class).setMaxResults(1);
                prefix = "EXC";
                break;
            case 2:
                query = entityManager.createQuery("SELECT i.numero FROM Inscripcion i ORDER BY i.numero DESC", String.class).setMaxResults(1);
                prefix = "INS";
                break;
            case 3:
                query = entityManager.createQuery("SELECT s.codigoSocio FROM Socio s ORDER BY s.codigoSocio DESC", String.class).setMaxResults(1);
                prefix = "SOC";
                break;
            default:
                throw new IllegalArgumentException("Tipo de objeto no válido");
        }

        try {
            String ultimoCodigo = query.getSingleResult();
            if (ultimoCodigo == null || !ultimoCodigo.startsWith(prefix)) {
                throw new IllegalArgumentException("Código no válido encontrado en la base de datos");
            }
            int numero = Integer.parseInt(ultimoCodigo.substring(3)) + 1;
            return String.format("%s%04d", prefix, numero);
        } catch (NoResultException e) {
            // No se encontró ningún resultado, entonces no hay objetos en la base de datos
            return prefix + "0001";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al generar el siguiente código", e);
        }
    }


    // Métodos para insertar nuevos registros según los procedimientos almacenados

    public Federacion insertarFederacion(String nombre) {
        Federacion federacion = new Federacion();
        federacion.setNombre(nombre);

        SQLFederacionDAO federacionDAO = new SQLFederacionDAO(entityManager);
        federacionDAO.insertarFederacion(federacion);

        return federacion;
    }

    public Estandar insertarEstandar(String nombre, String nif, int seguro) {
        Estandar estandar = new Estandar();
        // Configurar propiedades del objeto Estandar

        SQLEstandarDAO estandarDAO = new SQLEstandarDAO(entityManager);
        estandarDAO.insertarEstandar(estandar);

        return estandar;
    }

    public Federado insertarFederado(String nombre, String nif, int federacion) {
        Federado federado = new Federado();
        // Configurar propiedades del objeto Federado

        SQLFederadoDAO federadoDAO = new SQLFederadoDAO(entityManager);
        federadoDAO.insertarFederado(federado);

        return federado;
    }

    public Infantil insertarInfantil(String nombre, String nif, int socioTutor) {
        Infantil infantil = new Infantil();
        // Configurar propiedades del objeto Infantil

        SQLInfantilDAO infantilDAO = new SQLInfantilDAO(entityManager);
        infantilDAO.insertarInfantil(infantil);

        return infantil;
    }

    public Excursion insertarExcursion(String descripcion, String fecha, int duracion, float precio) {
        Excursion excursion = new Excursion();
        // Configurar propiedades del objeto Excursion

        SQLExcursionDAO excursionDAO = new SQLExcursionDAO(entityManager);
        excursionDAO.insertarExcursion(excursion);

        return excursion;
    }

    public Inscripcion insertarInscripcion(String fechaInscripcion, int idSocio, int idExcursion) {
        Inscripcion inscripcion = new Inscripcion();
        // Configurar propiedades del objeto Inscripcion

        SQLInscripcionDAO inscripcionDAO = new SQLInscripcionDAO(entityManager);
        inscripcionDAO.insertarInscripcion(inscripcion);

        return inscripcion;
    }
}
