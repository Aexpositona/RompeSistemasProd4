package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlExcursiones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class VistaExcursionesController {

    private ControlExcursiones controlExcursiones;
    private Stage stage;

    @FXML
    private TextField codigoExcursionField;
    @FXML
    private Label labelMensaje;

    public void initialize(ControlExcursiones controlExcursiones, Stage stage) {
        this.controlExcursiones = controlExcursiones;
        this.stage = stage;
    }
    public void setControlExcursiones(ControlExcursiones cExcursiones) {
        this.controlExcursiones = cExcursiones;
        initialize();
    }
    @FXML
    public void initialize() {
        if (codigoExcursionField != null) {
            codigoExcursionField.setText("");
        }
    }

    @FXML
    private void handleAddExcursion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VistaAddExcursion.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            VistaAddExcursionController controller = loader.getController();
            controller.initialize(controlExcursiones, newStage);
            newStage.setTitle("Añadir Excursión");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleListExcursiones(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VistaListarExcursiones.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            VistaListarExcursionesController controller = loader.getController();
            controller.initialize(controlExcursiones, newStage);
            newStage.setTitle("Listar Excursiones");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRemoveExcursion(ActionEvent event) {
        String codigo = codigoExcursionField.getText();
        try {
            if (controlExcursiones.checkExistenciaExcursion(codigo)) {
                controlExcursiones.removeExcursion(codigo);
                labelMensaje.setText("Excursión eliminada correctamente.");
            } else {
                labelMensaje.setText("Código de excursión no válido.");
            }
        } catch (SQLException e) {
            labelMensaje.setText("Error al eliminar la excursión: " + e.getMessage());
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        stage.close();
    }


}
