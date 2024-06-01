package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlInscripciones;
import com.example.RompeSistemasHibernate.Controlador.ControlPeticiones;

import java.sql.SQLException;
import java.time.LocalDate;

public class VistaListarInscripciones {

    // Atributos
    private ControlInscripciones cInscripciones;
    private ControlPeticiones cPeticiones;

    // Constructores
    /**
     * Método constructor de la clase VistaListarInscripciones que recibe por parámetros la vista de control de inscripciones.
     *
     * @param cInscripciones ControlInscripciones
     */
    public VistaListarInscripciones(ControlInscripciones cInscripciones) {
        this.cInscripciones = cInscripciones;
        this.cPeticiones = cInscripciones.getControlPeticiones();
    }

    /**
     * Método constructor de copia de la clase VistaListarInscripciones.
     *
     * @param vistaListarInscripciones VistaListarInscripciones a copiar.
     */
    public VistaListarInscripciones(VistaListarInscripciones vistaListarInscripciones) {
        this.cInscripciones = vistaListarInscripciones.getControlInscripciones();
        this.cPeticiones = vistaListarInscripciones.getControlPeticiones();
    }

    /**
     * Método constructor vacío de la clase VistaListarInscripciones.
     */
    public VistaListarInscripciones() {
        this.cInscripciones = null;
        this.cPeticiones = null;
    }

    // Getters

    public ControlInscripciones getControlInscripciones() {
        return cInscripciones;
    }

    public ControlPeticiones getControlPeticiones() {
        return cPeticiones;
    }

    // Setters

    public void setControlInscripciones(ControlInscripciones cInscripciones) {
        this.cInscripciones = cInscripciones;
    }

    public void setControlPeticiones(ControlPeticiones cPeticiones) {
        this.cPeticiones = cPeticiones;
    }

    // Métodos

    /**
     * Método para listar todas las inscripciones.
     */
    public void buttonListInscripciones() throws SQLException {
        cInscripciones.listInscripciones();
    }

    /**
     * Método para listar las inscripciones de un socio.
     */
    public void buttonListInscripcionesSocio() throws SQLException {
        String codigoSocio = cPeticiones.pedirString("Introduzca el código del socio: ");
        cInscripciones.listInscripcionesSocio(codigoSocio);
    }

    /**
     * Método para listar las inscripciones por fechas.
     */
    public void buttonListInscripcionesFechas() throws SQLException {
        LocalDate fechaInicial = cPeticiones.pedirFecha("Introduzca la fecha inicial (YYYY-MM-DD): ", LocalDate.parse("2000-01-01"), LocalDate.now().plusYears(2));
        LocalDate fechaFinal = cPeticiones.pedirFecha("Introduzca la fecha final (YYYY-MM-DD): ", fechaInicial, LocalDate.now().plusYears(2));
        cInscripciones.listInscripcionesFechas(fechaInicial, fechaFinal);
    }

    /**
     * Método para volver al menú anterior.
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

    /**
     * Método para mostrar el menú de listar inscripciones.
     */
    public void show() throws SQLException {
        boolean running = true;
        while (running) {
            txtMostrarMensaje("************ MENÚ LISTAR INSCRIPCIONES ************\n");
            txtMostrarMensaje("1. Listar todas las inscripciones\n");
            txtMostrarMensaje("2. Listar inscripción por socio\n");
            txtMostrarMensaje("3. Listar inscripción por fechas\n");
            txtMostrarMensaje("0. Atrás\n");

            int opcion = cPeticiones.pedirEntero("Seleccione una opción (1, 2, 3 o 0): ", 0, 3);
            switch (opcion) {
                case 1:
                    buttonListInscripciones();
                    break;
                case 2:
                    buttonListInscripcionesSocio();
                    break;
                case 3:
                    buttonListInscripcionesFechas();
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
