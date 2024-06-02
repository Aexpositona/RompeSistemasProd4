package com.example.RompeSistemasHibernate.Controlador;

import com.example.RompeSistemasHibernate.Datos.*;
import com.example.RompeSistemasHibernate.Modelo.*;
import com.example.RompeSistemasHibernate.Vista.*;
import com.example.RompeSistemasHibernate.ModeloDAO.*;
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
    private VistaAddInscripcionController vAddInscripcionController;
    private VistaListarInscripcionesController vListarInscripcionesController;
    private VistaInscripcionesController vInscripcionesController;
    private ControlPeticiones cPeticiones;

    public ControlInscripciones(EntityManager entityManager, ControlDatos cDatos, ControlPeticiones cPeticiones) {
        this.entityManager = entityManager;
        this.cDatos = cDatos;
        this.cPeticiones = cPeticiones;
        this.inscripcionDAO = new SQLInscripcionDAO(entityManager);
    }

    public void addInscripcion(Inscripcion inscripcion) throws SQLException {
        inscripcionDAO.insertarInscripcion(inscripcion);
    }

    public void listInscripciones() throws SQLException {
        List<Inscripcion> inscripciones = inscripcionDAO.getAllInscripciones();
        for (Inscripcion inscripcion : inscripciones) {
            System.out.println("Código: " + inscripcion.getNumero() + ", Fecha: " + inscripcion.getFecha() + ", Socio: " + inscripcion.getSocio().getCodigoSocio() + ", Excursión: " + inscripcion.getExcursion().getCodigoExcursion());
        }
    }

    public void listInscripcionesSocio(String codigoSocio) throws SQLException {
        List<Inscripcion> inscripciones = inscripcionDAO.getInscripcionesPorSocio(codigoSocio);
        for (Inscripcion inscripcion : inscripciones) {
            System.out.println("Código: " + inscripcion.getNumero() + ", Fecha: " + inscripcion.getFecha() + ", Socio: " + inscripcion.getSocio().getCodigoSocio() + ", Excursión: " + inscripcion.getExcursion().getCodigoExcursion());
        }
    }

    public void listInscripcionesFechas(LocalDate fechaInicial, LocalDate fechaFinal) throws SQLException {
        List<Inscripcion> inscripciones = inscripcionDAO.getInscripcionesPorFecha(fechaInicial, fechaFinal);
        for (Inscripcion inscripcion : inscripciones) {
            System.out.println("Código: " + inscripcion.getNumero() + ", Fecha: " + inscripcion.getFecha() + ", Socio: " + inscripcion.getSocio().getCodigoSocio() + ", Excursión: " + inscripcion.getExcursion().getCodigoExcursion());
        }
    }

    public void showVistaRemoveInscripcion() throws SQLException {
        String numero = cPeticiones.pedirString("Introduzca el código de la inscripción a eliminar: ");
        if (getInscripcion(numero)) {
            if (cPeticiones.pedirConfirmacion("¿Está seguro de que desea eliminar la inscripción? (S/N): ")) {
                removeInscripcion(numero);
                System.out.println("Inscripción eliminada correctamente.");
            } else {
                System.out.println("Operación cancelada.");
            }
        } else {
            System.out.println("El id introducido no es válido. Inténtelo de nuevo.\n\n");
        }
    }

    public void removeInscripcion(String numero) throws SQLException {
        Inscripcion inscripcion = inscripcionDAO.getInscripcion(numero);
        if (inscripcion != null) {
            inscripcionDAO.eliminarInscripcion(inscripcion);
            System.out.println("Inscripción eliminada correctamente.");
        } else {
            System.out.println("Inscripción no encontrada.");
        }
    }

    public void showVistaAddInscripcion() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VistaAddInscripcion.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        VistaAddInscripcionController controller = loader.getController();
        controller.initialize();
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
        controller.initialize();
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
}