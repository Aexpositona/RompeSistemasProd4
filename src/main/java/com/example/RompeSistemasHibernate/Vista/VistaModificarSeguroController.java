package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlSocios;
import com.example.RompeSistemasHibernate.Modelo.Estandar;
import com.example.RompeSistemasHibernate.Modelo.Seguro;
import com.example.RompeSistemasHibernate.Modelo.Socio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VistaModificarSeguroController {

    @FXML
    private TextField txtNumeroSocio;
    @FXML
    private ChoiceBox<String> choiceSeguro;
    @FXML
    private Button btnModificarSeguro;
    @FXML
    private Label labelMensaje;

    private ControlSocios controlSocios;

    public void setControlSocios(ControlSocios controlSocios) {
        this.controlSocios = controlSocios;
        initialize();
    }

    @FXML
    public void initialize() {
        for (Seguro seguro : Seguro.values()) {
            choiceSeguro.getItems().add(seguro.name());
        }
    }

    @FXML
    private void handleModificarSeguro(ActionEvent event) {
        String numeroSocio = txtNumeroSocio.getText();
        String tipoSeguro = choiceSeguro.getValue();

        Socio socio = controlSocios.getControlDatos().getSocio(numeroSocio);
        if (socio != null && socio instanceof Estandar) {
            Estandar estandar = (Estandar) socio;
            estandar.setSeguro(Seguro.valueOf(tipoSeguro));
            controlSocios.modificarSocio(estandar);
            labelMensaje.setText("Seguro modificado con éxito.");
        } else {
            labelMensaje.setText("El socio no es un usuario Estandar o no existe.");
        }
    }

    @FXML
    private void buttonAtras(ActionEvent event) {
        Stage stage = (Stage) btnModificarSeguro.getScene().getWindow();
        stage.close();
    }
}
