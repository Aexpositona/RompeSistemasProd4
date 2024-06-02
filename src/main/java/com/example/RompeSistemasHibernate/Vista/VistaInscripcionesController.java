package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlInscripciones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class VistaInscripcionesController {

    @FXML
    private TextArea textArea;
    @FXML
    private Button backButton;
    @FXML
    private TextField codigoInscripcionField;
    @FXML
    private Label labelMensaje;
    private ControlInscripciones controlInscripciones;
    private Stage stage;

    public void initialize(ControlInscripciones controlInscripciones, Stage stage) {
        this.controlInscripciones = controlInscripciones;
        this.stage = stage;
    }
    public void setControlInscripciones(ControlInscripciones controlInscripciones) {
        this.controlInscripciones = controlInscripciones;
        initialize();
    }

    @FXML
    public void initialize() {
        if (textArea != null) {
            textArea.setText("");
        }
    }

    @FXML
    private void handleAddInscripcion(ActionEvent event) throws IOException {
        controlInscripciones.showVistaAddInscripcion();
    }

    @FXML
    private void handleListInscripciones(ActionEvent event) throws IOException {
        controlInscripciones.showVistaListarInscripciones();
    }

    @FXML
    private void handleRemoveInscripcion(ActionEvent event) {
        String codigo = codigoInscripcionField.getText();
        try {
            if (controlInscripciones.getInscripcion(codigo)) {
                controlInscripciones.removeInscripcion(codigo);
                labelMensaje.setText("Inscripción eliminada correctamente.");
            } else {
                labelMensaje.setText("Código de inscripción no válido.");
            }
        } catch (SQLException e) {
            labelMensaje.setText("Error al eliminar la inscripción: " + e.getMessage());
        }
    }

    @FXML
    private void handleAtras(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
