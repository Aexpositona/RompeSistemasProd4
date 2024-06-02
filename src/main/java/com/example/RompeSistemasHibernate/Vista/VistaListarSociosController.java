package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlSocios;
import com.example.RompeSistemasHibernate.Modelo.Socio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class VistaListarSociosController {

    @FXML
    private ListView<String> listViewSocios;
    @FXML
    private RadioButton radioEstandar;
    @FXML
    private RadioButton radioFederado;
    @FXML
    private RadioButton radioInfantil;

    private ToggleGroup toggleGroupTipoSocio = new ToggleGroup();
    private ControlSocios controlSocios;
    private Stage stage;

    public void initialize(ControlSocios controlSocios, Stage stage) {
        this.controlSocios = controlSocios;
        this.stage = stage;
        setupToggleGroup();
    }

    private void setupToggleGroup() {
        radioEstandar.setToggleGroup(toggleGroupTipoSocio);
        radioFederado.setToggleGroup(toggleGroupTipoSocio);
        radioInfantil.setToggleGroup(toggleGroupTipoSocio);
    }

    @FXML
    private void handleListarPorTipo(ActionEvent event) {
        RadioButton selectedRadioButton = (RadioButton) toggleGroupTipoSocio.getSelectedToggle();
        if (selectedRadioButton == null) {
            listViewSocios.getItems().clear();
            listViewSocios.getItems().add("Debe seleccionar un tipo de socio.");
            return;
        }

        String tipoSocio = selectedRadioButton.getText();
        int tipo;

        switch (tipoSocio) {
            case "Estándar":
                tipo = 1;
                break;
            case "Federado":
                tipo = 2;
                break;
            case "Infantil":
                tipo = 3;
                break;
            default:
                listViewSocios.getItems().clear();
                listViewSocios.getItems().add("Tipo de socio no válido.");
                return;
        }

        List<? extends Socio> socios = controlSocios.listTipoSocios(tipo);
        listViewSocios.getItems().clear();
        for (Socio socio : socios) {
            listViewSocios.getItems().add(socio.toString());
        }
    }

    @FXML
    private void handleListarTodos(ActionEvent event) {
        List<Socio> socios = controlSocios.listSocios();
        listViewSocios.getItems().clear();
        for (Socio socio : socios) {
            listViewSocios.getItems().add(socio.toString());
        }
    }

    @FXML
    private void handleAtras(ActionEvent event) {
        stage.close();
    }
}
