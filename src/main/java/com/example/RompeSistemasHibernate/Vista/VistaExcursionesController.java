package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlExcursiones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VistaExcursionesController {

    private ControlExcursiones controlExcursiones;
    private Stage stage;

    public void initialize() {
        this.controlExcursiones = controlExcursiones;
        this.stage = stage;
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
        controlExcursiones.removeExcursion(); // Asumiendo que hay un método para eliminar excursiones en el controlador
    }

    @FXML
    private void handleBack(ActionEvent event) {
        stage.close();
    }

    public void setControlExcursiones(ControlExcursiones cExcursiones) {
        this.controlExcursiones = cExcursiones;
        initialize();
    }
}
