package org.example.model;

import lombok.*;
import org.example.model.enums.EstadoTarea;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Tarea {
    private Integer id;
    private String nombre;
    private String descripcion;
    private EstadoTarea estado;
    @Setter(AccessLevel.NONE)
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaVencimiento;
    @Builder.Default
    private Boolean eliminado = false;
    @Builder.Default
    private List<HistorialTarea> historial = new ArrayList<>();

    public static Tarea nueva(String nombre, String descripcion, EstadoTarea estado, LocalDateTime fechaVencimiento) {
        Tarea tarea = new Tarea();
        tarea.nombre = nombre;
        tarea.descripcion = descripcion;
        tarea.estado = estado;
        tarea.fechaCreacion = LocalDateTime.now();
        tarea.fechaVencimiento = fechaVencimiento;
        tarea.eliminado = false;
        tarea.historial = new ArrayList<>();
        return tarea;
    }

    // 📜 — Agrega un registro al historial
    public void agregarHistorial(HistorialTarea registro) {
        if (historial == null) {
            historial = new ArrayList<>();
        }
        historial.add(registro);
    }

    // 🚫 — Marca la tarea como eliminada (eliminación lógica)
    public void eliminar() {
        this.eliminado = true;
    }

    // 🔁 — Cambia el estado de la tarea
    public void cambiarEstado(EstadoTarea nuevoEstado) {
        this.estado = nuevoEstado;
    }
}
