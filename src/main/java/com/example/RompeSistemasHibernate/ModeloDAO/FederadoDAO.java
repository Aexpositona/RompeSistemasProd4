package com.example.RompeSistemasHibernate.ModeloDAO;

import com.example.RompeSistemasHibernate.Modelo.Federado;
import java.util.List;

public interface FederadoDAO {
    List<Federado> listarFederados();
    Federado getFederado(String codigo);
    void modificarFederado(Federado federado);
    void eliminarFederado(Federado federado);
    void insertarFederado(Federado federado);
}

