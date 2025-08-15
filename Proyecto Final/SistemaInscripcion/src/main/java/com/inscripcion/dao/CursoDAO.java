package com.inscripcion.dao;

import com.inscripcion.model.Curso;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad Curso.
 */
public interface CursoDAO {
    
    /**
     * Inserta un nuevo curso en la base de datos.
     * 
     * @param curso Curso a insertar
     * @return El ID generado para el curso
     * @throws SQLException Si ocurre un error en la base de datos
     */
    int insertar(Curso curso) throws SQLException;
    
    /**
     * Actualiza los datos de un curso existente.
     * 
     * @param curso Curso con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     * @throws SQLException Si ocurre un error en la base de datos
     */
    boolean actualizar(Curso curso) throws SQLException;
    
    /**
     * Elimina un curso de la base de datos.
     * 
     * @param idCurso ID del curso a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     * @throws SQLException Si ocurre un error en la base de datos
     */
    boolean eliminar(int idCurso) throws SQLException;
    
    /**
     * Obtiene un curso por su ID.
     * 
     * @param idCurso ID del curso a buscar
     * @return Curso encontrado o null si no existe
     * @throws SQLException Si ocurre un error en la base de datos
     */
    Curso obtenerPorId(int idCurso) throws SQLException;
    
    /**
     * Obtiene todos los cursos de la base de datos.
     * 
     * @return Lista de todos los cursos
     * @throws SQLException Si ocurre un error en la base de datos
     */
    List<Curso> obtenerTodos() throws SQLException;
    
    /**
     * Busca cursos por nombre o número de créditos.
     * 
     * @param termino Término de búsqueda (nombre o créditos)
     * @return Lista de cursos que coinciden con el término de búsqueda
     * @throws SQLException Si ocurre un error en la base de datos
     */
    List<Curso> buscarPorNombreOCreditos(String termino) throws SQLException;
    
    /**
     * Obtiene el número actual de estudiantes inscritos en un curso.
     * 
     * @param idCurso ID del curso
     * @return Número de estudiantes inscritos
     * @throws SQLException Si ocurre un error en la base de datos
     */
    int obtenerNumeroEstudiantesInscritos(int idCurso) throws SQLException;
}