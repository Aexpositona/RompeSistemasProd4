package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlExcursiones;
import com.example.RompeSistemasHibernate.Controlador.ControlInscripciones;
import com.example.RompeSistemasHibernate.Controlador.ControlSocios;
import com.example.RompeSistemasHibernate.Modelo.Excursion;
import com.example.RompeSistemasHibernate.Modelo.Inscripcion;
import com.example.RompeSistemasHibernate.Modelo.Socio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class VistaAddInscripcionController {

    @FXML
    private ComboBox<Socio> comboBoxSocios;
    @FXML
    private ComboBox<Excursion> comboBoxExcursiones;
    @FXML
    private Label labelMensaje;

    private ControlInscripciones controlInscripciones;
    private ControlSocios controlSocios;
    private ControlExcursiones controlExcursiones;
    private Stage stage;

    public void initialize(ControlInscripciones controlInscripciones, ControlSocios controlSocios, ControlExcursiones controlExcursiones, Stage stage) {
        this.controlInscripciones = controlInscripciones;
        this.controlSocios = controlSocios;
        this.controlExcursiones = controlExcursiones;
        this.stage = stage;
        cargarSocios();
        cargarExcursiones();
    }

    private void cargarSocios() {
        List<Socio> socios = controlSocios.listSocios();
        comboBoxSocios.getItems().addAll(socios);
    }

    private void cargarExcursiones() {
        List<Excursion> excursiones = controlExcursiones.listExcursiones();
        comboBoxExcursiones.getItems().addAll(excursiones);
    }

    @FXML
    private void handleAddInscripcion(ActionEvent event) throws SQLException, IOException {
        Socio socio = comboBoxSocios.getValue();
        Excursion excursion = comboBoxExcursiones.getValue();

        if (socio == null || excursion == null) {
            labelMensaje.setText("Debe seleccionar un socio y una excursión.");
            return;
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setSocio(socio);
        inscripcion.setExcursion(excursion);
        inscripcion.setFecha(java.time.LocalDate.now());

        controlInscripciones.addInscripcion(inscripcion);
        labelMensaje.setText("Inscripción añadida correctamente.");
    }

    @FXML
    private void handleAtras(ActionEvent event) {
        stage.close();
    }
}
