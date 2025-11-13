package org.example.service;

import org.example.model.HistorialTarea;
import org.example.model.Tarea;
import org.example.model.enums.AccionTarea;
import org.example.model.enums.EstadoTarea;
import org.example.repository.HistorialTareaRepository;
import org.example.repository.TareaRepository;

import java.time.LocalDateTime;
import java.util.List;

public class TareaService {
    private final TareaRepository tareaRepository = new TareaRepository();
    private final HistorialTareaRepository historialRepository = new HistorialTareaRepository();

    public TareaService() {
        //repository.crearTabla();
    }

    public void crearTarea(String nombre, String descripcion, LocalDateTime fechaVencimiento) {
        Tarea tarea = Tarea.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .estado(EstadoTarea.PENDIENTE)
                .fechaVencimiento(fechaVencimiento)
                .build();
        boolean inserto = tareaRepository.insertar(tarea);

        if (inserto) {
            HistorialTarea historial = HistorialTarea.builder()
                    .tareaId(tarea.getId())
                    .accion(AccionTarea.CREAR)
                    .fecha(LocalDateTime.now())
                    .comentario("Tarea creada")
                    .build();

            historialRepository.insertar(historial);

        }
    }

    public List<Tarea> obtenerTareas() {
        return tareaRepository.listar();
    }

    public Tarea obtenerTareaPorId(int id) {
        return tareaRepository.obtenerPorId(id).orElse(null);
    }


}
