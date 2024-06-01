package com.example.RompeSistemasHibernate.Modelo;

public enum Seguro {

    BASICO(1, "Básico", 100.0f),
    COMPLETO(2, "Completo", 200.0f);

    private int id;
    private float precio;
    private String nombre;

    Seguro(int id, String nombre, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public float getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public static Seguro getSeguro(int id) {
        for (Seguro seguro : Seguro.values()) {
            if (seguro.id == id) {
                return seguro;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " - " + precio + " Euros";
    }
}

