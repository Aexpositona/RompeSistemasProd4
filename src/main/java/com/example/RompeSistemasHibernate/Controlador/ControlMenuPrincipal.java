package com.example.RompeSistemasHibernate.Controlador;

import com.example.RompeSistemasHibernate.Vista.VistaExcursionesController;
import com.example.RompeSistemasHibernate.Vista.VistaInscripcionesController;
import com.example.RompeSistemasHibernate.Vista.VistaMenuPrincipalController;
import com.example.RompeSistemasHibernate.Vista.VistaSociosController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Controlador para la gestión del menú principal de la aplicación.
 *
 */
public class ControlMenuPrincipal {

    // Atributos
    private VistaMenuPrincipalController vMenuPrincipalController;
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
    public ControlMenuPrincipal(APPSenderosMontanas app, ControlDatos cDatos, ControlPeticiones cPeticiones, EntityManager em) throws SQLException, IOException {
        this.em = em;
        this.cSocios = new ControlSocios(app, cDatos, cPeticiones, em);
        this.cExcursiones = new ControlExcursiones(app, cDatos, cPeticiones, em);
        this.cInscripciones = new ControlInscripciones(em, cDatos, cPeticiones, cSocios, cExcursiones);
        this.cPeticiones = cPeticiones;
        initVistaMenuPrincipal();
    }

    /**
     * Constructor de ControlMenuPrincipal de copia.
     *
     * @param cMenuPrincipal ControlMenuPrincipal a copiar
     * @param em EntityManager
     */
    public ControlMenuPrincipal(ControlMenuPrincipal cMenuPrincipal, EntityManager em) throws IOException {
        this.em = em;
        this.cInscripciones = cMenuPrincipal.getControlInscripciones();
        this.cSocios = cMenuPrincipal.getControlSocios();
        this.cExcursiones = cMenuPrincipal.getControlExcursiones();
        this.cPeticiones = cMenuPrincipal.getControlPeticiones();
        initVistaMenuPrincipal();
    }

    /**
     * Constructor de ControlMenuPrincipal vacío.
     */
    public ControlMenuPrincipal() {
        this.cInscripciones = null;
        this.cSocios = null;
        this.cExcursiones = null;
        this.cPeticiones = null;
    }

    private void initVistaMenuPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VistaMenuPrincipal.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        vMenuPrincipalController = loader.getController();
        vMenuPrincipalController.setControlMenuPrincipal(this);
        stage.setTitle("Senderos y Montañas");
        stage.show();
    }

    // Getters

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

    // Métodos para mostrar las diferentes vistas

    public void showVistaInscripciones() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VistaInscripciones.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        VistaInscripcionesController vInscripcionesController = loader.getController();
        vInscripcionesController.setControlInscripciones(cInscripciones);
        stage.setTitle("Gestión de Inscripciones");
        stage.show();
    }

    public void showVistaSocios() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VistaSocios.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        VistaSociosController vSociosController = loader.getController();
        vSociosController.setControlSocios(cSocios);
        stage.setTitle("Gestión de Socios");
        stage.show();
    }

    public void showVistaExcursiones() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VistaExcursiones.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        VistaExcursionesController vExcursionesController = loader.getController();
        vExcursionesController.setControlExcursiones(cExcursiones);
        stage.setTitle("Gestión de Excursiones");
        stage.show();
    }

    public void showVistaMenuPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VistaMenuPrincipal.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        VistaMenuPrincipalController vMenuPrincipalController = loader.getController();
        vMenuPrincipalController.setControlMenuPrincipal(this);
        stage.setTitle("Senderos y Montañas");
        stage.show();
    }
}
