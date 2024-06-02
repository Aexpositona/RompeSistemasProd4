package com.example.RompeSistemasHibernate.Controlador;

import com.example.RompeSistemasHibernate.Datos.SQLInscripcionDAO;
import com.example.RompeSistemasHibernate.Modelo.Inscripcion;
import com.example.RompeSistemasHibernate.ModeloDAO.InscripcionDAO;
import com.example.RompeSistemasHibernate.Vista.VistaAddInscripcionController;
import com.example.RompeSistemasHibernate.Vista.VistaListarInscripcionesController;
import com.example.RompeSistemasHibernate.Vista.VistaInscripcionesController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ControlInscripciones {
    private EntityManager entityManager;
    private ControlDatos cDatos;
    private InscripcionDAO inscripcionDAO;
    private ControlPeticiones cPeticiones;
    private ControlSocios controlSocios;
    private ControlExcursiones controlExcursiones;

    public ControlInscripciones(EntityManager entityManager, ControlDatos cDatos, ControlPeticiones cPeticiones, ControlSocios controlSocios, ControlExcursiones controlExcursiones) {
        this.entityManager = entityManager;
        this.cDatos = cDatos;
        this.cPeticiones = cPeticiones;
        this.inscripcionDAO = new SQLInscripcionDAO(entityManager);
        this.controlSocios = controlSocios;
        this.controlExcursiones = controlExcursiones;
    }

    public void addInscripcion(Inscripcion inscripcion) throws SQLException {
        inscripcionDAO.insertarInscripcion(inscripcion);
    }

    public List<Inscripcion> listInscripciones() throws SQLException {
        return inscripcionDAO.getAllInscripciones();
    }

    public List<Inscripcion> listInscripcionesSocio(String codigoSocio) throws SQLException {
        return inscripcionDAO.getInscripcionesPorSocio(codigoSocio);
    }

    public List<Inscripcion> listInscripcionesFechas(LocalDate fechaInicial, LocalDate fechaFinal) throws SQLException {
        return inscripcionDAO.getInscripcionesPorFecha(fechaInicial, fechaFinal);
    }

    public void removeInscripcion(String numero) throws SQLException {
        Inscripcion inscripcion = inscripcionDAO.getInscripcion(numero);
        if (inscripcion != null) {
            inscripcionDAO.eliminarInscripcion(inscripcion);
        }
    }

    public void showVistaAddInscripcion() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VistaAddInscripcion.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        VistaAddInscripcionController controller = loader.getController();
        controller.initialize(this, controlSocios, controlExcursiones, stage);
        stage.setTitle("Añadir Inscripción");
        stage.show();
    }

    public void showVistaListarInscripciones() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VistaListarInscripciones.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        VistaListarInscripcionesController controller = loader.getController();
        controller.initialize(this, stage);
        stage.setTitle("Listar Inscripciones");
        stage.show();
    }

    public void showVistaInscripciones() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VistaInscripciones.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        VistaInscripcionesController controller = loader.getController();
        controller.initialize(this, stage);
        stage.setTitle("Menú Inscripciones");
        stage.show();
    }

    public boolean getInscripcion(String numero) {
        return inscripcionDAO.getInscripcion(numero) != null;
    }

    // Métodos para obtener controladores y otros componentes
    public ControlPeticiones getControlPeticiones() {
        return cPeticiones;
    }

    public ControlDatos getControlDatos() {
        return cDatos;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
    public String getSiguienteCodigo() {
        return cDatos.getSiguienteCodigo(2);
    }
}
