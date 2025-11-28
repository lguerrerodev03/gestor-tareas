package org.example.repository;

import org.example.database.ConexionBD;
import org.example.model.HistorialTarea;
import org.example.model.enums.AccionTarea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                     "FOREIGN KEY(tarea_id) REFERENCES tareas(id) ON DELETE CASCADE" +
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

    public List<HistorialTarea> obtenerHistorialPorTareaId(int tareaId) {
        // Implementación para obtener el historial de una tarea por su ID
        String sql = "SELECT * FROM historial_tareas WHERE tarea_id = ?";
        List<HistorialTarea> historialList = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tareaId);

            try {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    HistorialTarea historial = mapearHistorial(rs);
                    historialList.add(historial);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return historialList;
    }

    private HistorialTarea mapearHistorial(ResultSet rs) throws SQLException {

        return HistorialTarea.builder()
                .id(rs.getInt("id"))
                .tareaId(rs.getInt("tarea_id"))
                .accion(AccionTarea.valueOf(rs.getString("accion")))
                .fecha(rs.getTimestamp("fecha").toLocalDateTime())
                .comentario(rs.getString("comentario"))
                .build();
    }
}
