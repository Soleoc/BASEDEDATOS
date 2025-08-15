package com.inscripcion.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase utilitaria para gestionar la conexión a la base de datos.
 */
public class DatabaseConnection {
    
    // Configuración de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_inscripcion";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    private static Connection connection;
    
    /**
     * Obtiene una conexión a la base de datos.
     * Si la conexión no existe o está cerrada, crea una nueva.
     * 
     * @return Conexión a la base de datos
     * @throws SQLException Si ocurre un error al conectar con la base de datos
     */
    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                // Cargar el driver de MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Establecer la conexión
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            return connection;
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver de MySQL", e);
        }
    }
    
    /**
     * Cierra la conexión a la base de datos si está abierta.
     * 
     * @throws SQLException Si ocurre un error al cerrar la conexión
     */
    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}