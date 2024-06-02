package com.example.RompeSistemasHibernate.Controlador;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.SQLException;

public class APPSenderosMontanas extends Application {

    // Atributos
    private ControlMenuPrincipal cMenuPrincipal;
    private ControlInscripciones cInscripciones;
    private ControlSocios cSocios;
    private ControlExcursiones cExcursiones;
    private ControlDatos cDatos;
    private ControlPeticiones cPeticiones;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws SQLException, IOException {
        iniciar();
        mostrarMenuPrincipal();
    }

    public void iniciar() throws SQLException, IOException {
        entityManagerFactory = Persistence.createEntityManagerFactory("AppSenderosMontanasPU");
        entityManager = entityManagerFactory.createEntityManager();

        cPeticiones = new ControlPeticiones();
        cDatos = new ControlDatos(entityManager);

        cSocios = new ControlSocios(this, cDatos, cPeticiones, entityManager);
        cExcursiones = new ControlExcursiones(this, cDatos, cPeticiones, entityManager);
        cInscripciones = new ControlInscripciones(entityManager, cDatos, cPeticiones, cSocios, cExcursiones);
        cMenuPrincipal = new ControlMenuPrincipal(this, cDatos, cPeticiones, entityManager);
    }

    private void mostrarMenuPrincipal() throws IOException {
        cMenuPrincipal.showVistaMenuPrincipal();
    }

    @Override
    public void stop() {
        close();
    }

    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
