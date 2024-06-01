package com.example.RompeSistemasHibernate.ModeloDAO;

import com.example.RompeSistemasHibernate.Modelo.Socio;
import java.util.List;

public interface SocioDAO {
        List<Socio> listarSocios();
        Socio getSocio(String codigo);
        void modificarSocio(Socio socio);
        void eliminarSocio(Socio socio);
        void insertarSocio(Socio socio);
        Socio buscarSocio(int id);
        Socio buscarSocio(String codigo);
}


