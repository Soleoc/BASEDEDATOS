package com.inscripcion.model;

import java.time.LocalDateTime;

/**
 * Clase que representa un curso en el sistema.
 */
public class Curso {
    private Integer idCurso;
    private String nombre;
    private String descripcion;
    private Integer creditos;
    private Integer cupoMaximo;
    private LocalDateTime fechaCreacion;

    /**
     * Constructor por defecto.
     */
    public Curso() {
    }

    /**
     * Constructor con parámetros principales.
     * 
     * @param nombre Nombre del curso
     * @param creditos Número de créditos del curso
     * @param cupoMaximo Cupo máximo de estudiantes
     */
    public Curso(String nombre, Integer creditos, Integer cupoMaximo) {
        this.nombre = nombre;
        this.creditos = creditos;
        this.cupoMaximo = cupoMaximo;
    }

    /**
     * Constructor completo.
     * 
     * @param idCurso ID del curso
     * @param nombre Nombre del curso
     * @param descripcion Descripción del curso
     * @param creditos Número de créditos del curso
     * @param cupoMaximo Cupo máximo de estudiantes
     * @param fechaCreacion Fecha de creación del curso
     */
    public Curso(Integer idCurso, String nombre, String descripcion, Integer creditos,
                Integer cupoMaximo, LocalDateTime fechaCreacion) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creditos = creditos;
        this.cupoMaximo = cupoMaximo;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y Setters
    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Integer getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(Integer cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return nombre + " ("+creditos+" créditos)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Curso curso = (Curso) o;

        return idCurso != null ? idCurso.equals(curso.idCurso) : curso.idCurso == null;
    }

    @Override
    public int hashCode() {
        return idCurso != null ? idCurso.hashCode() : 0;
    }
}