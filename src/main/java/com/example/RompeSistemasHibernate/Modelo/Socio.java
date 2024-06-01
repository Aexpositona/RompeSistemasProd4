package com.example.RompeSistemasHibernate.Modelo;

import javax.persistence.*;

/**
 * Clase Socio que define los atributos y métodos de un socio
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE", discriminatorType = DiscriminatorType.STRING)
@Table(name = "socio")
public class Socio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSocio;

    @Column(nullable = false)
    private int tipo;

    @Column(nullable = false)
    private String nombreSocio;

    @Column(nullable = false, unique = true)
    private String codigoSocio;

    @Column(nullable = false, unique = true)
    private String nifSocio;

    // Constructores

    /**
     * Constructor de la clase Socio
     * @param nombreSocio Es el nombre del socio
     * @param codigoSocio Es el número del socio
     * @param nifSocio Es el NIF del socio
     */
    public Socio(String nombreSocio, String codigoSocio, String nifSocio) {
        this.tipo = 0;
        this.nombreSocio = nombreSocio;
        this.codigoSocio = codigoSocio;
        this.nifSocio = nifSocio;
    }

    /**
     * Constructor de copia de la clase Socio
     * @param socio Es el socio que se va a copiar
     */
    public Socio(Socio socio) {
        this.tipo = socio.tipo;
        this.nombreSocio = socio.nombreSocio;
        this.codigoSocio = socio.codigoSocio;
        this.nifSocio = socio.nifSocio;
    }

    /**
     * Constructor vacío para generar sobrecarga de constructores
     */
    public Socio() {
        this.tipo = 0;
        this.nombreSocio = "";
        this.codigoSocio = "";
        this.nifSocio = "";
    }

    // Métodos Getters
    /**
     * Método get() de la clase Socio que nos devuelve el nombre del socio
     * @return El nombre del socio
     */
    public String getNombreSocio() {
        return nombreSocio;
    }

    /**
     * Método get() de la clase Socio que nos devuelve el número del socio
     * @return El número del socio
     */
    public String getCodigoSocio() {
        return codigoSocio;
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
    public String getNifSocio() {
        return nifSocio;
    }

    // Métodos Setters
    /**
     * Método set() de la clase Socio que nos permite definir el nombre del socio
     * @param nombre Es el nombre del socio
     */
    public void setNombreSocio(String nombre) {
        this.nombreSocio = nombre;
    }

    /**
     * Método set() de la clase Socio que nos permite definir el número del socio
     * @param numero Es el número del socio
     */
    public void setCodigoSocio(String numero) {
        this.codigoSocio = numero;
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
    public void setNifSocio(String nif) {
        this.nifSocio = nif;
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
        return "Nombre: " + nombreSocio +
                "\nNumero de socio: " + codigoSocio +
                "\nNIF: " + nifSocio +
                "\nTipo: " + tipoSocio + "\n";
    }
}
