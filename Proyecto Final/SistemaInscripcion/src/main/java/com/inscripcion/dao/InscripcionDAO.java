package com.inscripcion.dao;

import com.inscripcion.model.Curso;
import com.inscripcion.model.Estudiante;
import com.inscripcion.model.Inscripcion;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz que define las operaciones para la gestión de inscripciones.
 */
public interface InscripcionDAO {
    
    /**
     * Inscribe a un estudiante en un curso.
     * 
     * @param idEstudiante ID del estudiante
     * @param idCurso ID del curso
     * @return true si la inscripción fue exitosa, false en caso contrario
     * @throws SQLException Si ocurre un error en la base de datos
     */
    boolean inscribir(int idEstudiante, int idCurso) throws SQLException;
    
    /**
     * Elimina la inscripción de un estudiante en un curso.
     * 
     * @param idEstudiante ID del estudiante
     * @param idCurso ID del curso
     * @return true si la eliminación fue exitosa, false en caso contrario
     * @throws SQLException Si ocurre un error en la base de datos
     */
    boolean eliminarInscripcion(int idEstudiante, int idCurso) throws SQLException;
    
    /**
     * Verifica si un estudiante ya está inscrito en un curso.
     * 
     * @param idEstudiante ID del estudiante
     * @param idCurso ID del curso
     * @return true si el estudiante ya está inscrito, false en caso contrario
     * @throws SQLException Si ocurre un error en la base de datos
     */
    boolean estaInscrito(int idEstudiante, int idCurso) throws SQLException;
    
    /**
     * Obtiene todos los cursos en los que está inscrito un estudiante.
     * 
     * @param idEstudiante ID del estudiante
     * @return Lista de cursos en los que está inscrito el estudiante
     * @throws SQLException Si ocurre un error en la base de datos
     */
    List<Curso> obtenerCursosDeEstudiante(int idEstudiante) throws SQLException;
    
    /**
     * Obtiene todos los estudiantes inscritos en un curso.
     * 
     * @param idCurso ID del curso
     * @return Lista de estudiantes inscritos en el curso
     * @throws SQLException Si ocurre un error en la base de datos
     */
    List<Estudiante> obtenerEstudiantesDeCurso(int idCurso) throws SQLException;
    
    /**
     * Obtiene todas las inscripciones registradas.
     * 
     * @return Lista de todas las inscripciones
     * @throws SQLException Si ocurre un error en la base de datos
     */
    List<Inscripcion> obtenerTodasLasInscripciones() throws SQLException;
}