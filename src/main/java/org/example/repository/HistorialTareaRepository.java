package org.example.repository;

import org.example.database.ConexionBD;
import org.example.model.HistorialTarea;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class HistorialTareaRepository {

    /**
     * Crea la tabla de historial de tareas si no existe.
     */
    public static void crearTablaHistorial() {
        String sql = "CREATE TABLE IF NOT EXISTS historial_tareas (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "tarea_id INTEGER NOT NULL," +
                     "accion TEXT NOT NULL," +
                     "fecha DATETIME NOT NULL," +
                     "comentario TEXT," +
                     "FOREIGN KEY(tarea_id) REFERENCES tareas(id)" +
                     ");";
    }

    public boolean insertar(HistorialTarea historial) {
        // Implementación para insertar un registro en el historial de tareas
        String sql = "INSERT INTO historial_tareas(tarea_id, accion, fecha, comentario) VALUES(?,?,?,?)";

        try (Connection conn = ConexionBD.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            // Configurar los parámetros del PreparedStatement aquí

            ps.setInt(1, historial.getTareaId());
            ps.setString(2, historial.getAccion().name());
            ps.setString(3, historial.getFecha().toString());
            ps.setString(4, historial.getComentario());
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
