package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlPeticiones;
import com.example.RompeSistemasHibernate.Controlador.ControlSocios;
import com.example.RompeSistemasHibernate.Modelo.Estandar;
import com.example.RompeSistemasHibernate.Modelo.Seguro;
import com.example.RompeSistemasHibernate.Modelo.Socio;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * Clase VistaModificarSeguro que nos permite modificar un seguro
 */
public class VistaModificarSeguro {

    private ControlSocios cSocios;
    private ControlPeticiones cPeticiones;

    /**
     * Método constructor de la clase VistaModificarSeguro que recibe por parámetros el número de socio y el tipo de seguro
     */
    public VistaModificarSeguro(ControlSocios cSocios) {
        this.cSocios = cSocios;
        this.cPeticiones = cSocios.getControlPeticiones();
    }

    public VistaModificarSeguro(VistaModificarSeguro vistaModificarSeguro) {
        this.cSocios = vistaModificarSeguro.getControlSocios();
        this.cPeticiones = vistaModificarSeguro.getControlPeticiones();
    }

    public VistaModificarSeguro() {
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

    // Métodos

    /**
     * Método para modificar un seguro
     */
    void buttonModificarSeguro() throws SQLException {
        String numeroSocio;
        int tipoSeguro;
        boolean valido = false;

        do {
            // Mostramos los socios
            txtMostrarMensaje("\n");
            cSocios.listSocios();
            txtMostrarMensaje("\n");
            // Solicitamos el número de socio al que se le va a modificar el seguro
            numeroSocio = cPeticiones.pedirString("Introduzca el número de socio al que se le va a modificar el seguro: ");
            // Si el número de socio es válido y es un usuario Estandar, se puede modificar el seguro
            Socio socio = cSocios.getControlDatos().getSocio(numeroSocio);
            if (socio != null && socio instanceof Estandar) {
                valido = true;
            } else {
                txtMostrarMensaje("El socio no es un usuario Estandar. No se puede modificar el seguro.\n");
            }
        } while (!valido);

        // Mostramos los seguros
        List<Seguro> seguros = cSocios.listarSeguros();
        for (Seguro seguro : seguros) {
            System.out.println("ID: " + seguro.getId() + ", Nombre: " + seguro.getNombre() + ", Precio: " + seguro.getPrecio());
        }

        // Solicitamos el tipo de seguro que se le va a asignar al socio
        tipoSeguro = cPeticiones.pedirEntero("Introduzca el tipo de seguro que se le va a asignar al socio: ", 1, Seguro.values().length);
        txtMostrarMensaje("\n");

        // Modificamos el seguro
        Estandar estandar = (Estandar) cSocios.getControlDatos().getSocio(numeroSocio);
        estandar.setSeguro(Seguro.getSeguro(tipoSeguro));
        cSocios.modificarSocio(estandar);
    }

    /**
     * Método para añadir un botón que nos permite ir hacia atrás
     */
    public void buttonAtras() throws ParseException {
        txtMostrarMensaje("Volviendo a la vista anterior\n\n");
    }

    /**
     * Método para mostrar un mensaje.
     *
     * @param mensaje Mensaje a mostrar.
     */
    public void txtMostrarMensaje(String mensaje){
        System.out.print(mensaje);
    }

    /**
     * Método para mostrar la vista de modificar seguro
     */
    public void show() throws ParseException, SQLException {
        boolean running = true;
        while (running) {
            txtMostrarMensaje("************ MENÚ MODIFICAR SEGURO ************\n");
            txtMostrarMensaje("1. Modificar seguro\n");
            txtMostrarMensaje("0. Atrás\n");
            switch (cPeticiones.pedirEntero("Seleccione una opción (1 o 0): ", 0, 1)) {
                case 1 -> buttonModificarSeguro();
                case 0 -> {
                    buttonAtras();
                    running = false;
                }
                default -> txtMostrarMensaje("Opción no válida. Inténtelo de nuevo.\n");
            }
        }
    }
}
