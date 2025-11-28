package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:sqlite:database.tareas.db";
    //private static Connection conexion;

    public static Connection getConexion() {
        try {
            System.out.println("✅ Conexión a SQLite establecida.");
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException("❌ Error al conectar con la base de datos: " + e.getMessage());
        }
    }

}
