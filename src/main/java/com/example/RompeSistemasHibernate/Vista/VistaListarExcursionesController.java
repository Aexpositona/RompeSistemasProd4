package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlExcursiones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.time.LocalDate;

public class VistaListarExcursionesController {

    @FXML
    private DatePicker fechaInicialPicker;

    @FXML
    private DatePicker fechaFinalPicker;

    private ControlExcursiones controlExcursiones;
    private Stage stage;

    public void initialize(ControlExcursiones controlExcursiones, Stage stage) {
        this.controlExcursiones = controlExcursiones;
        this.stage = stage;
    }

    @FXML
    private void handleListAll(ActionEvent event) {
        try {
            controlExcursiones.listExcursiones();
        } catch (Exception e) {
            txtMostrarMensaje("Error al listar las excursiones.\n");
        }
    }

    @FXML
    private void handleListByDates(ActionEvent event) {
        LocalDate fechaInicial = fechaInicialPicker.getValue();
        LocalDate fechaFinal = fechaFinalPicker.getValue();

        if (fechaInicial == null || fechaFinal == null) {
            txtMostrarMensaje("Debe seleccionar ambas fechas.\n");
            return;
        }

        try {
            controlExcursiones.listExcursionesFechas(fechaInicial, fechaFinal);
        } catch (Exception e) {
            txtMostrarMensaje("Error al listar las excursiones por fechas.\n");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        stage.close();
    }

    private void txtMostrarMensaje(String mensaje) {
        System.out.print(mensaje);
    }
}