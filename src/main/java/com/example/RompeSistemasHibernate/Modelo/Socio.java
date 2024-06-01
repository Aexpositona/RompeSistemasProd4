package com.example.RompeSistemasHibernate.Modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

/**
 * Clase Socio que define los atributos y métodos de un socio
 */
@Entity
public class Socio {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSocio;

    @Column(nullable = false)
    private int tipo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String numero;

    @Column(nullable = false, unique = true)
    private String nif;

    // Constructores

    /**
     * Constructor de la clase Socio
     * @param nombre Es el nombre del socio
     * @param numero Es el número del socio
     * @param nif Es el NIF del socio
     */
    public Socio(String nombre, String numero, String nif) {
        this.tipo = 0;
        this.nombre = nombre;
        this.numero = numero;
        this.nif = nif;
    }

    /**
     * Constructor de copia de la clase Socio
     * @param socio Es el socio que se va a copiar
     */
    public Socio(Socio socio) {
        this.tipo = socio.tipo;
        this.nombre = socio.nombre;
        this.numero = socio.numero;
        this.nif = socio.nif;
    }

    /**
     * Constructor vacío para generar sobrecarga de constructores
     */
    public Socio() {
        this.tipo = 0;
        this.nombre = "";
        this.numero = "";
        this.nif = "";
    }

    // Métodos Getters
    /**
     * Método get() de la clase Socio que nos devuelve el nombre del socio
     * @return El nombre del socio
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método get() de la clase Socio que nos devuelve el número del socio
     * @return El número del socio
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Método get() de la clase Socio que nos devuelve el tipo del socio
     * @return El tipo del socio
     *        1 - Socio Estándar
     *        2 - Socio Federado
     *        3 - Socio Infantil
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Método get() de la clase Socio que nos devuelve el NIF del socio
     * @return El NIF del socio
     */
    public String getNif() {
        return nif;
    }

    // Métodos Setters
    /**
     * Método set() de la clase Socio que nos permite definir el nombre del socio
     * @param nombre Es el nombre del socio
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método set() de la clase Socio que nos permite definir el número del socio
     * @param numero Es el número del socio
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Método set() de la clase Socio que nos permite definir el tipo del socio
     * @param tipo Es el tipo del socio
     *             1 - Socio Estándar
     *             2 - Socio Federado
     *             3 - Socio Infantil
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * Método set() de la clase Socio que nos permite definir el NIF del socio
     * @param nif Es el NIF del socio
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Método toString() de la clase Socio que nos devuelve un String con los datos del socio
     * @return Un String con los datos del socio, como el nombre, el domicilio, el NIF y el email
     */
    @Override
    public String toString() {
        String tipoSocio = "";
        if (tipo == 1) {
            tipoSocio = "Estándar";
        } else if (tipo == 2) {
            tipoSocio = "Federado";
        } else if (tipo == 3) {
            tipoSocio = "Infantil";
        }
        return "Nombre: " + nombre +
                "\nNumero de socio: " + numero +
                "\nNIF: " + nif +
                "\nTipo: " + tipoSocio + "\n";
    }
}
