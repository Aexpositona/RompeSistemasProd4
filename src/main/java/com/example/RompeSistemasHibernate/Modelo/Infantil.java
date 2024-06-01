package com.example.RompeSistemasHibernate.Modelo;

import javax.persistence.Entity;
import javax.persistence.Column;

@Entity
public class Infantil extends Socio {

    @Column(nullable = false)
    private String numSocioTutor;

    private static final int tipo = 3;

    // Métodos constructores
    /**
     * Método constructor de la clase Infantil que recibe por parámetros el nombre, el número y el numero de socio del tutor
     * @param nombre es el nombre del socio
     * @param numero es el número del socio
     * @param numSocioTutor es el número de socio del tutor
     */
    public Infantil(String nombre, String numero, String nif, String numSocioTutor){
        super(nombre, numero, nif);
        this.numSocioTutor = numSocioTutor;
        super.setTipo(tipo);
    }

    /**
     * Método constructor de copia de la clase Infantil
     * @param infantil Es el socio que se va a copiar
     */
    public Infantil(Infantil infantil){
        super(infantil);
        this.numSocioTutor = infantil.numSocioTutor;
        super.setTipo(tipo);
    }

    /**
     * Constructor vacío
     */
    public Infantil(){
        super();
        this.numSocioTutor = "";
        super.setTipo(tipo);
    }

    // Métodos Getters
    /**
     * Método que nos devuelve el número de socio del tutor
     * @return El número de socio del tutor
     */
    public String getNumSocioTutor() {
        return numSocioTutor;
    }

    public int getTipo() {
        return tipo;
    }

    // Métodos Setters
    /**
     * Método que nos permite modificar el número de socio del tutor
     * @param numSocioTutor es el nuevo número de socio del tutor
     */
    public void setNumSocioTutor(String numSocioTutor) {
        this.numSocioTutor = numSocioTutor;
    }

    public void setTipo(int tipo) {
        super.setTipo(tipo);
    }

    /**
     * Método toString() de la clase Infantil que nos devuelve un String con los datos del socio
     * @return El nombre, el número del socio, el tipo de socio y el número de socio del tutor
     */
    @Override
    public String toString(){
        super.setTipo(tipo);
        return super.toString() +
                "Numero de socio del tutor: " + numSocioTutor + "\n";
    }
}