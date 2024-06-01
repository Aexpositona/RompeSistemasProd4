package com.example.RompeSistemasHibernate.ModeloDAO;

import com.example.RompeSistemasHibernate.Modelo.Estandar;

import java.util.List;


public interface EstandarDAO {
    List<Estandar> listarEstandares();
    Estandar getEstandar(String codigo);
    void modificarEstandar(Estandar estandar);
    void eliminarEstandar(Estandar estandar);
    void insertarEstandar(Estandar estandar);
}

