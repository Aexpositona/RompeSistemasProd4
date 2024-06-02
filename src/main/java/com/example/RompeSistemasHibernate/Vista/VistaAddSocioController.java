package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlSocios;
import com.example.RompeSistemasHibernate.Modelo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class VistaAddSocioController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtNIF;
    @FXML
    private ChoiceBox<String> choiceTipoSocio;
    @FXML
    private ChoiceBox<String> choiceSeguro;
    @FXML
    private TextField txtNumSocioTutor;
    @FXML
    private Label labelMensaje;

    private ControlSocios controlSocios;

    public void setControlSocios(ControlSocios controlSocios) {
        this.controlSocios = controlSocios;
        initialize();
    }

    @FXML
    public void initialize() {
        choiceTipoSocio.getItems().addAll("Estándar", "Federado", "Infantil");
        for (Seguro seguro : Seguro.values()) {
            choiceSeguro.getItems().add(seguro.name());
        }
    }

    @FXML
    private void handleAddSocio(ActionEvent event) {
        try {
            String nombre = txtNombre.getText();
            String nif = txtNIF.getText();
            String tipoSocio = choiceTipoSocio.getValue();
            String seguroInput = choiceSeguro.getValue();
            String numSocioTutor = txtNumSocioTutor.getText();
            String numero = controlSocios.getControlDatos().getSiguienteCodigo(3);

            if (nifExiste(nif)) {
                labelMensaje.setText("El NIF introducido ya existe. Introduce otro NIF.");
                return;
            }

            switch (tipoSocio) {
                case "Estándar":
                    Seguro seguro = Seguro.valueOf(seguroInput);
                    Estandar estandar = new Estandar(nombre, numero, nif, seguro);
                    controlSocios.addSocio(estandar);
                    labelMensaje.setText("Socio Estándar añadido con éxito.");
                    break;

                case "Federado":
                    String codigoFederacion = controlSocios.getControlPeticiones().pedirString("Introduce el código de la federación a la que pertenece el socio: ");
                    Federado federado = new Federado(nombre, numero, nif, controlSocios.getControlDatos().getFederacion(codigoFederacion));
                    controlSocios.addSocio(federado);
                    labelMensaje.setText("Socio Federado añadido con éxito.");
                    break;

                case "Infantil":
                    Infantil infantil = new Infantil(nombre, numero, nif, numSocioTutor);
                    controlSocios.addSocio(infantil);
                    labelMensaje.setText("Socio Infantil añadido con éxito.");
                    break;

                default:
                    labelMensaje.setText("Tipo de socio no válido.");
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            labelMensaje.setText("Error al añadir socio.");
        }
    }

    private boolean nifExiste(String nif) throws SQLException {
        List<Socio> listSocios = controlSocios.listSocios();
        for (Socio socio : listSocios) {
            if (socio.getNifSocio().equals(nif)) {
                return true;
            }
        }
        return false;
    }

    @FXML
    private void handleAtras(ActionEvent event) {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }
}
