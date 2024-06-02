package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlInscripciones;
import com.example.RompeSistemasHibernate.Controlador.ControlSocios;
import com.example.RompeSistemasHibernate.Modelo.Socio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class VistaAddInscripcionController {

    @FXML
    private TextArea textArea;

    private ControlInscripciones controlInscripciones;
    private ControlSocios controlSocios;
    public void setControlInscripciones(ControlInscripciones controlInscripciones) {
        this.controlInscripciones = controlInscripciones;
        initialize();
    }

    @FXML
    public void initialize() {
        textArea.setText("");
    }

    @FXML
    private void handleAddInscripcion(ActionEvent event) throws SQLException, IOException {
        controlInscripciones.showVistaAddInscripcion();
    }

    @FXML
    private void handleListInscripciones(ActionEvent event) throws SQLException, IOException {
        controlInscripciones.showVistaListarInscripciones();
    }

    @FXML
    private void handleRemoveInscripcion(ActionEvent event) throws SQLException {
        controlInscripciones.showVistaRemoveInscripcion();
    }

    @FXML
    private void handleAtras(ActionEvent event) {
        textArea.setText("Volviendo al menú inscripciones...\n\n");
    }

}