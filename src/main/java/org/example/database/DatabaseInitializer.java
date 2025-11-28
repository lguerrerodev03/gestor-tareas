package org.example.database;

import org.example.repository.HistorialTareaRepository;
import org.example.repository.TareaRepository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void inicializar() {
        eliminarTablas();
        TareaRepository.crearTabla();
        HistorialTareaRepository.crearTablaHistorial();
        System.out.println("Base de datos inicializada.");
        ejecutarSQLDesdeArchivo("/data.sql");
    }

    public static void eliminarTablas() {
        ejecutarSQL("DROP TABLE IF EXISTS historial_tareas;");
        ejecutarSQL("DROP TABLE IF EXISTS tareas;");
    }

    private static void ejecutarSQLDesdeArchivo(String ruta) {
        try (InputStream is = DatabaseInitializer.class.getResourceAsStream(ruta)){

            if (is == null) {
                System.out.println("No se encontró el archivo SQL: " + ruta);
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sqlBuilder = new StringBuilder();
            String linea;

            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
                if (linea.trim().isEmpty() || linea.trim().startsWith("--")) {
                    continue;
                }

                sqlBuilder.append(linea).append("\n");

                if (linea.trim().endsWith(";")) {
                    ejecutarSQL(sqlBuilder.toString());
                    sqlBuilder.setLength(0); // Reiniciar el StringBuilder
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void ejecutarSQL(String sql) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = ConexionBD.getConexion();
            stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                //if (conn != null) conn.close();
            } catch (Exception ignored) {

            }
        }
    }
}
