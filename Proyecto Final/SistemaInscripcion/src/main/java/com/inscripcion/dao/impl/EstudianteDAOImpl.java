package com.inscripcion.dao.impl;

import com.inscripcion.dao.EstudianteDAO;
import com.inscripcion.model.Estudiante;
import com.inscripcion.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz EstudianteDAO para acceder a los datos de estudiantes en la base de datos.
 */
public class EstudianteDAOImpl implements EstudianteDAO {

    @Override
    public int insertar(Estudiante estudiante) throws SQLException {
        String sql = "INSERT INTO estudiante (nombre, correo, telefono, fecha_nacimiento, direccion) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getCorreo());
            stmt.setString(3, estudiante.getTelefono());
            
            if (estudiante.getFechaNacimiento() != null) {
                stmt.setDate(4, Date.valueOf(estudiante.getFechaNacimiento()));
            } else {
                stmt.setNull(4, Types.DATE);
            }
            
            stmt.setString(5, estudiante.getDireccion());
            
            int filasAfectadas = stmt.executeUpdate();
            
            if (filasAfectadas == 0) {
                throw new SQLException("Error al crear estudiante, no se insertaron filas");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Error al crear estudiante, no se obtuvo el ID");
                }
            }
        }
    }

    @Override
    public boolean actualizar(Estudiante estudiante) throws SQLException {
        String sql = "UPDATE estudiante SET nombre = ?, correo = ?, telefono = ?, fecha_nacimiento = ?, direccion = ? WHERE id_estudiante = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getCorreo());
            stmt.setString(3, estudiante.getTelefono());
            
            if (estudiante.getFechaNacimiento() != null) {
                stmt.setDate(4, Date.valueOf(estudiante.getFechaNacimiento()));
            } else {
                stmt.setNull(4, Types.DATE);
            }
            
            stmt.setString(5, estudiante.getDireccion());
            stmt.setInt(6, estudiante.getIdEstudiante());
            
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(int idEstudiante) throws SQLException {
        String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idEstudiante);
            
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public Estudiante obtenerPorId(int idEstudiante) throws SQLException {
        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idEstudiante);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extraerEstudianteDeResultSet(rs);
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public List<Estudiante> obtenerTodos() throws SQLException {
        String sql = "SELECT * FROM estudiante ORDER BY nombre";
        List<Estudiante> estudiantes = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                estudiantes.add(extraerEstudianteDeResultSet(rs));
            }
        }
        
        return estudiantes;
    }

    @Override
    public List<Estudiante> buscarPorNombreOCorreo(String termino) throws SQLException {
        String sql = "SELECT * FROM estudiante WHERE nombre LIKE ? OR correo LIKE ? ORDER BY nombre";
        List<Estudiante> estudiantes = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            String terminoBusqueda = "%" + termino + "%";
            stmt.setString(1, terminoBusqueda);
            stmt.setString(2, terminoBusqueda);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    estudiantes.add(extraerEstudianteDeResultSet(rs));
                }
            }
        }
        
        return estudiantes;
    }

    @Override
    public boolean existeCorreo(String correo, Integer idEstudiante) throws SQLException {
        String sql = "SELECT COUNT(*) FROM estudiante WHERE correo = ?";
        
        // Si se proporciona un ID, excluirlo de la búsqueda (para actualizaciones)
        if (idEstudiante != null) {
            sql += " AND id_estudiante != ?";
        }
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, correo);
            
            if (idEstudiante != null) {
                stmt.setInt(2, idEstudiante);
            }
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        
        return false;
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
}