package com.example.RompeSistemasHibernate.Datos;

import com.example.RompeSistemasHibernate.ModeloDAO.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

    public class SQLFabricaDAO implements FabricaDAO {
        private EntityManager em;
        private SocioDAO socioDAO;
        private InfantilDAO infantilDAO;
        private FederadoDAO federadoDAO;
        private EstandarDAO estandarDAO;
        private ExcursionDAO excursionDAO;
        private InscripcionDAO inscripcionDAO;
        private FederacionDAO federacionDAO;
        private SeguroDAO seguroDAO;

        public SQLFabricaDAO(EntityManager em) {
            this.em = em;
            this.socioDAO = new SQLSocioDAO(em);
            this.infantilDAO = new SQLInfantilDAO(em);
            this.federadoDAO = new SQLFederadoDAO(em);
            this.estandarDAO = new SQLEstandarDAO(em);
            this.excursionDAO = new SQLExcursionDAO(em);
            this.inscripcionDAO = new SQLInscripcionDAO(em);
            this.federacionDAO = new SQLFederacionDAO(em);
            this.seguroDAO = new SQLSeguroDAO(em);
        }

        @Override
        public SocioDAO getSocioDAO() {
            return socioDAO;
        }

        @Override
        public InfantilDAO getInfantilDAO() {
            return infantilDAO;
        }

        @Override
        public FederadoDAO getFederadoDAO() {
            return federadoDAO;
        }

        @Override
        public EstandarDAO getEstandarDAO() {
            return estandarDAO;
        }

        @Override
        public ExcursionDAO getExcursionDAO() {
            return excursionDAO;
        }

        @Override
        public InscripcionDAO getInscripcionDAO() {
            return inscripcionDAO;
        }

        @Override
        public FederacionDAO getFederacionDAO() {
            return federacionDAO;
        }

        @Override
        public SeguroDAO getSeguroDAO() {
            return seguroDAO;
        }
    }
