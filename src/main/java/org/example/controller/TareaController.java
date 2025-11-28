package org.example.controller;

import org.example.model.Tarea;
import org.example.service.TareaService;

import java.time.LocalDateTime;
import java.util.List;

public class TareaController {
    private final TareaService tareaService;

    public TareaController() {
        this.tareaService = new TareaService();
    }

    public void crearTarea(String nombre, String descripcion, LocalDateTime fechaVencimiento) {
        tareaService.crearTarea(nombre, descripcion, fechaVencimiento);
    }

    public List<Tarea> listarTareas() {
        return tareaService.obtenerTareas();
    }

    public Tarea obtenerTareaPorId(int id) {
        return tareaService.obtenerTareaPorId(id).orElse(null);
    }

    public boolean actualizarTarea(Tarea tarea) {
        return tareaService.actualizarTarea(tarea);
    }
}
