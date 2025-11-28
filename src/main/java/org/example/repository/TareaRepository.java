package org.example.repository;

import org.example.database.ConexionBD;
import org.example.model.Tarea;
import org.example.model.enums.EstadoTarea;
import org.example.util.FechaUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TareaRepository {

    /**
     * Crea la tabla de tareas si no existe.
     * La tabla contiene los siguientes campos:
     * - id: Identificador único de la tarea (clave primaria, autoincremental)
     * - nombre: Nombre de la tarea (no nulo)
     * - descripcion: Descripción de la tarea
     * - estado: Estado de la tarea (no nulo)
     * - fechaCreacion: Fecha de creación de la tarea (no nulo, valor por defecto: fecha y hora actual)
     * - fechaVencimiento: Fecha de vencimiento de la tarea (no nulo)
     */
    public static void crearTabla() {
        String sql = " CREATE TABLE IF NOT EXISTS tareas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "descripcion TEXT, " +
                "estado TEXT NOT NULL, " +
                "fechaCreacion TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                "fechaVencimiento TEXT NOT NULL," +
                "eliminado INTEGER NOT NULL DEFAULT 0" +
                ");";

        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserta una nueva tarea en la base de datos.
     * @param tarea La tarea a insertar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insertar(Tarea tarea) {
        String sql = "INSERT INTO " +
                "tareas(nombre, descripcion, estado, fechaCreacion, fechaVencimiento) " +
                "VALUES(?,?,?,?,?)";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, tarea.getNombre());
            ps.setString(2, tarea.getDescripcion());
            ps.setString(3, tarea.getEstado().name());
            ps.setString(4, tarea.getFechaCreacion().toString());
            ps.setString(5, tarea.getFechaVencimiento().toString());
            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Lista todas las tareas almacenadas en la base de datos.
     * @return Una lista de tareas.
     */
    public List<Tarea> listar() {
        List<Tarea> tareas = new ArrayList<>();
        String sql = "SELECT * FROM tareas WHERE eliminado = 0";

        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tareas.add(mapearTarea(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tareas;
    }

    /**
     * Obtiene una tarea por su ID.
     * @param id El ID de la tarea.
     * @return Un Optional que contiene la tarea si se encuentra, o vacío si no.
     */
    public Optional<Tarea> obtenerPorId(int id) {
        String sql = "SELECT * FROM tareas WHERE id = ? AND eliminado = 0";

        try (
            Connection conn = ConexionBD.getConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
                ){

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Tarea tarea = mapearTarea(rs);
                return Optional.of(tarea);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Actualiza una tarea existente en la base de datos.
     * @param tarea La tarea con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizar(Tarea tarea) {
        String sql = "UPDATE tareas SET nombre = ?, descripcion = ?, estado = ?, fechaVencimiento = ? WHERE id = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tarea.getNombre());
            ps.setString(2, tarea.getDescripcion());
            ps.setString(3, tarea.getEstado().name());
            ps.setString(4, tarea.getFechaVencimiento().toString());
            ps.setInt(5, tarea.getId());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Realiza una eliminación lógica de una tarea por su ID.
     * @param id El ID de la tarea a eliminar lógicamente.
     * @return true si la eliminación lógica fue exitosa, false en caso contrario.
     */
    public boolean eliminarLogicamente(int id) {
        String sql = "UPDATE tareas SET eliminado = 1 WHERE id = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina una tarea por su ID.
     * @param id El ID de la tarea a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminar(int id) {
        String sql = "DELETE FROM tareas WHERE id = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Tarea mapearTarea(ResultSet rs) throws SQLException {
        return new Tarea(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                EstadoTarea.valueOf(rs.getString("estado")),
                FechaUtils.parseFecha(rs.getString("fechaCreacion")),
                FechaUtils.parseFecha(rs.getString("fechaVencimiento")),
                rs.getInt("eliminado") == 1,
                new ArrayList<>()
        );
    }
}
