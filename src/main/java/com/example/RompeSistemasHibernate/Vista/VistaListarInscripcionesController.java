package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlInscripciones;
import com.example.RompeSistemasHibernate.Controlador.ControlPeticiones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;

public class VistaListarInscripcionesController {

    @FXML
    private TextField codigoSocioField;

    @FXML
    private DatePicker fechaInicialPicker;

    @FXML
    private DatePicker fechaFinalPicker;

    @FXML
    private Button listarTodasButton;

    @FXML
    private Button listarPorSocioButton;

    @FXML
    private Button listarPorFechasButton;

    @FXML
    private Button atrasButton;

    private ControlInscripciones controlInscripciones;
    private Stage stage;

    public void initialize(ControlInscripciones controlInscripciones, Stage stage) {
        this.controlInscripciones = controlInscripciones;
        this.stage = stage;
    }

    @FXML
    private void handleListarTodas(ActionEvent event) {
        try {
            controlInscripciones.listInscripciones();
        } catch (SQLException e) {
            txtMostrarMensaje("Error al listar inscripciones: " + e.getMessage());
        }
    }

    @FXML
    private void handleListarPorSocio(ActionEvent event) {
        String codigoSocio = codigoSocioField.getText();
        try {
            controlInscripciones.listInscripcionesSocio(codigoSocio);
        } catch (SQLException e) {
            txtMostrarMensaje("Error al listar inscripciones: " + e.getMessage());
        }
    }

    @FXML
    private void handleListarPorFechas(ActionEvent event) {
        LocalDate fechaInicial = fechaInicialPicker.getValue();
        LocalDate fechaFinal = fechaFinalPicker.getValue();
        try {
            controlInscripciones.listInscripcionesFechas(fechaInicial, fechaFinal);
        } catch (SQLException e) {
            txtMostrarMensaje("Error al listar inscripciones: " + e.getMessage());
        }
    }

    @FXML
    private void handleAtras(ActionEvent event) {
        stage.close();
    }

    private void txtMostrarMensaje(String mensaje) {
        System.out.print(mensaje);
    }
}