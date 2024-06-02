package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlExcursiones;
import com.example.RompeSistemasHibernate.Modelo.Excursion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class VistaListarExcursionesController {

    @FXML
    private DatePicker fechaInicialPicker;

    @FXML
    private DatePicker fechaFinalPicker;

    @FXML
    private ListView<String> listViewExcursiones;

    private ControlExcursiones controlExcursiones;
    private Stage stage;

    public void initialize(ControlExcursiones controlExcursiones, Stage stage) {
        this.controlExcursiones = controlExcursiones;
        this.stage = stage;
    }

    @FXML
    private void handleListAll(ActionEvent event) {
        try {
            List<Excursion> excursiones = controlExcursiones.listExcursiones();
            listViewExcursiones.getItems().clear();
            for (Excursion excursion : excursiones) {
                listViewExcursiones.getItems().add("Código: " + excursion.getCodigoExcursion() + ", Descripción: " + excursion.getDescripcion() + ", Fecha: " + excursion.getFecha() + ", Precio: " + excursion.getPrecio());
            }
        } catch (Exception e) {
            listViewExcursiones.getItems().add("Error al listar las excursiones.");
        }
    }

    @FXML
    private void handleListByDates(ActionEvent event) {
        LocalDate fechaInicial = fechaInicialPicker.getValue();
        LocalDate fechaFinal = fechaFinalPicker.getValue();

        if (fechaInicial == null || fechaFinal == null) {
            listViewExcursiones.getItems().add("Debe seleccionar ambas fechas.");
            return;
        }

        try {
            List<Excursion> excursiones = controlExcursiones.listExcursionesFechas(fechaInicial, fechaFinal);
            listViewExcursiones.getItems().clear();
            for (Excursion excursion : excursiones) {
                listViewExcursiones.getItems().add("Código: " + excursion.getCodigoExcursion() + ", Descripción: " + excursion.getDescripcion() + ", Fecha: " + excursion.getFecha());
            }
        } catch (Exception e) {
            listViewExcursiones.getItems().add("Error al listar las excursiones por fechas.");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            controlExcursiones.showVistaExcursiones();
        } catch (Exception e) {
            listViewExcursiones.getItems().add("Error al volver al menú de excursiones.");
        }
        stage.close();
    }
}
