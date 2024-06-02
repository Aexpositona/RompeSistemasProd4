package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlSocios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.sql.SQLException;

public class VistaListarSociosController {

    @FXML
    private TextArea textArea;

    private ControlSocios controlSocios;

    public void setControlSocios(ControlSocios controlSocios) {
        this.controlSocios = controlSocios;
        initialize();
    }

    @FXML
    public void initialize() {
        textArea.setText("");
    }

    @FXML
    private void handleListarPorTipo(ActionEvent event) throws SQLException {
        int tipo = controlSocios.getControlPeticiones().pedirEntero(
                "Introduzca el tipo de socio a listar:\n 1. Estándar\n 2. Federado\n 3. Infantil\n Seleccione una opción (1, 2 o 3): ",
                1,
                3
        );
        String resultado = controlSocios.listTipoSocios(tipo).toString();
        textArea.setText(resultado);
    }

    @FXML
    private void handleListarTodos(ActionEvent event) throws SQLException {
        String resultado = controlSocios.listSocios().toString();
        textArea.setText(resultado);
    }
}
