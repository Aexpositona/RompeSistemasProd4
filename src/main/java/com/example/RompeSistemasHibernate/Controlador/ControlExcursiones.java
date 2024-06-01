package com.example.RompeSistemasHibernate.Controlador;

import com.example.RompeSistemasHibernate.Datos.*;
import com.example.RompeSistemasHibernate.Modelo.*;
import com.example.RompeSistemasHibernate.Vista.*;
import com.example.RompeSistemasHibernate.ModeloDAO.*;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

public class ControlExcursiones {
    private EntityManager entityManager;
    private ControlDatos cDatos;
    private ExcursionDAO excursionDAO;
    private VistaExcursiones vExcursiones;
    private VistaAddExcursion vAddExcursion;
    private VistaListarExcursiones vListarExcursiones;
    private ControlPeticiones cPeticiones;

    public ControlExcursiones(APPSenderosMontanas app, ControlDatos cDatos, ControlPeticiones cPeticiones, EntityManager entityManager) {
        this.entityManager = entityManager;
        this.cDatos = cDatos;
        this.cPeticiones = cPeticiones;
        this.excursionDAO = new SQLExcursionDAO(entityManager);
        this.vExcursiones = new VistaExcursiones(this);
        this.vAddExcursion = new VistaAddExcursion(this);
        this.vListarExcursiones = new VistaListarExcursiones(this);
    }

    public void addExcursion(Excursion excursion) {
        entityManager.getTransaction().begin();
        try {
            excursionDAO.addExcursion(excursion);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void removeExcursion(Excursion excursion) {
        entityManager.getTransaction().begin();
        try {
            excursionDAO.deleteExcursion(excursion);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void listExcursiones() {
        List<Excursion> excursiones = excursionDAO.getAllExcursiones();
        for (Excursion excursion : excursiones) {
            System.out.println("Código: " + excursion.getCodigo() + ", Descripción: " + excursion.getDescripcion() + ", Fecha: " + excursion.getFecha());
        }
    }

    public void listExcursionesFechas(LocalDate fechaInicial, LocalDate fechaFinal) {
        List<Excursion> excursiones = excursionDAO.getExcursionesPorFecha(fechaInicial, fechaFinal);
        for (Excursion excursion : excursiones) {
            System.out.println("Código: " + excursion.getCodigo() + ", Descripción: " + excursion.getDescripcion() + ", Fecha: " + excursion.getFecha());
        }
    }

    public void showVistaListarExcursiones() throws SQLException {
        vListarExcursiones.show();
    }

    public void show() throws ParseException, SQLException {
        vExcursiones.show();
    }

    public void showVistaAddExcursion() throws ParseException, SQLException {
        vAddExcursion.show();
    }

    public String getUltimoCodigo() {
        return cDatos.getSiguienteCodigo(1); // 1 para tipo Excursión
    }

    public String getSiguienteCodigo() {
        return cDatos.getSiguienteCodigo(1); // 1 para tipo Excursión
    }

    public boolean checkExistenciaExcursion(String codigoExcursion) {
        return excursionDAO.getExcursionPorCodigo(codigoExcursion) != null;
    }

    public Excursion getExcursion(String codigoExcursion) {
        return excursionDAO.getExcursionPorCodigo(codigoExcursion);
    }

    public void setVistaExcursiones(VistaExcursiones vExcursiones) {
        this.vExcursiones = vExcursiones;
    }

    public void setVistaAddExcursion(VistaAddExcursion vAddExcursion) {
        this.vAddExcursion = vAddExcursion;
    }

    public void setVistaListarExcursiones(VistaListarExcursiones vListarExcursiones) {
        this.vListarExcursiones = vListarExcursiones;
    }

    // Método para obtener ControlDatos
    public ControlDatos getControlDatos() {
        return cDatos;
    }

    // Método para obtener ControlPeticiones
    public ControlPeticiones getControlPeticiones() {
        return cPeticiones;
    }

}
