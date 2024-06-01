package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlInscripciones;
import com.example.RompeSistemasHibernate.Controlador.ControlPeticiones;

import java.sql.SQLException;

public class VistaInscripciones {
    // Atributos
    private ControlInscripciones cInscripciones;
    private VistaListarInscripciones vListarInscripciones;
    private VistaAddInscripcion vAddInscripcion;
    private ControlPeticiones cPeticiones;

    // Constructores
    public VistaInscripciones(ControlInscripciones cInscripciones) throws SQLException {
        this.cInscripciones = cInscripciones;
        this.vAddInscripcion = new VistaAddInscripcion(cInscripciones);
        this.vListarInscripciones = new VistaListarInscripciones(cInscripciones);
        this.cPeticiones = cInscripciones.getControlPeticiones();
    }

    // Getters
    public ControlPeticiones getControlPeticiones() {
        return cPeticiones;
    }

    // Setters
    public void setControlInscripciones(ControlInscripciones cInscripciones) {
        this.cInscripciones = cInscripciones;
    }

    public void setVistaAddInscripcion(VistaAddInscripcion vAddInscripcion) {
        this.vAddInscripcion = vAddInscripcion;
    }

    public void setVistaListarInscripciones(VistaListarInscripciones vListarInscripciones) {
        this.vListarInscripciones = vListarInscripciones;
    }

    public void setControlPeticiones(ControlPeticiones cPeticiones) {
        this.cPeticiones = cPeticiones;
    }

    // Métodos
    public void buttonMenuAddInscripcion() throws SQLException {
        txtMostrarMensaje("Navegando a la vista de añadir inscripción...\n\n");
        vAddInscripcion.show();
    }

    public void buttonRemoveInscripcion() throws SQLException {
        txtMostrarMensaje("\n-- Eliminando inscripción --\n\n");
        cInscripciones.listInscripciones();
        String numero = cPeticiones.pedirString("Introduzca el código de la inscripción a eliminar: ");

        if (inscripcionExiste(numero)) {
            if (cPeticiones.pedirString("¿Está seguro de que desea eliminar la inscripción? (S/N): ").equalsIgnoreCase("S")) {
                cInscripciones.removeInscripcion(numero);
                txtMostrarMensaje("Inscripción eliminada correctamente.\n\n");
            } else {
                txtMostrarMensaje("Operación cancelada.\n");
            }
        } else {
            txtMostrarMensaje("El id introducido no es válido. Inténtelo de nuevo.\n\n");
        }
    }

    private boolean inscripcionExiste(String numero) throws SQLException {
        return cInscripciones.getInscripcion(numero);
    }

    public void buttonMenuListInscripciones() throws SQLException {
        txtMostrarMensaje("Navegando a la vista de listar inscripciones...\n\n");
        vListarInscripciones.show();
    }

    public void buttonAtras() {
        txtMostrarMensaje("Volviendo al menú principal...\n\n");
    }

    public void txtMostrarMensaje(String mensaje) {
        System.out.print(mensaje);
    }

    public void show() throws SQLException {
        boolean running = true;
        while (running) {
            txtMostrarMensaje("************ MENÚ INSCRIPCIONES ************\n");
            txtMostrarMensaje("1. Añadir inscripción\n");
            txtMostrarMensaje("2. Menú Listar inscripciones\n");
            txtMostrarMensaje("3. Eliminar inscripción\n");
            txtMostrarMensaje("0. Atrás\n");
            switch (cPeticiones.pedirEntero("Seleccione una opción (1, 2, 3 o 0):", 0, 3)) {
                case 1:
                    buttonMenuAddInscripcion();
                    break;
                case 2:
                    buttonMenuListInscripciones();
                    break;
                case 3:
                    buttonRemoveInscripcion();
                    break;
                case 0:
                    buttonAtras();
                    running = false;
                    break;
                default:
                    txtMostrarMensaje("Opción no válida. Intente de nuevo.\n");
                    break;
            }
        }
    }
}
