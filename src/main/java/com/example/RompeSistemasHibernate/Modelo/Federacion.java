package com.example.RompeSistemasHibernate.Modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class Federacion {

    @Id
    @Column(nullable = false, unique = true)
    private String codigoFederacion;

    @Column(nullable = false)
    private String nombre;

    // Constructor
    public Federacion(String codigo, String nombre) {
        this.codigoFederacion = codigo;
        this.nombre = nombre;
    }

    // Constructor vacío (requerido por JPA)
    public Federacion() {}

    // Getters
    public String getCodigo() {
        return codigoFederacion;
    }

    public String getNombre() {
        return nombre;
    }

    // Setters
    public void setCodigo(String codigo) {
        this.codigoFederacion = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nCódigo: " + codigoFederacion + "\n";
    }
}

