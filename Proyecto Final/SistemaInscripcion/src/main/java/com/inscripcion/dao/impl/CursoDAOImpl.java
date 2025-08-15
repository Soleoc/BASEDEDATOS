package com.inscripcion.dao.impl;

import com.inscripcion.dao.CursoDAO;
import com.inscripcion.model.Curso;
import com.inscripcion.util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz CursoDAO para acceder a los datos de cursos en la base de datos.
 */
public class CursoDAOImpl implements CursoDAO {

    @Override
    public int insertar(Curso curso) throws SQLException {
        String sql = "INSERT INTO curso (nombre, descripcion, creditos, cupo_maximo) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getDescripcion());
            stmt.setInt(3, curso.getCreditos());
            stmt.setInt(4, curso.getCupoMaximo());
            
            int filasAfectadas = stmt.executeUpdate();
            
            if (filasAfectadas == 0) {
                throw new SQLException("Error al crear curso, no se insertaron filas");
            }
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Error al crear curso, no se obtuvo el ID");
                }
            }
        }
    }

    @Override
    public boolean actualizar(Curso curso) throws SQLException {
        String sql = "UPDATE curso SET nombre = ?, descripcion = ?, creditos = ?, cupo_maximo = ? WHERE id_curso = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getDescripcion());
            stmt.setInt(3, curso.getCreditos());
            stmt.setInt(4, curso.getCupoMaximo());
            stmt.setInt(5, curso.getIdCurso());
            
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean eliminar(int idCurso) throws SQLException {
        String sql = "DELETE FROM curso WHERE id_curso = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idCurso);
            
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public Curso obtenerPorId(int idCurso) throws SQLException {
        String sql = "SELECT * FROM curso WHERE id_curso = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idCurso);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extraerCursoDeResultSet(rs);
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public List<Curso> obtenerTodos() throws SQLException {
        String sql = "SELECT * FROM curso ORDER BY nombre";
        List<Curso> cursos = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                cursos.add(extraerCursoDeResultSet(rs));
            }
        }
        
        return cursos;
    }

    @Override
    public List<Curso> buscarPorNombreOCreditos(String termino) throws SQLException {
        String sql = "SELECT * FROM curso WHERE nombre LIKE ? OR CAST(creditos AS CHAR) LIKE ? ORDER BY nombre";
        List<Curso> cursos = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            String terminoBusqueda = "%" + termino + "%";
            stmt.setString(1, terminoBusqueda);
            stmt.setString(2, terminoBusqueda);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cursos.add(extraerCursoDeResultSet(rs));
                }
            }
        }
        
        return cursos;
    }

    @Override
    public int obtenerNumeroEstudiantesInscritos(int idCurso) throws SQLException {
        String sql = "SELECT COUNT(*) FROM inscripcion WHERE id_curso = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idCurso);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        
        return 0;
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