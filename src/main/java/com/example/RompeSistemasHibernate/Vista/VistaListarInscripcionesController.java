package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlInscripciones;
import com.example.RompeSistemasHibernate.Modelo.Inscripcion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class VistaListarInscripcionesController {

    @FXML
    private TextField codigoSocioField;

    @FXML
    private DatePicker fechaInicialPicker;

    @FXML
    private DatePicker fechaFinalPicker;

    @FXML
    private ListView<String> listViewInscripciones;

    private ControlInscripciones controlInscripciones;
    private Stage stage;

    public void initialize(ControlInscripciones controlInscripciones, Stage stage) {
        this.controlInscripciones = controlInscripciones;
        this.stage = stage;
    }

    @FXML
    private void handleListarTodas(ActionEvent event) {
        try {
            List<Inscripcion> inscripciones = controlInscripciones.listInscripciones();
            listViewInscripciones.getItems().clear();
            for (Inscripcion inscripcion : inscripciones) {
                listViewInscripciones.getItems().add("Código: " + inscripcion.getNumero() + ", Fecha: " + inscripcion.getFecha() + ", Socio: " + inscripcion.getSocio().getCodigoSocio() + ", Excursión: " + inscripcion.getExcursion().getCodigoExcursion());
            }
        } catch (SQLException e) {
            listViewInscripciones.getItems().add("Error al listar inscripciones: " + e.getMessage());
        }
    }

    @FXML
    private void handleListarPorSocio(ActionEvent event) {
        String codigoSocio = codigoSocioField.getText();
        try {
            List<Inscripcion> inscripciones = controlInscripciones.listInscripcionesSocio(codigoSocio);
            listViewInscripciones.getItems().clear();
            for (Inscripcion inscripcion : inscripciones) {
                listViewInscripciones.getItems().add("Código: " + inscripcion.getNumero() + ", Fecha: " + inscripcion.getFecha() + ", Socio: " + inscripcion.getSocio().getCodigoSocio() + ", Excursión: " + inscripcion.getExcursion().getCodigoExcursion());
            }
        } catch (SQLException e) {
            listViewInscripciones.getItems().add("Error al listar inscripciones: " + e.getMessage());
        }
    }

    @FXML
    private void handleListarPorFechas(ActionEvent event) {
        LocalDate fechaInicial = fechaInicialPicker.getValue();
        LocalDate fechaFinal = fechaFinalPicker.getValue();
        if (fechaInicial == null || fechaFinal == null) {
            listViewInscripciones.getItems().add("Debe seleccionar ambas fechas.");
            return;
        }
        try {
            List<Inscripcion> inscripciones = controlInscripciones.listInscripcionesFechas(fechaInicial, fechaFinal);
            listViewInscripciones.getItems().clear();
            for (Inscripcion inscripcion : inscripciones) {
                listViewInscripciones.getItems().add("Código: " + inscripcion.getNumero() + ", Fecha: " + inscripcion.getFecha() + ", Socio: " + inscripcion.getSocio().getCodigoSocio() + ", Excursión: " + inscripcion.getExcursion().getCodigoExcursion());
            }
        } catch (SQLException e) {
            listViewInscripciones.getItems().add("Error al listar inscripciones: " + e.getMessage());
        }
    }

    @FXML
    private void handleAtras(ActionEvent event) {
        try {
            controlInscripciones.showVistaInscripciones();
        } catch (IOException e) {
            listViewInscripciones.getItems().add("Error al volver al menú de inscripciones.");
        }
        stage.close();
    }
}
