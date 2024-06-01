package com.example.RompeSistemasHibernate.ModeloDAO;

import javax.persistence.PersistenceException;

public interface FabricaDAO {
    ExcursionDAO getExcursionDAO() throws PersistenceException;
    InscripcionDAO getInscripcionDAO() throws PersistenceException;
    SocioDAO getSocioDAO() throws PersistenceException;
    FederacionDAO getFederacionDAO() throws PersistenceException;
    SeguroDAO getSeguroDAO() throws PersistenceException;
    InfantilDAO getInfantilDAO() throws PersistenceException;
    FederadoDAO getFederadoDAO() throws PersistenceException;
    EstandarDAO getEstandarDAO() throws PersistenceException;
}
