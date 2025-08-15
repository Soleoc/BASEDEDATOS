package com.inscripcion.dao.impl;

import com.inscripcion.dao.InscripcionDAO;
import com.inscripcion.model.Curso;
import com.inscripcion.model.Estudiante;
import com.inscripcion.model.Inscripcion;
import com.inscripcion.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz InscripcionDAO para gestionar las inscripciones en la base de datos.
 */
public class InscripcionDAOImpl implements InscripcionDAO {

    @Override
    public boolean inscribir(int idEstudiante, int idCurso) throws SQLException {
        String sql = "INSERT INTO inscripcion (id_estudiante, id_curso) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idEstudiante);
            stmt.setInt(2, idCurso);
            
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminarInscripcion(int idEstudiante, int idCurso) throws SQLException {
        String sql = "DELETE FROM inscripcion WHERE id_estudiante = ? AND id_curso = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idEstudiante);
            stmt.setInt(2, idCurso);
            
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean estaInscrito(int idEstudiante, int idCurso) throws SQLException {
        String sql = "SELECT COUNT(*) FROM inscripcion WHERE id_estudiante = ? AND id_curso = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idEstudiante);
            stmt.setInt(2, idCurso);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        
        return false;
    }

    @Override
    public List<Curso> obtenerCursosDeEstudiante(int idEstudiante) throws SQLException {
        String sql = "SELECT c.* FROM curso c INNER JOIN inscripcion i ON c.id_curso = i.id_curso WHERE i.id_estudiante = ? ORDER BY c.nombre";
        List<Curso> cursos = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idEstudiante);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cursos.add(extraerCursoDeResultSet(rs));
                }
            }
        }
        
        return cursos;
    }

    @Override
    public List<Estudiante> obtenerEstudiantesDeCurso(int idCurso) throws SQLException {
        String sql = "SELECT e.* FROM estudiante e INNER JOIN inscripcion i ON e.id_estudiante = i.id_estudiante WHERE i.id_curso = ? ORDER BY e.nombre";
        List<Estudiante> estudiantes = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idCurso);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    estudiantes.add(extraerEstudianteDeResultSet(rs));
                }
            }
        }
        
        return estudiantes;
    }

    @Override
    public List<Inscripcion> obtenerTodasLasInscripciones() throws SQLException {
        String sql = "SELECT e.*, c.*, i.fecha_inscripcion FROM inscripcion i " +
                    "INNER JOIN estudiante e ON i.id_estudiante = e.id_estudiante " +
                    "INNER JOIN curso c ON i.id_curso = c.id_curso " +
                    "ORDER BY i.fecha_inscripcion DESC";
        
        List<Inscripcion> inscripciones = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Estudiante estudiante = extraerEstudianteDeResultSet(rs);
                Curso curso = extraerCursoDeResultSet(rs);
                
                Timestamp fechaInscripcion = rs.getTimestamp("fecha_inscripcion");
                LocalDateTime fecha = null;
                if (fechaInscripcion != null) {
                    fecha = fechaInscripcion.toLocalDateTime();
                }
                
                Inscripcion inscripcion = new Inscripcion(estudiante, curso, fecha);
                inscripciones.add(inscripcion);
            }
        }
        
        return inscripciones;
    }
    
    /**
     * Método auxiliar para extraer un objeto Estudiante de un ResultSet.
     * 
     * @param rs ResultSet con los datos del estudiante
     * @return Objeto Estudiante con los datos extraídos
     * @throws SQLException Si ocurre un error al acceder a los datos
     */
    private Estudiante extraerEstudianteDeResultSet(ResultSet rs) throws SQLException {
        Estudiante estudiante = new Estudiante();
        
        estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
        estudiante.setNombre(rs.getString("nombre"));
        estudiante.setCorreo(rs.getString("correo"));
        estudiante.setTelefono(rs.getString("telefono"));
        
        Date fechaNacimiento = rs.getDate("fecha_nacimiento");
        if (fechaNacimiento != null) {
            estudiante.setFechaNacimiento(fechaNacimiento.toLocalDate());
        }
        
        estudiante.setDireccion(rs.getString("direccion"));
        
        Timestamp fechaRegistro = rs.getTimestamp("fecha_registro");
        if (fechaRegistro != null) {
            estudiante.setFechaRegistro(fechaRegistro.toLocalDateTime());
        }
        
        return estudiante;
    }
    
    /**
     * Método auxiliar para extraer un objeto Curso de un ResultSet.
     * 
     * @param rs ResultSet con los datos del curso
     * @return Objeto Curso con los datos extraídos
     * @throws SQLException Si ocurre un error al acceder a los datos
     */
    private Curso extraerCursoDeResultSet(ResultSet rs) throws SQLException {
        Curso curso = new Curso();
        
        curso.setIdCurso(rs.getInt("id_curso"));
        curso.setNombre(rs.getString("nombre"));
        curso.setDescripcion(rs.getString("descripcion"));
        curso.setCreditos(rs.getInt("creditos"));
        curso.setCupoMaximo(rs.getInt("cupo_maximo"));
        
        Timestamp fechaCreacion = rs.getTimestamp("fecha_creacion");
        if (fechaCreacion != null) {
            curso.setFechaCreacion(fechaCreacion.toLocalDateTime());
        }
        
        return curso;
    }
}