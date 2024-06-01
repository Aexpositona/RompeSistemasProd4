package com.example.RompeSistemasHibernate.Modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Inscripcion {

    @Id
    @Column(nullable = false, unique = true)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "idSocio", nullable = false)
    private Socio socio;

    @ManyToOne
    @JoinColumn(name = "codigoExcursion", nullable = false)
    private Excursion excursion;

    @Column(nullable = false)
    private LocalDate fechaInscripcion;

    // Métodos constructores

    /**
     ** Método constructor de la clase Inscripcion que recibe por parámetros el número de inscripción, el socio y la excursión.
     * @param numero es el número que identifica la inscripción
     * @param socio es el socio que se inscribe
     * @param excursion es la excursión a la que se inscribe
     */
    public Inscripcion(String numero, Socio socio, Excursion excursion) {
        if (excursion == null) {
            throw new IllegalArgumentException("La excursión no puede ser nula");
        }

        this.numero = numero;
        this.socio = socio;
        this.excursion = excursion;
        this.fechaInscripcion = LocalDate.now();
    }

    public Inscripcion(String codigoInscripcion, LocalDate fechaInscripcion, String idSocio, String idExcursion) {
        this.numero = codigoInscripcion;
        this.fechaInscripcion = fechaInscripcion;
        this.socio = new Socio(); // Se debería resolver la relación correctamente
        this.excursion = new Excursion(); // Se debería resolver la relación correctamente
    }

    /**
     * Método constructor de la clase Inscripcion que recibe un objeto de la misma clase para copiar los atributos.
     * @param inscripcion objeto de la clase Inscripcion
     */
    public Inscripcion(Inscripcion inscripcion) {
        this.numero = inscripcion.numero;
        this.socio = inscripcion.socio;
        this.excursion = inscripcion.excursion;
        this.fechaInscripcion = inscripcion.fechaInscripcion;
    }

    /**
     * Constructor vacío para generar sobrecarga de constructores.
     */
    public Inscripcion() {
        this.numero = "";
        this.socio = new Socio();
        this.excursion = new Excursion();
        this.fechaInscripcion = LocalDate.now();
    }

    // Métodos Getters

    /**
     * Obtiene el número de la inscripción.
     *
     * @return el número de la inscripción
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Establece el número de la inscripción.
     *
     * @param numero el número de la inscripción
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Obtiene el socio que se inscribe.
     *
     * @return el socio que se inscribe
     */
    public Socio getSocio() {
        return socio;
    }

    /**
     * Establece el socio que se inscribe.
     *
     * @param socio el socio que se inscribe
     */
    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    /**
     * Obtiene la excursión a la que se inscribe.
     *
     * @return la excursión a la que se inscribe
     */
    public Excursion getExcursion() {
        return excursion;
    }

    /**
     * Establece la excursión a la que se inscribe.
     *
     * @param excursion la excursión a la que se inscribe
     */
    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }

    /**
     * Obtiene la fecha de inscripción.
     *
     * @return la fecha de inscripción
     */
    public LocalDate getFecha() {
        return fechaInscripcion;
    }

    /**
     * Establece la fecha de inscripción.
     *
     * @param fechaInscripcion la fecha de inscripción
     */
    public void setFecha(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    /**
     * Método toString que devuelve una representación en cadena de los datos de la inscripción.
     *
     * @return una cadena con los datos de la inscripción
     */
    @Override
    public String toString() {
        return "Número de la inscripción: " + numero +
                "\nSocio: " + socio +
                "\n-- Excursion --\n" + excursion +
                "\nFecha de inscripción: " + fechaInscripcion + "\n";
    }
}
