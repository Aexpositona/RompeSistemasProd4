package com.example.RompeSistemasHibernate.Controlador;

import com.example.RompeSistemasHibernate.Vista.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.text.ParseException;

public class APPSenderosMontanas {

    // Atributos
    private ControlMenuPrincipal cMenuPrincipal;
    private ControlInscripciones cInscripciones;
    private ControlSocios cSocios;
    private ControlExcursiones cExcursiones;
    private ControlDatos cDatos;
    private ControlPeticiones cPeticiones;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public static void main(String[] args) throws ParseException, SQLException {
        APPSenderosMontanas app = new APPSenderosMontanas();
        app.iniciar();
        app.showVistaMenuPrincipal();
        app.close();
    }

    public void iniciar() throws SQLException {
        entityManagerFactory = Persistence.createEntityManagerFactory("AppSenderosMontanasPU");
        entityManager = entityManagerFactory.createEntityManager();

        cPeticiones = new ControlPeticiones();
        cDatos = new ControlDatos(entityManager);

        cInscripciones = new ControlInscripciones(entityManager, cDatos, cPeticiones);
        cSocios = new ControlSocios(this, cDatos, cPeticiones, entityManager);
        cExcursiones = new ControlExcursiones(this, cDatos, cPeticiones, entityManager);
        cMenuPrincipal = new ControlMenuPrincipal(this, cDatos, cPeticiones, entityManager);

        // Configurar las vistas para ControlMenuPrincipal
        VistaMenuPrincipal vMenuPrincipal = new VistaMenuPrincipal(cMenuPrincipal);
        cMenuPrincipal.setVistaMenuPrincipal(vMenuPrincipal);

        // Configurar las vistas para ControlInscripciones
        VistaInscripciones vInscripciones = new VistaInscripciones(cInscripciones);
        VistaListarInscripciones vListarInscripciones = new VistaListarInscripciones(cInscripciones);
        VistaAddInscripcion vAddInscripcion = new VistaAddInscripcion(cInscripciones);

        cInscripciones.setVistaInscripciones(vInscripciones);
        cInscripciones.setVistaListarInscripciones(vListarInscripciones);
        cInscripciones.setVistaAddInscripcion(vAddInscripcion);

        // Configurar las vistas para ControlSocios
        VistaSocios vSocios = new VistaSocios(cSocios);
        VistaListarSocios vListarSocios = new VistaListarSocios(cSocios);
        VistaAddSocio vAddSocio = new VistaAddSocio(cSocios);
        VistaModificarSeguro vModificarSeguro = new VistaModificarSeguro(cSocios);

        cSocios.setVistaSocios(vSocios);
        cSocios.setVistaListarSocios(vListarSocios);
        cSocios.setVistaAddSocio(vAddSocio);
        cSocios.setVistaModificarSeguro(vModificarSeguro);

        // Configurar las vistas para ControlExcursiones
        VistaExcursiones vExcursiones = new VistaExcursiones(cExcursiones);
        VistaListarExcursiones vListarExcursiones = new VistaListarExcursiones(cExcursiones);
        VistaAddExcursion vAddExcursion = new VistaAddExcursion(cExcursiones);

        cExcursiones.setVistaExcursiones(vExcursiones);
        cExcursiones.setVistaListarExcursiones(vListarExcursiones);
        cExcursiones.setVistaAddExcursion(vAddExcursion);
    }

    private void showVistaMenuPrincipal() throws ParseException, SQLException {
        cMenuPrincipal.show();
    }

    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
