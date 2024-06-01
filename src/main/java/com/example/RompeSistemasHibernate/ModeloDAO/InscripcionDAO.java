package com.example.RompeSistemasHibernate.ModeloDAO;

import com.example.RompeSistemasHibernate.Modelo.Inscripcion;
import java.time.LocalDate;
import java.util.List;

public interface InscripcionDAO {

        void insertarInscripcion(Inscripcion inscripcion);
        void modificarInscripcion(Inscripcion inscripcion);
        void eliminarInscripcion(Inscripcion inscripcion);
        Inscripcion buscarInscripcion(String id);
        List<Inscripcion> listarInscripciones();
        List<Inscripcion> getInscripcionesPorSocio(String idSocio);
        List<Inscripcion> getInscripcionesPorFecha(LocalDate fechaInicial, LocalDate fechaFinal);
        Inscripcion getInscripcion(String id);
        List<Inscripcion> getAllInscripciones();
}

