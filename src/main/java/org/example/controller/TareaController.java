package org.example.controller;

import org.example.model.Tarea;
import org.example.service.TareaService;

import java.time.LocalDateTime;
import java.util.List;

public class TareaController {
    private final TareaService tareaService = new TareaService();

    public void crearTarea(String nombre, String descripcion, LocalDateTime fechaVencimiento) {
        tareaService.crearTarea(nombre, descripcion, fechaVencimiento);
    }

    public List<Tarea> listarTareas() {
        return tareaService.obtenerTareas();
    }
}
