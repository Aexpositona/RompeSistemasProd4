package com.example.RompeSistemasHibernate.Modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "excursion") // Nombre de la tabla en la base de datos
public class Excursion {

    @Id
    @Column(nullable = false, unique = true)
    private String codigoExcursion;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private int duracion;

    @Column(nullable = false)
    private float precio;

    // Métodos constructores

    /**
     * Constructor de la clase Excursion que recibe parámetros para inicializar los atributos.
     *
     * @param codigoExcursion      código de la excursión
     * @param descripcion descripción de la excursión
     * @param fecha       fecha de la excursión
     * @param duracion    duración de la excursión
     * @param precio      precio de la excursión
     */
    public Excursion(String codigoExcursion, String descripcion, LocalDate fecha, int duracion, float precio) {
        this.codigoExcursion = codigoExcursion;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.duracion = duracion;
        this.precio = precio;
    }

    /**
     * Constructor de la clase Excursion que recibe un objeto de la misma clase para copiar los atributos.
     *
     * @param excursion objeto de la clase Excursion
     */
    public Excursion(Excursion excursion) {
        this.codigoExcursion = excursion.codigoExcursion;
        this.descripcion = excursion.descripcion;
        this.fecha = excursion.fecha;
        this.duracion = excursion.duracion;
        this.precio = excursion.precio;
    }

    /**
     * Constructor vacío para generar sobrecarga de constructores.
     */
    public Excursion() {
        this.codigoExcursion = "";
        this.descripcion = "";
        this.fecha = LocalDate.now();
        this.duracion = 0;
        this.precio = 0;
    }

    // Métodos Getters

    /**
     * Obtiene el código de la excursión.
     *
     * @return el código de la excursión
     */
    public String getCodigoExcursion() {
        return codigoExcursion;
    }

    /**
     * Obtiene la descripción de la excursión.
     *
     * @return la descripción de la excursión
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene la fecha de la excursión.
     *
     * @return la fecha de la excursión
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Obtiene la duración de la excursión.
     *
     * @return la duración de la excursión
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Obtiene el precio de la excursión.
     *
     * @return el precio de la excursión
     */
    public float getPrecio() {
        return precio;
    }

    // Métodos Setters

    /**
     * Establece el código de la excursión.
     *
     * @param codigo el código de la excursión
     */
    public void setCodigoExcursion(String codigo) {
        this.codigoExcursion = codigo;
    }

    /**
     * Establece la descripción de la excursión.
     *
     * @param descripcion la descripción de la excursión
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Establece la fecha de la excursión.
     *
     * @param fecha la fecha de la excursión
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Establece la duración de la excursión.
     *
     * @param duracion la duración de la excursión
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * Establece el precio de la excursión.
     *
     * @param precio el precio de la excursión
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * Método toString que devuelve una representación en cadena de los datos de la excursión.
     *
     * @return una cadena con los datos de la excursión
     */
    @Override
    public String toString() {
        return "Código: " + codigoExcursion +
                "\nDescripción: " + descripcion +
                "\nFecha: " + fecha +
                "\nDuración: " + duracion +
                "\nPrecio: " + precio + " Euros.\n";
    }
}
