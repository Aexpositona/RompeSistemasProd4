package com.example.RompeSistemasHibernate.Modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Estandar extends Socio {

    @Enumerated(EnumType.STRING)
    private Seguro seguro; // Enum Seguro

    private static final int tipo = 1;

    // Constructor
    public Estandar(String nombre, String numero, String nif, Seguro seguro) {
        super(nombre, numero, nif);
        this.seguro = seguro;
        super.setTipo(tipo);
    }

    // Constructor de copia
    public Estandar(Estandar estandar) {
        super(estandar);
        this.seguro = estandar.seguro;
        super.setTipo(tipo);
    }

    // Constructor vacío
    public Estandar() {
        super();
        this.seguro = null;
        super.setTipo(tipo);
    }

    // Getter
    public Seguro getSeguro() {
        return seguro;
    }

    // Setter
    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    // Método toString
    @Override
    public String toString() {
        super.setTipo(tipo);
        return super.toString() +
                "Seguro: " + seguro + "\n";
    }
}
