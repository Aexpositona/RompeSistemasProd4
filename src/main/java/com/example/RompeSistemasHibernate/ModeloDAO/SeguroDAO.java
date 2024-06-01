package com.example.RompeSistemasHibernate.ModeloDAO;

import com.example.RompeSistemasHibernate.Modelo.Seguro;
import java.util.List;

public interface SeguroDAO {
        Seguro buscarSeguro(int id);
        List<Seguro> listarSeguros();
        Seguro getSeguro(int id);
}


