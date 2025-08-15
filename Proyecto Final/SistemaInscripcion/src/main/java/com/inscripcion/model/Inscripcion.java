package com.inscripcion.model;

import java.time.LocalDateTime;

/**
 * Clase que representa la inscripción de un estudiante a un curso (relación muchos a muchos).
 */
public class Inscripcion {
    private Estudiante estudiante;
    private Curso curso;
    private LocalDateTime fechaInscripcion;

    /**
     * Constructor por defecto.
     */
    public Inscripcion() {
    }

    /**
     * Constructor con parámetros principales.
     * 
     * @param estudiante Estudiante que se inscribe
     * @param curso Curso al que se inscribe
     */
    public Inscripcion(Estudiante estudiante, Curso curso) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.fechaInscripcion = LocalDateTime.now();
    }

    /**
     * Constructor completo.
     * 
     * @param estudiante Estudiante que se inscribe
     * @param curso Curso al que se inscribe
     * @param fechaInscripcion Fecha y hora de la inscripción
     */
    public Inscripcion(Estudiante estudiante, Curso curso, LocalDateTime fechaInscripcion) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.fechaInscripcion = fechaInscripcion;
    }

    // Getters y Setters
    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDateTime fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Inscripcion that = (Inscripcion) o;

        if (estudiante != null ? !estudiante.equals(that.estudiante) : that.estudiante != null) return false;
        return curso != null ? curso.equals(that.curso) : that.curso == null;
    }

    @Override
    public int hashCode() {
        int result = estudiante != null ? estudiante.hashCode() : 0;
        result = 31 * result + (curso != null ? curso.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Inscripción: " + estudiante.getNombre() + " - " + curso.getNombre();
    }
}