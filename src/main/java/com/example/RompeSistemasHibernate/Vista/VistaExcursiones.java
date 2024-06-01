package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.*;
import com.example.RompeSistemasHibernate.Modelo.Excursion;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

/**
 * Clase que representa la vista de excursiones de la aplicación.
 *
 */
public class VistaExcursiones {

    // Atributos
    private VistaAddExcursion vAddExcursion;
    private VistaListarExcursiones vListarExcursiones;
    private ControlDatos cDatos;
    private ControlExcursiones cExcursiones;
    private ControlPeticiones cPeticiones;

    /**
     * Constructor de VistaExcursiones.
     *
     * @param cExcursiones ControlExcursiones asociado a la vista.
     */
    // Constructor
    public VistaExcursiones(ControlExcursiones cExcursiones) {
        this.cExcursiones = cExcursiones;
        this.cDatos = cExcursiones.getControlDatos();
        this.cPeticiones = cExcursiones.getControlPeticiones();
        this.vAddExcursion = new VistaAddExcursion(cExcursiones);
        this.vListarExcursiones = new VistaListarExcursiones(cExcursiones);
    }

    /**
     * Constructor de VistaExcursiones de copia.
     *
     * @param vistaExcursiones VistaExcursiones a copiar
     */
    public VistaExcursiones(VistaExcursiones vistaExcursiones) {
        if (vistaExcursiones != null) {
            this.cExcursiones = vistaExcursiones.getControlExcursiones();
            this.cDatos = vistaExcursiones.getControlDatos();
            this.cPeticiones = vistaExcursiones.getControlPeticiones();
            this.vAddExcursion = vistaExcursiones.getVistaAddExcursion();
            this.vListarExcursiones = vistaExcursiones.getVistaListarExcursiones();
        }
    }



    // Getters

    /**
     * Método para obtener el controlador de excursiones.
     *
     * @return ControlExcursiones
     */
    public ControlExcursiones getControlExcursiones() {
        return cExcursiones;
    }

    /**
     * Método para obtener el controlador de datos.
     *
     * @return ControlDatos
     */
    public ControlDatos getControlDatos() {
        return cDatos;
    }

    /**
     * Método para obtener el controlador de peticiones.
     *
     * @return ControlPeticiones
     */
    public ControlPeticiones getControlPeticiones() {
        return cPeticiones;
    }

    /**
     * Método para obtener la vista de añadir excursión.
     *
     * @return VistaAddExcursion
     */
    public VistaAddExcursion getVistaAddExcursion() {
        return vAddExcursion;
    }

    /**
     * Método para obtener la vista de listar excursiones.
     *
     * @return VistaListarExcursiones
     */
    public VistaListarExcursiones getVistaListarExcursiones() {
        return vListarExcursiones;
    }

    // Setters

    /**
     * Método para establecer el controlador de excursiones.
     *
     * @param cExcursiones ControlExcursiones
     */
    public void setControlExcursiones(ControlExcursiones cExcursiones) {
        this.cExcursiones = cExcursiones;
    }

    /**
     * Método para establecer el controlador de datos.
     *
     * @param cDatos ControlDatos
     */
    public void setControlDatos(ControlDatos cDatos) {
        this.cDatos = cDatos;
    }

    /**
     * Método para establecer el controlador de peticiones.
     *
     * @param cPeticiones ControlPeticiones
     */
    public void setControlPeticiones(ControlPeticiones cPeticiones) {
        this.cPeticiones = cPeticiones;
    }

    /**
     * Método para establecer la vista de añadir excursión.
     *
     * @param vAddExcursion VistaAddExcursion
     */
    public void setVistaAddExcursion(VistaAddExcursion vAddExcursion) {
        this.vAddExcursion = vAddExcursion;
    }

    /**
     * Método para establecer la vista de listar excursiones.
     *
     * @param vListarExcursiones VistaListarExcursiones
     */
    public void setVistaListarExcursiones(VistaListarExcursiones vListarExcursiones) {
        this.vListarExcursiones = vListarExcursiones;
    }

    // Métodos

    /**
     * Método para añadir una excursión.
     *
     * @throws ParseException Excepción de parseo.
     */
    private void buttonVistaAddExcursion() throws ParseException, SQLException {
        txtMostrarMensaje("Accediendo a la vista de añadir excursión...\n");
        cExcursiones.showVistaAddExcursion();
    }

    /**
     * Método para eliminar una excursión.
     */
    private void buttonRemoveExcursion() throws SQLException {
        String codigoExcursion;
        txtMostrarMensaje("\n-- Eliminando excursión --\n\n");
        cExcursiones.listExcursiones();

        while (true) {
            txtMostrarMensaje("\n-- Seleccionando Excursión a eliminar --\n");
            codigoExcursion = cPeticiones.pedirString("Introduzca el código de la excursión a eliminar: ");
            if (cExcursiones.checkExistenciaExcursion(codigoExcursion)) {
                break;
            } else {
                txtMostrarMensaje("El código de la excursión no es válido. Inténtelo de nuevo.\n");
            }
        }

        if (cPeticiones.pedirConfirmacion("¿Está seguro de que desea eliminar la excursión? (S/N): ")) {
            Excursion excursion = cExcursiones.getExcursion(codigoExcursion);
            cExcursiones.removeExcursion(excursion);
            txtMostrarMensaje("Excursión eliminada correctamente.\n");
        } else {
            txtMostrarMensaje("Operación cancelada.\n");
        }
    }

    /**
     * Método para listar las excursiones.
     *
     * @throws SQLException Excepción SQL.
     */
    private void buttonVistaListExcursiones() throws SQLException {
        // Informamos al usuario de que accedemos a la vista de listar excursiones
        txtMostrarMensaje("Accediendo a la vista de listar excursiones...\n");
        // Mostramos la vista de listar excursiones
        vListarExcursiones.show();
    }

    /**
     * Método para volver al menú principal.
     */
    private void buttonAtras() {
        // Informamos al usuario de que volvemos al menú principal
        txtMostrarMensaje("Volviendo al menú principal...\n");
    }

    /**
     * Método para mostrar un mensaje.
     *
     * @param mensaje Mensaje a mostrar.
     */
    private void txtMostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Método para mostrar la vista.
     *
     * @throws ParseException Excepción de parseo.
     */
    public void show() throws ParseException, SQLException {
        // Declaramos una variable para controlar el bucle
        boolean running = true;
        // Mientras el bucle esté activo
        while (running) {
            txtMostrarMensaje("************ MENÚ EXCURSIONES ************");
            txtMostrarMensaje("1. Añadir excursión");
            txtMostrarMensaje("2. Listar excursiones");
            txtMostrarMensaje("3. Eliminar excursión");
            txtMostrarMensaje("0. Atrás");
            // Pedimos una opción
            switch (cPeticiones.pedirEntero("Seleccione una opción (1, 2, 3 o 0):", 0, 3)) {
                // Si la opción es 1, añadimos una excursión
                case 1:
                    buttonVistaAddExcursion();
                    break;
                // Si la opción es 2, listamos las excursiones
                case 2:
                    buttonVistaListExcursiones();
                    break;
                // Si la opción es 3, eliminamos una excursión
                case 3:
                    buttonRemoveExcursion();
                    break;
                // Si la opción es 0, volvemos al menú principal
                case 0:
                    buttonAtras();
                    running = false;
                    break;
                // Si la opción no es válida, informamos al usuario
                default:
                    txtMostrarMensaje("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }
}
