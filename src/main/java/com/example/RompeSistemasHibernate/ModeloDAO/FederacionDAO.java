package com.example.RompeSistemasHibernate.ModeloDAO;

import com.example.RompeSistemasHibernate.Modelo.Federacion;
import java.util.List;

public interface FederacionDAO {
    void insertarFederacion(Federacion federacion);
    void modificarFederacion(Federacion federacion);
    void eliminarFederacion(Federacion federacion);
    Federacion buscarFederacion(int id);
    Federacion buscarFederacion(String nombre);
    Federacion getFederacion(String codigoFederacion);
    List<Federacion> listarFederaciones();
}

