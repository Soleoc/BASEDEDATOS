package com.inscripcion.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Clase que representa a un estudiante en el sistema.
 */
public class Estudiante {
    private Integer idEstudiante;
    private String nombre;
    private String correo;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String direccion;
    private LocalDateTime fechaRegistro;

    /**
     * Constructor por defecto.
     */
    public Estudiante() {
    }

    /**
     * Constructor con parámetros principales.
     * 
     * @param nombre Nombre completo del estudiante
     * @param correo Correo electrónico del estudiante
     */
    public Estudiante(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    /**
     * Constructor completo.
     * 
     * @param idEstudiante ID del estudiante
     * @param nombre Nombre completo del estudiante
     * @param correo Correo electrónico del estudiante
     * @param telefono Número de teléfono del estudiante
     * @param fechaNacimiento Fecha de nacimiento del estudiante
     * @param direccion Dirección del estudiante
     * @param fechaRegistro Fecha de registro en el sistema
     */
    public Estudiante(Integer idEstudiante, String nombre, String correo, String telefono,
                     LocalDate fechaNacimiento, String direccion, LocalDateTime fechaRegistro) {
        this.idEstudiante = idEstudiante;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters
    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Estudiante that = (Estudiante) o;

        return idEstudiante != null ? idEstudiante.equals(that.idEstudiante) : that.idEstudiante == null;
    }

    @Override
    public int hashCode() {
        return idEstudiante != null ? idEstudiante.hashCode() : 0;
    }
}