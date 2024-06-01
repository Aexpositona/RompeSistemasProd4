package com.example.RompeSistemasHibernate.Controlador;

import com.example.RompeSistemasHibernate.Datos.*;
import com.example.RompeSistemasHibernate.Modelo.*;
import com.example.RompeSistemasHibernate.Vista.*;
import com.example.RompeSistemasHibernate.ModeloDAO.*;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

public class ControlInscripciones {
    private EntityManager entityManager;
    private VistaInscripciones vInscripciones;
    private VistaAddInscripcion vAddInscripcion;
    private VistaListarInscripciones vListarInscripciones;
    private ControlPeticiones cPeticiones;
    private ControlDatos cDatos;
    private InscripcionDAO inscripcionDAO;

    public ControlInscripciones(EntityManager entityManager, ControlDatos cDatos, ControlPeticiones cPeticiones) throws SQLException {
        this.entityManager = entityManager;
        this.cPeticiones = cPeticiones;
        this.cDatos = cDatos;
        this.vInscripciones = new VistaInscripciones(this);
        this.vAddInscripcion = new VistaAddInscripcion(this);
        this.vListarInscripciones = new VistaListarInscripciones(this);
        this.inscripcionDAO = new SQLInscripcionDAO(entityManager);
    }

    public void addInscripcion(Inscripcion inscripcion) throws SQLException {
        inscripcionDAO.insertarInscripcion(inscripcion);
    }

    public void listInscripciones() throws SQLException {
        inscripcionDAO.listarInscripciones();
        for (Inscripcion inscripcion : inscripcionDAO.getAllInscripciones()) {
            System.out.println(inscripcion);
        }
    }

    public void listInscripcionesSocio(String idSocio) throws SQLException {
        inscripcionDAO.getInscripcionesPorSocio(idSocio);
    }

    public void listInscripcionesFechas(LocalDate fechaInicial, LocalDate fechaFinal) throws SQLException {
        inscripcionDAO.getInscripcionesPorFecha(fechaInicial, fechaFinal);
    }

    public void removeInscripcion(String codigo) throws SQLException {
        Inscripcion inscripcion = inscripcionDAO.getInscripcion(codigo);
        if (inscripcion != null) {
            inscripcionDAO.eliminarInscripcion(inscripcion);
            System.out.println("Inscripción eliminada correctamente.");
        } else {
            System.out.println("Inscripción no encontrada.");
        }
    }

    public void modificarInscripcion(Inscripcion inscripcion) throws SQLException {
        inscripcionDAO.modificarInscripcion(inscripcion);
    }

    // Getters y setters para vistas y controladores

    public VistaInscripciones getVistaInscripciones() {
        return vInscripciones;
    }

    public VistaAddInscripcion getVistaAddInscripcion() {
        return vAddInscripcion;
    }

    public VistaListarInscripciones getVistaListarInscripciones() {
        return vListarInscripciones;
    }

    public ControlPeticiones getControlPeticiones() {
        return cPeticiones;
    }

    public ControlDatos getControlDatos() {
        return cDatos;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void setVistaInscripciones(VistaInscripciones vInscripciones) {
        this.vInscripciones = vInscripciones;
    }

    public void setVistaListarInscripciones(VistaListarInscripciones vListarInscripciones) {
        this.vListarInscripciones = vListarInscripciones;
    }

    public void setVistaAddInscripcion(VistaAddInscripcion vAddInscripcion) {
        this.vAddInscripcion = vAddInscripcion;
    }

    public void show() throws ParseException, SQLException {
        vInscripciones.show();
    }

    public boolean getInscripcion(String idInscripcion) {
        return inscripcionDAO.getInscripcion(idInscripcion) != null;
    }
}
