package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlDatos;
import com.example.RompeSistemasHibernate.Controlador.ControlExcursiones;
import com.example.RompeSistemasHibernate.Controlador.ControlPeticiones;
import com.example.RompeSistemasHibernate.Modelo.Excursion;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

/**
 * Clase que representa la vista para añadir una excursión.
 *
 */
public class VistaAddExcursion {

    // Atributos
    private ControlExcursiones cExcursiones; // Instancia de ControlExcursiones
    private ControlPeticiones cPeticiones; // Instancia de ControlPeticiones
    private ControlDatos controlDatos;

    // Constructor
    /**
     * Constructor de la clase VistaAddExcursion.
     *
     * @param cExcursiones ControlExcursiones
     */
    public VistaAddExcursion(ControlExcursiones cExcursiones) {
        this.cExcursiones = cExcursiones;
        this.cPeticiones = cExcursiones.getControlPeticiones();
        this.controlDatos = cExcursiones.getControlDatos();
    }

    public VistaAddExcursion(VistaAddExcursion vistaAddExcursion) {
        if (vistaAddExcursion != null) {
            this.cExcursiones = vistaAddExcursion.getControlExcursiones();
            this.cPeticiones = vistaAddExcursion.getControlPeticiones();
            this.controlDatos = vistaAddExcursion.getControlDatos();
        }
    }

    public VistaAddExcursion() {
        this.cExcursiones = null;
        this.cPeticiones = null;
        this.controlDatos = null;
    }

    // Getters

    public ControlExcursiones getControlExcursiones() {
        return cExcursiones;
    }

    public ControlPeticiones getControlPeticiones() {
        return cPeticiones;
    }

    public VistaAddExcursion getVistaAddExcursion() {
        return this;
    }

    // Setters

    public void setControlExcursiones(ControlExcursiones cExcursiones) {
        this.cExcursiones = cExcursiones;
    }

    public void setControlPeticiones(ControlPeticiones cPeticiones) {
        this.cPeticiones = cPeticiones;
    }

    public void setControlDatos(ControlDatos controlDatos) {
        this.controlDatos = controlDatos;
    }

    public ControlDatos getControlDatos() {
        return controlDatos;
    }

    /**
     * Método para añadir una excursión.
     *
     */
    public void buttonAddExcursion() throws SQLException {
        // Variables internas
        boolean resultado;
        String codigo, descripcion;
        LocalDate fecha;
        float precio;
        int dias;
        txtMostrarMensaje("\n-- Añadiendo nueva excursión --\n");
        // Obtener el último código de excursión y sumarle 1
        codigo = cExcursiones.getSiguienteCodigo();
        // Cambiamos el resultado a falso
        resultado = false;
        // Mientras no se introduzca una descripción válida
        do {
            // Solicitamos la descripción de la excursión
            descripcion = cPeticiones.pedirString("Introduzca la descripción de la excursión: ");
            // Si la descripción está vacía
            if (descripcion.length() < 5) {
                // Informamos al usuario de que la descripción no puede estar vacía
                txtMostrarMensaje("Descripción inválida. La descripción no puede estar vacía y ha de tener al menos 5 caracteres.\n");
            } else {
                // Cambiamos el resultado a verdadero
                resultado = true;
            }
        } while (!resultado);
        // Solicitamos la fecha de la excursión
        fecha = cPeticiones.pedirFecha("-- Introduzca a continuación la fecha de la excursión --", LocalDate.now(), LocalDate.now().plusYears(2));
        // Mientras no se introduzca un precio válido
        precio = cPeticiones.pedirFloat("Introduzca el precio de la excursión: ", 0, Float.MAX_VALUE);
        // Mientras no se introduzca un número de días válido
        dias = cPeticiones.pedirEntero("Introduzca los días de la excursión: ", 1, 100);
        // Añadimos la excursión
        cExcursiones.addExcursion(new Excursion(codigo, descripcion, fecha, dias, precio));
        // Informamos al usuario
        txtMostrarMensaje("Excursión añadida correctamente.\n\n");
    }

    /**
     * Método para añadir un botón que nos permite cancelar la operación.
     */
    public void buttonAtras() throws ParseException {
        // Informamos al usuario de que volvemos al menú de excursiones
        txtMostrarMensaje("Volviendo al menú de excursiones...\n\n");
    }

    /**
     * Método para mostrar un mensaje.
     *
     * @param mensaje Mensaje a mostrar.
     */
    private void txtMostrarMensaje(String mensaje) {
        System.out.print(mensaje);
    }

    /**
     * Método para mostrar la vista.
     */
    public void show() throws ParseException, SQLException {
        // Variables internas
        boolean running = true;
        // Mientras se ejecute la vista
        while (running) {
            // Mostramos el menú
            txtMostrarMensaje("************ MENÚ AÑADIR EXCURSIÓN ************\n");
            txtMostrarMensaje("1. Añadir excursión\n");
            txtMostrarMensaje("0. Atrás\n");
            // Solicitamos la opción
            switch (cPeticiones.pedirEntero("Seleccione una opción (1 o 0):", 0, 1)) {
                // Si la opción es 1 añadimos una excursión
                case 1:
                    buttonAddExcursion();
                    break;
                // Si la opción es 0 volvemos al menú anterior
                case 0:
                    buttonAtras();
                    running = false;
                    break;
                // Si la opción no es válida informamos al usuario
                default:
                    txtMostrarMensaje("Opción no válida. Intente de nuevo.\n");
                    break;
            }
        }
    }
}
