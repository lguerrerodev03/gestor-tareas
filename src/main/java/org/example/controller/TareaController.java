package org.example.controller;

import org.example.model.Tarea;
import org.example.model.enums.AccionTarea;
import org.example.service.TareaService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TareaController {
    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    public void crearTarea(String nombre, String descripcion, LocalDateTime fechaVencimiento) {
        tareaService.crearTarea(nombre, descripcion, fechaVencimiento);
    }

    public List<Tarea> listarTareas() {
        return tareaService.obtenerTareas();
    }

    public Optional<Tarea> obtenerTareaPorId(int id) {
        return tareaService.obtenerTareaPorId(id);
    }

    public boolean actualizarTarea(Tarea tarea) {
        return tareaService.actualizarTarea(tarea);
    }

    public boolean actualizarEstadoTarea(int idTarea, AccionTarea accionTarea) {
        return tareaService.actualizarEstadoTarea(idTarea, accionTarea);
    }
}
