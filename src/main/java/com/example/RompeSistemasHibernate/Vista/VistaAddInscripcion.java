package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.*;
import com.example.RompeSistemasHibernate.Modelo.Excursion;
import com.example.RompeSistemasHibernate.Modelo.Inscripcion;
import com.example.RompeSistemasHibernate.Modelo.Socio;

import javax.persistence.EntityManager;
import java.sql.SQLException;

/**
 * Vista de añadir inscripción de la aplicación.
 */
public class VistaAddInscripcion {

    // Atributos
    private ControlInscripciones cInscripciones;
    private ControlPeticiones cPeticiones;
    private ControlDatos cDatos;
    private ControlExcursiones cExcursiones;
    private ControlSocios cSocios;
    private EntityManager em;
    private APPSenderosMontanas app;

    // Constructores

    /**
     * Método constructor de la clase VistaAddInscripcion que recibe por parámetros la vista de control de inscripciones.
     *
     * @param cInscripciones ControlInscripciones
     */
    public VistaAddInscripcion(ControlInscripciones cInscripciones) throws SQLException {
        this.cInscripciones = cInscripciones;
        this.cPeticiones = cInscripciones.getControlPeticiones();
        this.cDatos = cInscripciones.getControlDatos();
        this.cExcursiones = new ControlExcursiones(app, cDatos, cPeticiones, em);
        this.cSocios = new ControlSocios(app, cDatos, cPeticiones, em);
    }

    /**
     * Método constructor de copia de la clase VistaAddInscripcion.
     *
     * @param vistaAddInscripcion VistaAddInscripcion a copiar.
     */
    public VistaAddInscripcion(VistaAddInscripcion vistaAddInscripcion) {
        this.cInscripciones = vistaAddInscripcion.getControlInscripciones();
        this.cPeticiones = vistaAddInscripcion.getControlPeticiones();
        this.cDatos = vistaAddInscripcion.getControlDatos();
        this.cExcursiones = vistaAddInscripcion.getControlExcursiones();
        this.cSocios = vistaAddInscripcion.getControlSocios();
    }

    /**
     * Método constructor vacío de la clase VistaAddInscripcion.
     */
    public VistaAddInscripcion() {
        this.cInscripciones = null;
        this.cPeticiones = null;
        this.cDatos = null;
        this.cExcursiones = null;
        this.cSocios = null;
    }

    // Getters

    public ControlInscripciones getControlInscripciones() {
        return cInscripciones;
    }

    public ControlPeticiones getControlPeticiones() {
        return cPeticiones;
    }

    public ControlDatos getControlDatos() {
        return cDatos;
    }

    public ControlExcursiones getControlExcursiones() {
        return cExcursiones;
    }

    public ControlSocios getControlSocios() {
        return cSocios;
    }

    // Setters

    public void setControlInscripciones(ControlInscripciones cInscripciones) {
        this.cInscripciones = cInscripciones;
    }

    public void setControlPeticiones(ControlPeticiones cPeticiones) {
        this.cPeticiones = cPeticiones;
    }

    public void setControlDatos(ControlDatos cDatos) {
        this.cDatos = cDatos;
    }

    public void setControlExcursiones(ControlExcursiones cExcursiones) {
        this.cExcursiones = cExcursiones;
    }

    public void setControlSocios(ControlSocios cSocios) {
        this.cSocios = cSocios;
    }

    // Métodos

    /**
     * Método para añadir una inscripción.
     */
    public void buttonAddInscripcion() throws SQLException {
        String idSocio, idExcursion;
        Socio socio = null;
        Excursion excursion = null;

        // Mostramos mensaje de añadir inscripción
        txtMostrarMensaje("\n-- Añadiendo inscripción --\n");

        // Selección del socio
        while (true) {
            cSocios.listSocios();  // Cambiado a cSocios
            txtMostrarMensaje("\n-- Seleccionando socio --\n");
            idSocio = cPeticiones.pedirString("Introduzca el código del socio: ");
            socio = cSocios.getSocio(idSocio);
            if (socio != null) {
                break;
            } else {
                txtMostrarMensaje("El id introducido no es válido. Inténtelo de nuevo.\n");
            }
        }

        // Selección de la excursión
        while (true) {
            cExcursiones.listExcursiones();  // Cambiado a cExcursiones
            txtMostrarMensaje("\n-- Seleccionando excursión --\n");
            idExcursion = cPeticiones.pedirString("Introduzca el código de la excursión: ");
            excursion = cExcursiones.getExcursion(idExcursion);
            if (excursion != null) {
                break;
            } else {
                txtMostrarMensaje("El id introducido no es válido. Inténtelo de nuevo.\n");
            }
        }

        // Creamos y añadimos la inscripción
        cInscripciones.addInscripcion(new Inscripcion(cDatos.getSiguienteCodigo(2), socio, excursion));
        txtMostrarMensaje("Inscripción añadida correctamente.\n\n");
    }

    /**
     * Método para añadir un botón que nos permite volver al menú de inscripciones.
     */
    public void buttonAtras() {
        txtMostrarMensaje("Volviendo al menú inscripciones...\n\n");
    }

    /**
     * Método para mostrar un mensaje.
     *
     * @param mensaje Mensaje a mostrar.
     */
    public void txtMostrarMensaje(String mensaje) {
        System.out.print(mensaje);
    }

    public void show() throws SQLException {
        boolean running = true;
        while (running) {
            txtMostrarMensaje("************ MENÚ AÑADIR INSCRIPCIÓN ************\n");
            txtMostrarMensaje("1. Añadir inscripción\n");
            txtMostrarMensaje("0. Atrás\n");
            switch (cPeticiones.pedirEntero("Seleccione una opción (1 o 0): ", 0, 1)) {
                case 1:
                    buttonAddInscripcion();
                    break;
                case 0:
                    buttonAtras();
                    running = false;
                    break;
                default:
                    txtMostrarMensaje("Opción no válida. Inténtelo de nuevo.\n");
                    break;
            }
        }
    }
}
