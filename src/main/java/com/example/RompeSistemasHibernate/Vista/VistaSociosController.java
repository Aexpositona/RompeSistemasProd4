package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlPeticiones;
import com.example.RompeSistemasHibernate.Controlador.ControlSocios;
import com.example.RompeSistemasHibernate.Modelo.Socio;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

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
    private Label lblMensaje;

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
    private void handleButtonRemoveSocio() throws SQLException {
        String codigoSocio = cPeticiones.pedirString("Introduzca el código del socio a eliminar: ");
        Socio socio = cSocios.getControlDatos().getSocio(codigoSocio);
        if (socio != null) {
            cSocios.removeSocio(socio);
        } else {
            txtMostrarMensaje("Socio no encontrado.\n");
        }
    }

    @FXML
    private void handleButtonModificarSeguro() throws SQLException, ParseException, IOException {
        cSocios.showVistaModificarSeguro();
    }

    @FXML
    private void handleButtonListSocios() throws SQLException, ParseException, IOException {
        cSocios.showVistaListarSocios();
    }

    @FXML
    private void handleButtonMostrarFacturaMensual() throws SQLException {
        cSocios.calcularFacturaMensualSocios();
    }

    @FXML
    private void handleButtonMostrarFacturaFechas() throws SQLException {
        LocalDate fechaInicial = cPeticiones.pedirFecha("Introduzca la fecha inicial (YYYY-MM-DD): ", LocalDate.parse("2000-01-01"), LocalDate.now());
        LocalDate fechaFinal = cPeticiones.pedirFecha("Introduzca la fecha final (YYYY-MM-DD): ", fechaInicial, LocalDate.now());
        cSocios.calcularFacturaFechas(fechaInicial, fechaFinal);
    }

    @FXML
    private void handleButtonMostrarFacturaFechasSocio() throws SQLException {
        String codigoSocio = cPeticiones.pedirString("Introduzca el código del socio: ");
        LocalDate fechaInicial = cPeticiones.pedirFecha("Introduzca la fecha inicial (YYYY-MM-DD): ", LocalDate.parse("2000-01-01"), LocalDate.now());
        LocalDate fechaFinal = cPeticiones.pedirFecha("Introduzca la fecha final (YYYY-MM-DD): ", fechaInicial, LocalDate.now());
        cSocios.calcularFacturasFechasSocio(codigoSocio, fechaInicial, fechaFinal);
    }

    @FXML
    private void handleButtonAtras() {
        txtMostrarMensaje("Volviendo al menú principal...\n\n");
        stage.close();
    }

    public void txtMostrarMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
    }

}
