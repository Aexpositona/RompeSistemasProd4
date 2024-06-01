package com.example.RompeSistemasHibernate.Vista;

import com.example.RompeSistemasHibernate.Controlador.ControlPeticiones;
import com.example.RompeSistemasHibernate.Controlador.ControlSocios;
import com.example.RompeSistemasHibernate.Modelo.Socio;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

public class VistaSocios {
    private ControlSocios cSocios;
    private ControlPeticiones cPeticiones;

    public VistaSocios(ControlSocios cSocios) {
        this.cSocios = cSocios;
        this.cPeticiones = cSocios.getControlPeticiones(); // Asegurarse de que cPeticiones se inicializa aquí
    }

    public VistaSocios(VistaSocios vistaSocios) {
        this.cSocios = vistaSocios.getControlSocios();
        this.cPeticiones = vistaSocios.getControlPeticiones();
    }

    public VistaSocios() {
        this.cSocios = null;
        this.cPeticiones = null;
    }

    public ControlSocios getControlSocios() {
        return cSocios;
    }

    public ControlPeticiones getControlPeticiones() {
        return cPeticiones;
    }

    public void setControlSocios(ControlSocios cSocios) {
        this.cSocios = cSocios;
    }

    public void setControlPeticiones(ControlPeticiones cPeticiones) {
        this.cPeticiones = cPeticiones;
    }

    // Métodos

    /**
     * Método para añadir un botón que nos permite añadir un socio
     */
    public void buttonAddSocio() throws SQLException, ParseException {
        cSocios.showVistaAddSocio();
    }

    /**
     * Método para añadir un botón que nos permite eliminar un socio
     */
    public void buttonRemoveSocio() throws SQLException {
        String codigoSocio = cPeticiones.pedirString("Introduzca el código del socio a eliminar: ");
        Socio socio = cSocios.getControlDatos().getSocio(codigoSocio);
        if (socio != null) {
            cSocios.removeSocio(socio);
        } else {
            txtMostrarMensaje("Socio no encontrado.\n");
        }
    }

    /**
     * Método para añadir un botón que nos permite modificar el tipo de seguro de un socio
     */
    public void buttonModificarSeguro() throws SQLException, ParseException {
        cSocios.showVistaModificarSeguro();
    }

    /**
     * Método para añadir un botón que nos permite listar los socios
     */
    public void buttonListSocios() throws SQLException, ParseException {
        cSocios.showVistaListarSocios();
    }

    /**
     * Método para añadir un botón que nos permite mostrar la factura mensual de los socios
     */
    public void buttonMostrarFacturaMensual() throws SQLException {
        cSocios.calcularFacturaMensualSocios();
    }

    /**
     * Método para añadir un botón que nos permite mostrar la factura entre dos fechas
     */
    public void buttonMostrarFacturaFechas() throws SQLException {
        LocalDate fechaInicial = cPeticiones.pedirFecha("Introduzca la fecha inicial (YYYY-MM-DD): ", LocalDate.parse("2000-01-01"), LocalDate.now());
        LocalDate fechaFinal = cPeticiones.pedirFecha("Introduzca la fecha final (YYYY-MM-DD): ", fechaInicial, LocalDate.now());
        cSocios.calcularFacturaFechas(fechaInicial, fechaFinal);
    }

    /**
     * Método para añadir un botón que nos permite mostrar la factura entre dos fechas de un socio
     */
    public void buttonMostrarFacturaFechasSocio() throws SQLException {
        String codigoSocio = cPeticiones.pedirString("Introduzca el código del socio: ");
        LocalDate fechaInicial = cPeticiones.pedirFecha("Introduzca la fecha inicial (YYYY-MM-DD): ", LocalDate.parse("2000-01-01"), LocalDate.now());
        LocalDate fechaFinal = cPeticiones.pedirFecha("Introduzca la fecha final (YYYY-MM-DD): ", fechaInicial, LocalDate.now());
        cSocios.calcularFacturasFechasSocio(codigoSocio, fechaInicial, fechaFinal);
    }

    /**
     * Método para añadir un botón que nos permite ir hacia atrás
     */
    public void buttonAtras() {
        txtMostrarMensaje("Volviendo al menú principal...\n\n");
    }

    /**
     * Método para mostrar un mensaje.
     *
     * @param mensaje Mensaje a mostrar.
     */
    public void txtMostrarMensaje(String mensaje) {
        System.out.print(mensaje);
    }

    public void show() throws ParseException, SQLException {
        boolean running = true;
        while (running) {
            txtMostrarMensaje("************ MENÚ SOCIOS ************\n");
            txtMostrarMensaje("1. Añadir socio\n");
            txtMostrarMensaje("2. Eliminar socio\n");
            txtMostrarMensaje("3. Modificar tipo de seguro\n");
            txtMostrarMensaje("4. Listar socios\n");
            txtMostrarMensaje("5. Mostrar factura mensual de los socios\n");
            txtMostrarMensaje("6. Mostrar factura entre dos fechas\n");
            txtMostrarMensaje("7. Mostrar factura entre dos fechas de un socio\n");
            txtMostrarMensaje("0. Atrás\n");
            switch (cPeticiones.pedirEntero("Seleccione una opción (1, 2, 3, 4, 5, 6, 7 o 0): ", 0, 7)) {
                case 1:
                    buttonAddSocio();
                    break;
                case 2:
                    buttonRemoveSocio();
                    break;
                case 3:
                    buttonModificarSeguro();
                    break;
                case 4:
                    buttonListSocios();
                    break;
                case 5:
                    buttonMostrarFacturaMensual();
                    break;
                case 6:
                    buttonMostrarFacturaFechas();
                    break;
                case 7:
                    buttonMostrarFacturaFechasSocio();
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
