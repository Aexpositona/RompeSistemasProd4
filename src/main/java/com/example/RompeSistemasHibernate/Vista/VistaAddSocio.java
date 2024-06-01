package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.*;
import com.example.RompeSistemasHibernate.Modelo.*;
import com.example.RompeSistemasHibernate.ModeloDAO.InfantilDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class VistaAddSocio {
    // Atributos
    private ControlSocios cSocios;
    private ControlPeticiones cPeticiones;
    private ControlDatos cDatos;
    private InfantilDAO infantilDAO;

    // Constructores
    public VistaAddSocio(ControlSocios cSocios) {
        this.cSocios = cSocios;
        this.cPeticiones = cSocios.getControlPeticiones();
        this.cDatos = cSocios.getControlDatos();
    }

    public VistaAddSocio(VistaAddSocio vistaAddSocio) {
        this.cSocios = vistaAddSocio.getControlSocios();
        this.cPeticiones = vistaAddSocio.getControlPeticiones();
        this.cDatos = vistaAddSocio.getControlDatos();
    }

    public VistaAddSocio() {
        this.cSocios = null;
        this.cPeticiones = null;
        this.cDatos = null;
    }

    // Getters
    public ControlSocios getControlSocios() {
        return cSocios;
    }

    public ControlPeticiones getControlPeticiones() {
        return cPeticiones;
    }

    public ControlDatos getControlDatos() {
        return cDatos;
    }

    // Setters
    public void setControlSocios(ControlSocios cSocios) {
        this.cSocios = cSocios;
    }

    public void setControlPeticiones(ControlPeticiones cPeticiones) {
        this.cPeticiones = cPeticiones;
    }

    public void setControlDatos(ControlDatos cDatos) {
        this.cDatos = cDatos;
    }

    // Métodos

    /**
     * Método para añadir un socio
     */
    public void buttonAddSocio() throws SQLException {
        // Variables internas
        String numero, nombre = "", nif;
        Seguro seguro = null;
        txtMostrarMensaje("-- Procediendo a añadir un socio --\n");
        txtMostrarMensaje("Tipos de socio:\n");
        txtMostrarMensaje("1. Estándar\n");
        txtMostrarMensaje("2. Federado\n");
        txtMostrarMensaje("3. Infantil\n");
        int tipoSocio = cPeticiones.pedirEntero("Introduzca el tipo de socio que desea añadir: (1, 2 o 3)", 1, 3);

        // Mientras el NIF introducido ya exista, pedir otro NIF
        do {
            nif = cPeticiones.pedirNIF();
            if (!nifExiste(nif)) {
                break;
            } else {
                txtMostrarMensaje("El NIF introducido ya existe. Introduce otro NIF.");
            }
        } while (true);

        // Pedir nombre del socio mediante un método
        nombre = pedirNombreSocio();
        // Obtener el número de socio mediante un método
        numero = obtenerNumeroSocio();

        switch (tipoSocio) {
            case 1: // Socio Estándar
                // Mostrar tipos de seguro disponibles
                txtMostrarMensaje("Tipos de seguro disponibles:\n");
                Seguro[] seguros = Seguro.values();
                for (Seguro value : seguros) {
                    String seguroName = value.name().charAt(0) + value.name().substring(1).toLowerCase();
                    txtMostrarMensaje(seguroName + "\n");
                }
                // Pedir tipo de seguro
                String seguroInput = cPeticiones.pedirString("Introduce el nombre del tipo de seguro del socio: ");
                seguro = Seguro.valueOf(seguroInput.toUpperCase());
                // Añadir socio tipo Estándar mediante el controlador de socios
                Estandar estandar = new Estandar(nombre, numero, nif, seguro);
                cSocios.addSocio(estandar);
                txtMostrarMensaje("Socio Estándar añadido con éxito.\n");
                break;

            case 2: // Socio Federado
                String codigoFederacion = cPeticiones.pedirString("Introduce el código de la federación a la que pertenece el socio: ");
                Federacion federacion = cDatos.getFederacion(codigoFederacion);
                if (federacion != null) {
                    Federado federado = new Federado(nombre, numero, nif, federacion);
                    cSocios.addSocio(federado);
                    txtMostrarMensaje("Socio Federado añadido con éxito.\n");
                } else {
                    txtMostrarMensaje("El código de federación introducido no existe. Intente de nuevo.");
                }
                break;

            case 3: // Socio Infantil
                String numSocioTutor = "";
                do {
                    String codigoSocioTutor = cPeticiones.pedirString("Introduce el número del socio tutor: ");
                    if (cSocios.getSocio(codigoSocioTutor) != null) {
                        numSocioTutor = codigoSocioTutor;
                        break;
                    } else {
                        txtMostrarMensaje("El socio tutor no existe. Introduce un número de socio válido.");
                    }
                } while (true);
                Infantil infantil = new Infantil(nombre, numero, nif, numSocioTutor);
                cSocios.addSocio(infantil);
                txtMostrarMensaje("Socio Infantil añadido con éxito.\n");
                break;

            default:
                txtMostrarMensaje("Tipo de socio no válido.\n");
                break;
        }
    }

    private boolean nifExiste(String nif) throws SQLException {
        List<Socio> listSocios = cSocios.listSocios();
        for (Socio socio : listSocios) {
            if (socio.getNif().equals(nif)) {
                return true;
            }
        }
        return false;
    }

    public void buttonAtras() throws ParseException {
        // Informamos al usuario de que volvemos al menú de socios
        txtMostrarMensaje("Volviendo al menú de socios...\n\n");
    }

    // Comprobar último número de socio y devolver el siguiente
    private String obtenerNumeroSocio() throws SQLException {
        // Si no hay socios, devolver "SOC001"
        if (cDatos.getSiguienteCodigo(3).isEmpty()) {
            return "SOC001";
        }
        // Si hay socios, devolver el siguiente número de socio
        else {
            return cDatos.getSiguienteCodigo(3);
        }
    }

    private String pedirNombreSocio() {
        return cPeticiones.pedirString("Introduce el nombre del socio: ");
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
     * Método para mostrar la vista de añadir socio
     */
    public void show() throws ParseException, SQLException {
        // Variables internas
        boolean running = true;
        // Mientras se ejecute la vista
        while (running) {
            // Mostramos menú de añadir socio
            txtMostrarMensaje("************ MENÚ AÑADIR SOCIO ************\n");
            txtMostrarMensaje("1. Añadir socio\n");
            txtMostrarMensaje("0. Atrás\n");
            switch (cPeticiones.pedirEntero("Seleccione una opción (1 o 0): ", 0, 1)) {
                case 1:
                    buttonAddSocio();
                    break;
                case 0:
                    buttonAtras();
                    running = false;
                    break;
                default:
                    txtMostrarMensaje("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }
}
