package com.example.RompeSistemasHibernate.Controlador;

import com.example.RompeSistemasHibernate.Vista.VistaMenuPrincipal;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Controlador para la gestión del menú principal de la aplicación.
 *
 */
public class ControlMenuPrincipal {

    // Atributos
    private VistaMenuPrincipal vMenuPrincipal;
    private ControlInscripciones cInscripciones;
    private ControlSocios cSocios;
    private ControlExcursiones cExcursiones;
    private ControlPeticiones cPeticiones;
    private EntityManager em;

    /**
     * Constructor de ControlMenuPrincipal.
     *
     * @param app APPSenderosMontanas
     * @param cDatos ControlDatos
     * @param cPeticiones ControlPeticiones
     * @param em EntityManager
     */
    public ControlMenuPrincipal(APPSenderosMontanas app, ControlDatos cDatos, ControlPeticiones cPeticiones, EntityManager em) throws SQLException {
        this.em = em;
        this.vMenuPrincipal = new VistaMenuPrincipal(this);
        this.cInscripciones = new ControlInscripciones(em, cDatos, cPeticiones);
        this.cSocios = new ControlSocios(app, cDatos, cPeticiones, em);
        this.cExcursiones = new ControlExcursiones(app, cDatos, cPeticiones, em);
        this.cPeticiones = cPeticiones;
    }

    /**
     * Constructor de ControlMenuPrincipal de copia.
     *
     * @param cMenuPrincipal ControlMenuPrincipal a copiar
     * @param em EntityManager
     */
    public ControlMenuPrincipal(ControlMenuPrincipal cMenuPrincipal, EntityManager em) {
        this.em = em;
        this.vMenuPrincipal = cMenuPrincipal.getVistaMenuPrincipal();
        this.cInscripciones = cMenuPrincipal.getControlInscripciones();
        this.cSocios = cMenuPrincipal.getControlSocios();
        this.cExcursiones = cMenuPrincipal.getControlExcursiones();
        this.cPeticiones = cMenuPrincipal.getControlPeticiones();
    }

    /**
     * Constructor de ControlMenuPrincipal vacío.
     */
    public ControlMenuPrincipal() {
        this.vMenuPrincipal = null;
        this.cInscripciones = null;
        this.cSocios = null;
        this.cExcursiones = null;
        this.cPeticiones = null;
    }

    // Getters

    public VistaMenuPrincipal getVistaMenuPrincipal() {
        return vMenuPrincipal;
    }

    public ControlInscripciones getControlInscripciones() {
        return cInscripciones;
    }

    public ControlSocios getControlSocios() {
        return cSocios;
    }

    public ControlExcursiones getControlExcursiones() {
        return cExcursiones;
    }

    public ControlPeticiones getControlPeticiones() {
        return cPeticiones;
    }

    // Setters

    public void setVistaMenuPrincipal(VistaMenuPrincipal vMenuPrincipal) {
        this.vMenuPrincipal = vMenuPrincipal;
    }

    public void setControlInscripciones(ControlInscripciones cInscripciones) {
        this.cInscripciones = cInscripciones;
    }

    public void setControlSocios(ControlSocios cSocios) {
        this.cSocios = cSocios;
    }

    public void setControlExcursiones(ControlExcursiones cExcursiones) {
        this.cExcursiones = cExcursiones;
    }

    public void setControlPeticiones(ControlPeticiones cPeticiones) {
        this.cPeticiones = cPeticiones;
    }

    // Métodos

    public void showInscripciones() throws SQLException, ParseException {
        cInscripciones.show();
    }

    public void showVistaSocios() throws ParseException, SQLException {
        cSocios.show();
    }

    public void showVistaExcursiones() throws ParseException, SQLException {
        cExcursiones.show();
    }

    public void show() throws ParseException, SQLException {
        vMenuPrincipal.show();
    }
}
