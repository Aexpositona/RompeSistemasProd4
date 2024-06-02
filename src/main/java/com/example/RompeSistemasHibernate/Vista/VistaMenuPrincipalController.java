package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlMenuPrincipal;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Clase que representa la vista del menú principal.
 */
public class VistaMenuPrincipalController {

    private ControlMenuPrincipal cMenuPrincipal;
    private Stage stage;

    @FXML
    private Button btnInscripciones;

    @FXML
    private Button btnSocios;

    @FXML
    private Button btnExcursiones;

    @FXML
    private Button btnSalir;

    @FXML
    private Label lblMensaje;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Método para manejar el evento de ir a la vista de inscripciones.
     */
    @FXML
    private void handleButtonVistaInscripciones() throws SQLException, ParseException, IOException {
        lblMensaje.setText("Navegando a la vista de inscripciones...");
        cMenuPrincipal.showVistaInscripciones();
    }

    /**
     * Método para manejar el evento de ir a la vista de socios.
     */
    @FXML
    private void handleButtonVistaSocios() throws ParseException, SQLException, IOException {
        lblMensaje.setText("Navegando a la vista de socios...");
        cMenuPrincipal.showVistaSocios();
    }

    /**
     * Método para manejar el evento de ir a la vista de excursiones.
     */
    @FXML
    private void handleButtonVistaExcursiones() throws ParseException, SQLException, IOException {
        lblMensaje.setText("Navegando a la vista de excursiones...");
        cMenuPrincipal.showVistaExcursiones();
    }

    /**
     * Método para manejar el evento de salir de la aplicación.
     */
    @FXML
    private void handleButtonVistaSalir(){
        lblMensaje.setText("Saliendo de la aplicación...");
        stage.close();
    }

    /**
     * Método para mostrar un mensaje.
     *
     * @param mensaje Mensaje a mostrar.
     */
    public void txtMostrarMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
    }

    /**
     * Establece el controlador principal.
     *
     * @param cMenuPrincipal Controlador del menú principal.
     */
    public void setControlMenuPrincipal(ControlMenuPrincipal cMenuPrincipal) {
        this.cMenuPrincipal = cMenuPrincipal;
    }
}
