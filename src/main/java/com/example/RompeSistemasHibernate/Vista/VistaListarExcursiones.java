package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlExcursiones;
import com.example.RompeSistemasHibernate.Controlador.ControlPeticiones;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * VistaListarExcursiones es la clase que muestra el menú de listar excursiones y solicita una opción.
 */
public class VistaListarExcursiones {
    // Atributos
    private ControlExcursiones cExcursiones;
    private ControlPeticiones cPeticiones;

    /**
     * Constructor de VistaListarExcursiones.
     *
     * @param cExcursiones ControlExcursiones asociado a la vista.
     */
    // Constructor
    public VistaListarExcursiones(ControlExcursiones cExcursiones) {
        this.cExcursiones = cExcursiones;
        this.cPeticiones = cExcursiones.getControlPeticiones();
    }

    /**
     * Constructor de copia de VistaListarExcursiones.
     *
     * @param vistaListarExcursiones VistaListarExcursiones a copiar.
     */
    public VistaListarExcursiones(VistaListarExcursiones vistaListarExcursiones) {
        if (vistaListarExcursiones != null) {
            this.cExcursiones = vistaListarExcursiones.getControlExcursiones();
            this.cPeticiones = vistaListarExcursiones.getControlPeticiones();
        }
    }

    /**
     * Constructor vacío de VistaListarExcursiones.
     */
    public VistaListarExcursiones() {
        this.cExcursiones = null;
        this.cPeticiones = null;
    }

    // Getters
    public ControlExcursiones getControlExcursiones() {
        return cExcursiones;
    }

    public ControlPeticiones getControlPeticiones() {
        return cPeticiones;
    }

    // Setters

    public void setControlExcursiones(ControlExcursiones cExcursiones) {
        this.cExcursiones = cExcursiones;
    }

    public void setControlPeticiones(ControlPeticiones cPeticiones) {
        this.cPeticiones = cPeticiones;
    }

    /**
     * Método que nos permite listar todas las excursiones
     */
    public void buttonListExcursiones() throws SQLException {
        txtMostrarMensaje("\n");
        // Llamamos al método de ControlExcursiones que lista las excursiones
        cExcursiones.listExcursiones();
    }

    /**
     * Método que nos permite listar las excursiones entre dos fechas
     */
    public void buttonListExcursionesFechas() throws SQLException {
        // Pedimos las fechas
        LocalDate fechaInicial = cPeticiones.pedirFecha("\n-- Introduzca la fecha inicial -- ", LocalDate.parse("2000-01-01"), LocalDate.now().plusYears(2));
        LocalDate fechaFinal = cPeticiones.pedirFecha("\n-- Introduzca la fecha final -- ", fechaInicial, LocalDate.now().plusYears(2));
        // Llamamos al método de ControlExcursiones que lista las excursiones entre dos fechas
        cExcursiones.listExcursionesFechas(fechaInicial, fechaFinal);
    }

    /**
     * Método que nos permite ir hacia atrás
     */
    public void buttonAtras() {
        // Mostramos mensaje de vuelta
        txtMostrarMensaje("Volviendo al menú de excursiones...\n\n");
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
     * Método para mostrar el menú de listar excursiones y solicitar una opción.
     */
    // Métodos
    public void show() throws SQLException {
        // Declaramos una variable para controlar el bucle
        boolean running = true;
        // Mientras el bucle esté activo
        while (running) {
            // Mostramos el menú
            txtMostrarMensaje("************ MENÚ LISTAR EXCURSIONES ************\n");
            txtMostrarMensaje("1. Listar Excursiones\n");
            txtMostrarMensaje("2. Listar Excursiones por Fecha\n");
            txtMostrarMensaje("0. Atrás\n");
            // Pedimos la opción
            switch (cPeticiones.pedirEntero("Seleccione una opción (1, 2 o 0): ", 0, 2)) {
                // Si la opción es 1, listamos las excursiones
                case 1:
                    buttonListExcursiones();
                    break;
                // Si la opción es 2, listamos las excursiones entre dos fechas
                case 2:
                    buttonListExcursionesFechas();
                    break;
                // Si la opción es 0, volvemos al menú de excursiones
                case 0:
                    buttonAtras();
                    running = false;
                    break;
                // Si la opción no es ninguna de las anteriores, mostramos un mensaje de error
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}
