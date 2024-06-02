package com.example.RompeSistemasHibernate.Controlador;

import com.example.RompeSistemasHibernate.Datos.*;
import com.example.RompeSistemasHibernate.Modelo.*;
import com.example.RompeSistemasHibernate.ModeloDAO.*;
import com.example.RompeSistemasHibernate.Vista.VistaAddExcursionController;
import com.example.RompeSistemasHibernate.Vista.VistaListarExcursionesController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ControlExcursiones {
    private EntityManager entityManager;
    private ControlDatos cDatos;
    private ExcursionDAO excursionDAO;
    private ControlPeticiones cPeticiones;
    private Stage primaryStage;
    private Excursion excursion;

    public ControlExcursiones(APPSenderosMontanas app, ControlDatos cDatos, ControlPeticiones cPeticiones, EntityManager entityManager) {
        this.entityManager = entityManager;
        this.cDatos = cDatos;
        this.cPeticiones = cPeticiones;
        this.excursionDAO = new SQLExcursionDAO(entityManager);
        this.excursion = excursion;
    }

    public void addExcursion(Excursion excursion) {
        excursionDAO.insertarExcursion(excursion);
    }

    public void removeExcursion(Excursion excursion) {
        entityManager.getTransaction().begin();
        try {
            excursionDAO.deleteExcursion(this.excursion);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public List<Excursion> listExcursiones() {
        return excursionDAO.getAllExcursiones();
    }

    public List<Excursion> listExcursionesFechas(LocalDate fechaInicial, LocalDate fechaFinal) {
        return excursionDAO.getExcursionesPorFecha(fechaInicial, fechaFinal);
    }

    public void showVistaListarExcursiones() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VistaListarExcursiones.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        VistaListarExcursionesController controller = loader.getController();
        controller.initialize(this, stage);
        stage.setTitle("Listar Excursiones");
        stage.show();
    }

    public void showVistaAddExcursion() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VistaAddExcursion.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        VistaAddExcursionController controller = loader.getController();
        controller.initialize(this, stage);
        stage.setTitle("Añadir Excursión");
        stage.show();
    }

    public void showVistaExcursiones() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VistaExcursiones.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Gestión de Excursiones");
        stage.show();
    }

    public String getUltimoCodigo() {
        return cDatos.getSiguienteCodigo(1); // 1 para tipo Excursión
    }

    public String getSiguienteCodigo() {
        return cDatos.getSiguienteCodigo(1); // 1 para tipo Excursión
    }

    public boolean checkExistenciaExcursion(String codigoExcursion) {
        return excursionDAO.getExcursionPorCodigo(codigoExcursion) != null;
    }

    public Excursion getExcursion(String codigoExcursion) {
        return excursionDAO.getExcursionPorCodigo(codigoExcursion);
    }

    // Método para obtener ControlDatos
    public ControlDatos getControlDatos() {
        return cDatos;
    }

    // Método para obtener ControlPeticiones
    public ControlPeticiones getControlPeticiones() {
        return cPeticiones;
    }
}
