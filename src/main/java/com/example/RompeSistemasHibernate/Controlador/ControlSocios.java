package com.example.RompeSistemasHibernate.Controlador;

import com.example.RompeSistemasHibernate.Vista.*;
import com.example.RompeSistemasHibernate.Modelo.*;
import com.example.RompeSistemasHibernate.ModeloDAO.*;
import com.example.RompeSistemasHibernate.Datos.*;


import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

public class ControlSocios {
    private APPSenderosMontanas app;
    private VistaSocios vSocios;
    private VistaModificarSeguro vModificarSeguro;
    private VistaListarSocios vListarSocios;
    private VistaAddSocio vAddSocio;
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
        this.cPeticiones = new ControlPeticiones();
        this.cDatos = cDatos;
        this.em = em;
        this.vSocios = new VistaSocios(this);
        this.vModificarSeguro = new VistaModificarSeguro(this);
        this.vListarSocios = new VistaListarSocios(this);
        this.vAddSocio = new VistaAddSocio(this);
        this.socioDAO = new SQLSocioDAO(em);
        this.infantilDAO = new SQLInfantilDAO(em);
        this.federadoDAO = new SQLFederadoDAO(em);
        this.estandarDAO = new SQLEstandarDAO(em);
        this.seguroDAO = new SQLSeguroDAO(em);
        this.excursionDAO = new SQLExcursionDAO(em);
        this.inscripcionDAO = new SQLInscripcionDAO(em);
    }

    public void show() throws ParseException, SQLException {
        vSocios.show();
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

    public void listTipoSocios(int tipo) {
        switch (tipo) {
            case 1:
                List<Estandar> listEstandares = estandarDAO.listarEstandares();
                for (Estandar estandar : listEstandares) {
                    System.out.println("Nombre: " + estandar.getNombreSocio() + ", Código: " + estandar.getCodigoSocio() + ", NIF: " + estandar.getNifSocio());
                }
                break;
            case 2:
                List<Federado> listFederados = federadoDAO.listarFederados();
                for (Federado federado : listFederados) {
                    System.out.println("Nombre: " + federado.getNombreSocio() + ", Código: " + federado.getCodigoSocio() + ", NIF: " + federado.getNifSocio());
                }
                break;
            case 3:
                List<Infantil> listInfantiles = infantilDAO.listarInfantiles();
                for (Infantil infantil : listInfantiles) {
                    System.out.println("Nombre: " + infantil.getNombreSocio() + ", Código: " + infantil.getCodigoSocio() + ", NIF: " + infantil.getNifSocio());
                }
                break;
            default:
                throw new IllegalArgumentException("Tipo de socio no válido");
        }
    }

    public List<Socio> listSocios() {
        List<Socio> listSocios = socioDAO.listarSocios();
        for (Socio socio : listSocios) {
            System.out.println("Nombre: " + socio.getNombreSocio() + ", Código: " + socio.getCodigoSocio() + ", NIF: " + socio.getNifSocio());
        }
        return listSocios;
    }

    public void removeSocio(Socio socio) {
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

    public void showVistaAddSocio() throws ParseException, SQLException {
        vAddSocio.show();
    }

    public void showVistaListarSocios() throws ParseException, SQLException {
        vListarSocios.show();
    }

    public void showVistaModificarSeguro() throws ParseException, SQLException {
        vModificarSeguro.show();
    }

    // Métodos de cálculo de facturas

    public void calcularFacturaMensualSocios() {
        LocalDate fechaFinal = LocalDate.now();
        LocalDate fechaInicial = fechaFinal.minusMonths(1);
        List<Inscripcion> listInscripciones = inscripcionDAO.getInscripcionesPorFecha(fechaInicial, fechaFinal);
        float totalFactura = 0;

        for (Inscripcion inscripcion : listInscripciones) {
            Excursion codigoExcursion = inscripcion.getExcursion();
            Excursion excursion = excursionDAO.getExcursion(codigoExcursion.getCodigo());
            totalFactura += excursion.getPrecio();
        }

        System.out.println("Total factura mensual de los socios: " + totalFactura + " euros.");
    }

    public void calcularFacturaFechas(LocalDate fechaInicial, LocalDate fechaFinal) {
        List<Inscripcion> listInscripciones = inscripcionDAO.getInscripcionesPorFecha(fechaInicial, fechaFinal);
        float totalFactura = 0;

        for (Inscripcion inscripcion : listInscripciones) {
            Excursion codigoExcursion = inscripcion.getExcursion();
            System.out.println("Recuperado codigoExcursion: " + codigoExcursion);  // Añadir depuración
            Excursion excursion = excursionDAO.getExcursionPorCodigo(codigoExcursion.getCodigo());
            if (excursion != null) {
                totalFactura += excursion.getPrecio();
            } else {
                System.out.println("No se encontró la excursión con código: " + codigoExcursion);
            }
        }

        System.out.println("Total factura entre fechas de los socios: " + totalFactura + " euros.");
    }

    public void calcularFacturasFechasSocio(String numeroSocio, LocalDate fechaInicial, LocalDate fechaFinal) {
        List<Inscripcion> listInscripciones = inscripcionDAO.getInscripcionesPorFecha(fechaInicial, fechaFinal);
        float totalFactura = 0;

        for (Inscripcion inscripcion : listInscripciones) {
            Socio codigoSocio = inscripcion.getSocio();
            if (codigoSocio.getCodigoSocio().equals(numeroSocio)) {
                Excursion excursion = excursionDAO.getExcursion(codigoSocio.getCodigoSocio());
                totalFactura += excursion.getPrecio();
            }
        }

        System.out.println("Total factura entre fechas para el socio " + numeroSocio + ": " + totalFactura + " euros.");
    }

    // Getters

    public VistaSocios getVistaSocios() {
        return vSocios;
    }

    public VistaModificarSeguro getVistaModificarSeguro() {
        return vModificarSeguro;
    }

    public VistaListarSocios getVistaListarSocios() {
        return vListarSocios;
    }

    public VistaAddSocio getVistaAddSocio() {
        return vAddSocio;
    }

    public ControlPeticiones getControlPeticiones() {
        return cPeticiones;
    }

    public ControlDatos getControlDatos() {
        return cDatos;
    }

    public APPSenderosMontanas getApp() {
        return app;
    }

    public void setVistaSocios(VistaSocios vSocios) {
        this.vSocios = vSocios;
    }

    public void setVistaListarSocios(VistaListarSocios vListarSocios) {
        this.vListarSocios = vListarSocios;
    }

    public void setVistaAddSocio(VistaAddSocio vAddSocio) {
        this.vAddSocio = vAddSocio;
    }

    public void setVistaModificarSeguro(VistaModificarSeguro vModificarSeguro) {
        this.vModificarSeguro = vModificarSeguro;
    }

    public Socio getSocio(String idSocio) {
        return socioDAO.getSocio(idSocio);
    }
}
