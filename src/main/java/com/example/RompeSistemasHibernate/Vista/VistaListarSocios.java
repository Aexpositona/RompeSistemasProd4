package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlPeticiones;
import com.example.RompeSistemasHibernate.Controlador.ControlSocios;

import java.sql.SQLException;
import java.text.ParseException;

public class VistaListarSocios {
    // Atributos
    private ControlSocios cSocios;
    private ControlPeticiones cPeticiones;

    /**
     * Constructor de la clase VistaListarSocios que recibe por parámetros el controlador de socios
     * @param cSocios es el controlador de socios
     */
    public VistaListarSocios(ControlSocios cSocios) {
        this.cSocios = cSocios;
        this.cPeticiones = cSocios.getControlPeticiones();
    }

    /**
     * Constructor de copia de la clase VistaListarSocios
     * @param vistaListarSocios VistaListarSocios a copiar
     */
    public VistaListarSocios(VistaListarSocios vistaListarSocios) {
        this.cSocios = vistaListarSocios.getControlSocios();
        this.cPeticiones = vistaListarSocios.getControlPeticiones();
    }

    /**
     * Constructor vacío de la clase VistaListarSocios
     */
    public VistaListarSocios() {
        this.cSocios = null;
        this.cPeticiones = null;
    }

    // Getters
    public ControlSocios getControlSocios() {
        return cSocios;
    }

    public ControlPeticiones getControlPeticiones() {
        return cPeticiones;
    }

    // Setters
    public void setControlSocios(ControlSocios cSocios) {
        this.cSocios = cSocios;
    }

    public void setControlPeticiones(ControlPeticiones cPeticiones) {
        this.cPeticiones = cPeticiones;
    }

    /**
     * Método para añadir un botón que nos permite listar los tipos de socios
     */
    private void buttonListTipoSocio() throws SQLException {
        int tipo = cPeticiones.pedirEntero("\nIntroduzca el tipo de socio a listar:\n 1. Estándar\n 2. Federado\n 3. Infantil\n Seleccione una opción (1, 2 o 3): ", 1, 3);
        cSocios.listTipoSocios(tipo);
    }

    /**
     * Método para añadir un botón que nos permite listar todos los socios
     */
    private void buttonListSocios() throws SQLException {
        cSocios.listSocios();
    }

    /**
     * Método para añadir un botón que nos permite ir hacia atrás
     */
    private void buttonAtras() {
        txtMostrarMensaje("Volviendo a la vista anterior...\n\n");
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
     * Método para mostrar la vista de listar socios
     */
    public void show() throws ParseException, SQLException {
        boolean running = true;
        while (running) {
            txtMostrarMensaje("************ MENÚ LISTAR SOCIOS ************\n");
            txtMostrarMensaje("1. Listar por tipo de socio\n");
            txtMostrarMensaje("2. Listar todos los socios\n");
            txtMostrarMensaje("0. Atrás\n");
            switch (cPeticiones.pedirEntero("Seleccione una opción (1, 2 o 0): ", 0, 2)) {
                case 1:
                    buttonListTipoSocio();
                    break;
                case 2:
                    buttonListSocios();
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
