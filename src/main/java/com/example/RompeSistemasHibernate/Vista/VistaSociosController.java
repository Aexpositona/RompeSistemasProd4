package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlPeticiones;
import com.example.RompeSistemasHibernate.Controlador.ControlSocios;
import com.example.RompeSistemasHibernate.Modelo.Socio;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

public class VistaSociosController {

    private ControlSocios cSocios;
    private ControlPeticiones cPeticiones;
    private Stage stage;

    @FXML
    private Button btnAddSocio;
    @FXML
    private Button btnRemoveSocio;
    @FXML
    private Button btnModificarSeguro;
    @FXML
    private Button btnListSocios;
    @FXML
    private Button btnMostrarFacturaMensual;
    @FXML
    private Button btnMostrarFacturaFechas;
    @FXML
    private Button btnMostrarFacturaFechasSocio;
    @FXML
    private Button btnAtras;
    @FXML
    private Button btnListSociosremove;
    @FXML
    private Label lblMensaje;
    @FXML
    private ListView<String> listViewSocios;
    @FXML
    private TextArea textAreaResultados;
    @FXML
    private DatePicker fechaInicialPicker;
    @FXML
    private DatePicker fechaFinalPicker;

    public void initialize(ControlSocios cSocios, Stage stage) {
        this.cSocios = cSocios;
        this.cPeticiones = cSocios.getControlPeticiones();
        this.stage = stage;
    }

    @FXML
    private void handleButtonAddSocio() throws SQLException, ParseException, IOException {
        cSocios.showVistaAddSocio();
    }

    @FXML
    private void handleButtonRemoveSocio() {
        String selectedSocio = listViewSocios.getSelectionModel().getSelectedItem();
        if (selectedSocio != null) {
            String codigoSocio = selectedSocio.split(",")[0].split(":")[1].trim();
            Socio socio = cSocios.getControlDatos().getSocio(codigoSocio);
            try {
                if (socio != null) {
                    cSocios.removeSocio(socio);
                    lblMensaje.setText("Socio eliminado correctamente.");
                    cargarSocios();
                } else {
                    lblMensaje.setText("Socio no encontrado.");
                }
            } catch (SQLException e) {
                lblMensaje.setText("Error al eliminar el socio: " + e.getMessage());
            }
        } else {
            lblMensaje.setText("Debe seleccionar un socio.");
        }
    }

    @FXML
    private void handleButtonModificarSeguro() throws SQLException, ParseException, IOException {
        cSocios.showVistaModificarSeguro();
    }

    @FXML
    private void handleButtonListSocios() throws IOException {
        cSocios.showVistaListarSocios();
    }

    @FXML
    private void handleButtonListSociosremove() {
        cargarSocios();
    }
    @FXML
    private void handleButtonMostrarFacturaMensual() {
        String resultado = cSocios.calcularFacturaMensualSocios();
        textAreaResultados.setText(resultado);
    }

    @FXML
    private void handleButtonMostrarFacturaFechas() {
        LocalDate fechaInicial = fechaInicialPicker.getValue();
        LocalDate fechaFinal = fechaFinalPicker.getValue();

        if (fechaInicial == null || fechaFinal == null) {
            lblMensaje.setText("Debe seleccionar ambas fechas.");
            return;
        }

        String resultado = cSocios.calcularFacturaFechas(fechaInicial, fechaFinal);
        textAreaResultados.setText(resultado);
    }

    @FXML
    private void handleButtonMostrarFacturaFechasSocio() {
        String selectedSocio = listViewSocios.getSelectionModel().getSelectedItem();
        if (selectedSocio == null) {
            lblMensaje.setText("Debe seleccionar un socio.");
            return;
        }

        String codigoSocio = selectedSocio.split(",")[0].split(":")[1].trim();
        LocalDate fechaInicial = fechaInicialPicker.getValue();
        LocalDate fechaFinal = fechaFinalPicker.getValue();

        if (fechaInicial == null || fechaFinal == null) {
            lblMensaje.setText("Debe seleccionar ambas fechas.");
            return;
        }

        String resultado = cSocios.calcularFacturasFechasSocio(codigoSocio, fechaInicial, fechaFinal);
        textAreaResultados.setText(resultado);
    }

    @FXML
    private void handleButtonAtras() {
        txtMostrarMensaje("Volviendo al menú principal...\n\n");
        stage.close();
    }

    public void txtMostrarMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
    }

    private void cargarSocios() {
        List<Socio> socios = cSocios.listSocios();
        listViewSocios.getItems().clear();
        for (Socio socio : socios) {
            listViewSocios.getItems().add("Código: " + socio.getCodigoSocio() + ", NIF: " + socio.getNifSocio() + ", Nombre: " + socio.getNombreSocio());
        }
    }
}
