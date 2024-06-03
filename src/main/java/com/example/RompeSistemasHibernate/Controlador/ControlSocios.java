package com.example.RompeSistemasHibernate.Controlador;

import com.example.RompeSistemasHibernate.Modelo.*;
import com.example.RompeSistemasHibernate.ModeloDAO.*;
import com.example.RompeSistemasHibernate.Datos.*;
import com.example.RompeSistemasHibernate.Vista.VistaAddSocioController;
import com.example.RompeSistemasHibernate.Vista.VistaListarSociosController;
import com.example.RompeSistemasHibernate.Vista.VistaModificarSeguroController;
import com.example.RompeSistemasHibernate.Vista.VistaSociosController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ControlSocios {
    private APPSenderosMontanas app;
    private ControlPeticiones cPeticiones;
    private ControlDatos cDatos;
    private SocioDAO socioDAO;
    private InfantilDAO infantilDAO;
    private FederadoDAO federadoDAO;
    private EstandarDAO estandarDAO;
    private SeguroDAO seguroDAO;
    private ExcursionDAO excursionDAO;
    private InscripcionDAO inscripcionDAO;
    private EntityManager em;

    public ControlSocios(APPSenderosMontanas app, ControlDatos cDatos, ControlPeticiones cPeticiones, EntityManager em) {
        this.app = app;
        this.cPeticiones = cPeticiones;
        this.cDatos = cDatos;
        this.em = em;
        this.socioDAO = new SQLSocioDAO(em);
        this.infantilDAO = new SQLInfantilDAO(em);
        this.federadoDAO = new SQLFederadoDAO(em);
        this.estandarDAO = new SQLEstandarDAO(em);
        this.seguroDAO = new SQLSeguroDAO(em);
        this.excursionDAO = new SQLExcursionDAO(em);
        this.inscripcionDAO = new SQLInscripcionDAO(em);
    }

    public void showVistaSocios() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VistaSocios.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        VistaSociosController vSociosController = loader.getController();
        vSociosController.initialize(this, stage);
        stage.setTitle("Gestión de Socios");
        stage.show();
    }

    public void showVistaAddSocio() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VistaAddSocio.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        VistaAddSocioController vAddSocioController = loader.getController();
        vAddSocioController.setControlSocios(this);
        stage.setTitle("Añadir Socio");
        stage.show();
    }

    public void showVistaListarSocios() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VistaListarSocios.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        VistaListarSociosController vListarSociosController = loader.getController();
        vListarSociosController.initialize(this, stage);
        stage.setTitle("Listar Socios");
        stage.show();
    }

    public void showVistaModificarSeguro() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VistaModificarSeguro.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        VistaModificarSeguroController vModificarSeguroController = loader.getController();
        vModificarSeguroController.setControlSocios(this);
        stage.setTitle("Modificar Seguro");
        stage.show();
    }

    public void addSocio(Socio socio) {
        if (socio instanceof Infantil) {
            infantilDAO.insertarInfantil((Infantil) socio);
        } else if (socio instanceof Federado) {
            federadoDAO.insertarFederado((Federado) socio);
        } else if (socio instanceof Estandar) {
            estandarDAO.insertarEstandar((Estandar) socio);
        } else {
            socioDAO.insertarSocio(socio);
        }
    }

    public List<? extends Socio> listTipoSocios(int tipo) {
        switch (tipo) {
            case 1:
                return estandarDAO.listarEstandares();
            case 2:
                return federadoDAO.listarFederados();
            case 3:
                return infantilDAO.listarInfantiles();
            default:
                throw new IllegalArgumentException("Tipo de socio no válido");
        }
    }

    public List<Socio> listSocios() {
        return socioDAO.listarSocios();
    }

    public void removeSocio(Socio socio) throws SQLException {
        if (socio != null) {
            socioDAO.eliminarSocio(socio);
            System.out.println("Socio eliminado correctamente.");
        } else {
            System.out.println("Socio no encontrado.");
        }
    }

    public void modificarSocio(Socio socio) {
        if (socio instanceof Infantil) {
            infantilDAO.modificarInfantil((Infantil) socio);
        } else if (socio instanceof Federado) {
            federadoDAO.modificarFederado((Federado) socio);
        } else if (socio instanceof Estandar) {
            estandarDAO.modificarEstandar((Estandar) socio);
        } else {
            socioDAO.modificarSocio(socio);
        }
    }

    public Seguro buscarSeguro(int id) {
        return seguroDAO.buscarSeguro(id);
    }

    public List<Seguro> listarSeguros() {
        return seguroDAO.listarSeguros();
    }

    public String calcularFacturaMensualSocios() {
        LocalDate fechaFinal = LocalDate.now();
        LocalDate fechaInicial = fechaFinal.minusMonths(1);
        List<Inscripcion> listInscripciones = inscripcionDAO.getInscripcionesPorFecha(fechaInicial, fechaFinal);
        float totalFactura = 0;

        for (Inscripcion inscripcion : listInscripciones) {
            Excursion codigoExcursion = inscripcion.getExcursion();
            Excursion excursion = excursionDAO.getExcursion(codigoExcursion.getCodigoExcursion());
            totalFactura += excursion.getPrecio();
        }

        return "Total factura mensual de los socios: " + totalFactura + " euros.";
    }

    public String calcularFacturaFechas(LocalDate fechaInicial, LocalDate fechaFinal) {
        List<Inscripcion> listInscripciones = inscripcionDAO.getInscripcionesPorFecha(fechaInicial, fechaFinal);
        float totalFactura = 0;

        for (Inscripcion inscripcion : listInscripciones) {
            Excursion codigoExcursion = inscripcion.getExcursion();
            Excursion excursion = excursionDAO.getExcursionPorCodigo(codigoExcursion.getCodigoExcursion());
            if (excursion != null) {
                totalFactura += excursion.getPrecio();
            } else {
                System.out.println("No se encontró la excursión con código: " + codigoExcursion);
            }
        }

        return "Total factura entre fechas de los socios: " + totalFactura + " euros.";
    }

    public String calcularFacturasFechasSocio(String numeroSocio, LocalDate fechaInicial, LocalDate fechaFinal) {
        List<Inscripcion> listInscripciones = inscripcionDAO.getInscripcionesPorFecha(fechaInicial, fechaFinal);
        float totalFactura = 0;

        for (Inscripcion inscripcion : listInscripciones) {
            Socio codigoSocio = inscripcion.getSocio();
            if (codigoSocio.getCodigoSocio().equals(numeroSocio)) {
                Excursion excursion = excursionDAO.getExcursion(codigoSocio.getCodigoSocio());
                totalFactura += excursion.getPrecio();
            }
        }

        return "Total factura entre fechas para el socio " + numeroSocio + ": " + totalFactura + " euros.";
    }

    // Getters
    public ControlPeticiones getControlPeticiones() {
        return cPeticiones;
    }

    public ControlDatos getControlDatos() {
        return cDatos;
    }

    public APPSenderosMontanas getApp() {
        return app;
    }

    public Socio getSocio(String idSocio) {
        return socioDAO.getSocio(idSocio);
    }
}
