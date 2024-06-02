package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlExcursiones;
import com.example.RompeSistemasHibernate.Modelo.Excursion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class VistaAddExcursionController {

    @FXML
    private TextField descripcionField;

    @FXML
    private DatePicker fechaPicker;

    @FXML
    private TextField precioField;

    @FXML
    private TextField diasField;

    private ControlExcursiones controlExcursiones;
    private Stage stage;

    public void initialize(ControlExcursiones controlExcursiones, Stage stage) {
        this.controlExcursiones = controlExcursiones;
        this.stage = stage;
    }

    @FXML
    private void handleAddExcursion(ActionEvent event) {
        String descripcion = descripcionField.getText();
        LocalDate fecha = fechaPicker.getValue();
        float precio;
        int dias;

        try {
            precio = Float.parseFloat(precioField.getText());
            dias = Integer.parseInt(diasField.getText());
        } catch (NumberFormatException e) {
            txtMostrarMensaje("El precio y los días deben ser números válidos.\n");
            return;
        }

        if (descripcion.isEmpty() || fecha == null) {
            txtMostrarMensaje("Debe completar todos los campos.\n");
            return;
        }

        String codigo = controlExcursiones.getSiguienteCodigo();
        Excursion excursion = new Excursion(codigo, descripcion, fecha, dias, precio);
        controlExcursiones.addExcursion(excursion);
        txtMostrarMensaje("Excursión añadida correctamente.\n");
    }

    @FXML
    private void handleBack(ActionEvent event) {
        stage.close();
    }

    private void txtMostrarMensaje(String mensaje) {
        System.out.print(mensaje);
    }
}
