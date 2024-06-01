package com.example.RompeSistemasHibernate.Modelo;

import com.example.RompeSistemasHibernate.Datos.SQLFabricaDAO;
import com.example.RompeSistemasHibernate.ModeloDAO.FabricaDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Datos {
    private EntityManagerFactory emf;
    private EntityManager em;
    private FabricaDAO fabricaDAO;

    public Datos() {
        this.emf = Persistence.createEntityManagerFactory("AppSenderosMontanasPU");
        this.em = emf.createEntityManager();
        this.fabricaDAO = new SQLFabricaDAO(em);
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public FabricaDAO getFabricaDAO() {
        return fabricaDAO;
    }

    // Métodos para obtener objetos específicos
    public Seguro getSeguro(int id) throws SQLException {
        return fabricaDAO.getSeguroDAO().getSeguro(id);
    }

    public Socio getSocio(String codigo) throws SQLException {
        return fabricaDAO.getSocioDAO().getSocio(codigo);
    }

    public Federacion getFederacion(String codigo) throws SQLException {
        return fabricaDAO.getFederacionDAO().getFederacion(codigo);
    }

    // Método para agregar un objeto
    public void addObjeto(int tipoObjeto, Object objeto) throws SQLException {
        em.getTransaction().begin();
        try {
            switch (tipoObjeto) {
                case 1 -> fabricaDAO.getExcursionDAO().addExcursion((Excursion) objeto);
                case 2 -> fabricaDAO.getInscripcionDAO().insertarInscripcion((Inscripcion) objeto);
                case 3 -> {
                    if (objeto instanceof Infantil) {
                        fabricaDAO.getInfantilDAO().insertarInfantil((Infantil) objeto);
                    } else if (objeto instanceof Federado) {
                        fabricaDAO.getFederadoDAO().insertarFederado((Federado) objeto);
                    } else if (objeto instanceof Estandar) {
                        fabricaDAO.getEstandarDAO().insertarEstandar((Estandar) objeto);
                    } else {
                        fabricaDAO.getSocioDAO().insertarSocio((Socio) objeto);
                    }
                }
                case 4 -> fabricaDAO.getFederacionDAO().insertarFederacion((Federacion) objeto);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    // Método para eliminar un objeto
    public void removeObjeto(int tipoObjeto, Object objeto) throws SQLException {
        em.getTransaction().begin();
        try {
            switch (tipoObjeto) {
                case 1 -> fabricaDAO.getExcursionDAO().deleteExcursion((Excursion) objeto);
                case 2 -> fabricaDAO.getInscripcionDAO().eliminarInscripcion((Inscripcion) objeto);
                case 3 -> {
                    if (objeto instanceof Infantil) {
                        fabricaDAO.getInfantilDAO().eliminarInfantil((Infantil) objeto);
                    } else if (objeto instanceof Federado) {
                        fabricaDAO.getFederadoDAO().eliminarFederado((Federado) objeto);
                    } else if (objeto instanceof Estandar) {
                        fabricaDAO.getEstandarDAO().eliminarEstandar((Estandar) objeto);
                    } else {
                        fabricaDAO.getSocioDAO().eliminarSocio((Socio) objeto);
                    }
                }
                case 4 -> fabricaDAO.getFederacionDAO().eliminarFederacion((Federacion) objeto);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    // Método para modificar un objeto
    public void modifyObjeto(int tipoObjeto, Object objeto) throws SQLException {
        em.getTransaction().begin();
        try {
            switch (tipoObjeto) {
                case 1 -> fabricaDAO.getExcursionDAO().updateExcursion((Excursion) objeto);
                case 2 -> fabricaDAO.getInscripcionDAO().modificarInscripcion((Inscripcion) objeto);
                case 3 -> {
                    if (objeto instanceof Infantil) {
                        fabricaDAO.getInfantilDAO().modificarInfantil((Infantil) objeto);
                    } else if (objeto instanceof Federado) {
                        fabricaDAO.getFederadoDAO().modificarFederado((Federado) objeto);
                    } else if (objeto instanceof Estandar) {
                        fabricaDAO.getEstandarDAO().modificarEstandar((Estandar) objeto);
                    } else {
                        fabricaDAO.getSocioDAO().modificarSocio((Socio) objeto);
                    }
                }
                case 4 -> fabricaDAO.getFederacionDAO().modificarFederacion((Federacion) objeto);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    // Método para obtener el siguiente código
    public String getSiguienteCodigo(int tipoObjeto) {
        String query = switch (tipoObjeto) {
            case 1 -> "SELECT e FROM Excursion e ORDER BY e.codigo DESC";
            case 2 -> "SELECT i FROM Inscripcion i ORDER BY i.numero DESC";
            case 3 -> "SELECT s FROM Socio s ORDER BY s.id DESC";
            case 4 -> "SELECT f FROM Federacion f ORDER BY f.codigo DESC";
            default -> throw new IllegalArgumentException("Tipo de objeto no válido");
        };

        return generarSiguienteCodigo(query, tipoObjeto);
    }

    private String generarSiguienteCodigo(String query, int tipoObjeto) {
        try {
            List<?> resultList = em.createQuery(query).setMaxResults(1).getResultList();
            if (!resultList.isEmpty()) {
                String ultimoCodigo = switch (tipoObjeto) {
                    case 1 -> ((Excursion) resultList.get(0)).getCodigo();
                    case 2 -> ((Inscripcion) resultList.get(0)).getNumero();
                    case 3 -> String.valueOf(((Socio) resultList.get(0)).getCodigoSocio());
                    case 4 -> ((Federacion) resultList.get(0)).getCodigo();
                    default -> throw new IllegalArgumentException("Tipo de objeto no válido");
                };
                int numero = Integer.parseInt(ultimoCodigo.substring(3));
                numero++;
                String relleno = numero < 10 ? "000" : numero < 100 ? "00" : numero < 1000 ? "0" : "";
                return ultimoCodigo.substring(0, 3) + relleno + numero;
            }
        } catch (Exception e) {
            System.out.println("Error al obtener el siguiente código de la base de datos: " + e.getMessage());
        }
        return "";
    }

    // Método para listar objetos en formato String
    public String listToStringObjetos(int tipoObjeto) {
        String query = switch (tipoObjeto) {
            case 1 -> "SELECT e FROM Excursion e";
            case 2 -> "SELECT i FROM Inscripcion i";
            case 3 -> "SELECT s FROM Socio s";
            case 4 -> "SELECT f FROM Federacion f";
            default -> throw new IllegalArgumentException("Tipo de objeto no válido");
        };

        return obtenerResultados(query, tipoObjeto);
    }

    // Método para listar objetos por fechas en formato String
    public String listToStringObjetosFechas(int tipoObjeto, LocalDate fechaInicial, LocalDate fechaFinal) {
        String query = switch (tipoObjeto) {
            case 1 -> "SELECT e FROM Excursion e WHERE e.fecha BETWEEN :fechaInicial AND :fechaFinal";
            case 2 -> "SELECT i FROM Inscripcion i WHERE i.fechaInscripcion BETWEEN :fechaInicial AND :fechaFinal";
            default -> throw new IllegalArgumentException("Tipo de objeto no válido");
        };

        return obtenerResultadosFechas(query, tipoObjeto, fechaInicial, fechaFinal);
    }

    private String obtenerResultados(String query, int tipoObjeto) {
        StringBuilder result = new StringBuilder();
        try {
            List<?> resultList = em.createQuery(query).getResultList();
            for (Object obj : resultList) {
                switch (tipoObjeto) {
                    case 1 -> {
                        Excursion excursion = (Excursion) obj;
                        result.append("Excursion: ").append(excursion.getCodigo())
                                .append(", ").append(excursion.getDescripcion())
                                .append(", ").append(excursion.getFecha())
                                .append(", ").append(excursion.getDuracion())
                                .append(", ").append(excursion.getPrecio()).append("\n");
                    }
                    case 2 -> {
                        Inscripcion inscripcion = (Inscripcion) obj;
                        result.append("Inscripcion: ").append(inscripcion.getNumero())
                                .append(", ").append(inscripcion.getFecha())
                                .append(", ").append(inscripcion.getSocio().getCodigoSocio())
                                .append(", ").append(inscripcion.getExcursion().getCodigo()).append("\n");
                    }
                    case 3 -> {
                        Socio socio = (Socio) obj;
                        result.append("Socio: ").append(socio.getNombreSocio())
                                .append(", ").append(socio.getCodigoSocio())
                                .append(", ").append(socio.getNifSocio()).append("\n");
                    }
                    case 4 -> {
                        Federacion federacion = (Federacion) obj;
                        result.append("Federacion: ").append(federacion.getCodigo())
                                .append(", ").append(federacion.getNombre()).append("\n");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los datos de la base de datos: " + e.getMessage());
        }
        return result.toString();
    }

    private String obtenerResultadosFechas(String query, int tipoObjeto, LocalDate fechaInicial, LocalDate fechaFinal) {
        StringBuilder result = new StringBuilder();
        try {
            List<?> resultList = em.createQuery(query)
                    .setParameter("fechaInicial", fechaInicial)
                    .setParameter("fechaFinal", fechaFinal)
                    .getResultList();
            for (Object obj : resultList) {
                switch (tipoObjeto) {
                    case 1 -> {
                        Excursion excursion = (Excursion) obj;
                        result.append("Excursion: ").append(excursion.getCodigo())
                                .append(", ").append(excursion.getDescripcion())
                                .append(", ").append(excursion.getFecha())
                                .append(", ").append(excursion.getDuracion())
                                .append(", ").append(excursion.getPrecio()).append("\n");
                    }
                    case 2 -> {
                        Inscripcion inscripcion = (Inscripcion) obj;
                        result.append("Inscripcion: ").append(inscripcion.getNumero())
                                .append(", ").append(inscripcion.getFecha())
                                .append(", ").append(inscripcion.getSocio().getCodigoSocio())
                                .append(", ").append(inscripcion.getExcursion().getCodigo()).append("\n");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los datos de la base de datos: " + e.getMessage());
        }
        return result.toString();
    }
}
