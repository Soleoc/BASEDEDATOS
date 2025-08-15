package com.inscripcion.dao;

import com.inscripcion.model.Estudiante;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad Estudiante.
 */
public interface EstudianteDAO {
    
    /**
     * Inserta un nuevo estudiante en la base de datos.
     * 
     * @param estudiante Estudiante a insertar
     * @return El ID generado para el estudiante
     * @throws SQLException Si ocurre un error en la base de datos
     */
    int insertar(Estudiante estudiante) throws SQLException;
    
    /**
     * Actualiza los datos de un estudiante existente.
     * 
     * @param estudiante Estudiante con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     * @throws SQLException Si ocurre un error en la base de datos
     */
    boolean actualizar(Estudiante estudiante) throws SQLException;
    
    /**
     * Elimina un estudiante de la base de datos.
     * 
     * @param idEstudiante ID del estudiante a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     * @throws SQLException Si ocurre un error en la base de datos
     */
    boolean eliminar(int idEstudiante) throws SQLException;
    
    /**
     * Obtiene un estudiante por su ID.
     * 
     * @param idEstudiante ID del estudiante a buscar
     * @return Estudiante encontrado o null si no existe
     * @throws SQLException Si ocurre un error en la base de datos
     */
    Estudiante obtenerPorId(int idEstudiante) throws SQLException;
    
    /**
     * Obtiene todos los estudiantes de la base de datos.
     * 
     * @return Lista de todos los estudiantes
     * @throws SQLException Si ocurre un error en la base de datos
     */
    List<Estudiante> obtenerTodos() throws SQLException;
    
    /**
     * Busca estudiantes por nombre o correo.
     * 
     * @param termino Término de búsqueda (nombre o correo)
     * @return Lista de estudiantes que coinciden con el término de búsqueda
     * @throws SQLException Si ocurre un error en la base de datos
     */
    List<Estudiante> buscarPorNombreOCorreo(String termino) throws SQLException;
    
    /**
     * Verifica si un correo ya está registrado en la base de datos.
     * 
     * @param correo Correo a verificar
     * @param idEstudiante ID del estudiante actual (para excluirlo en actualizaciones)
     * @return true si el correo ya está registrado, false en caso contrario
     * @throws SQLException Si ocurre un error en la base de datos
     */
    boolean existeCorreo(String correo, Integer idEstudiante) throws SQLException;
}