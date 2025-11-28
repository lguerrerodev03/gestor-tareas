package org.example.service;

import org.example.model.HistorialTarea;
import org.example.model.Tarea;
import org.example.model.enums.AccionTarea;
import org.example.model.enums.EstadoTarea;
import org.example.repository.HistorialTareaRepository;
import org.example.repository.TareaRepository;
import org.example.rules.FlujoTarea;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TareaService {
    private final TareaRepository tareaRepository;
    private final HistorialTareaRepository historialRepository;

    public TareaService() {
        this.tareaRepository = new TareaRepository();
        this.historialRepository = new HistorialTareaRepository();
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

    public Optional<Tarea> obtenerTareaPorId(int id) {

        if (id <= 0) {
            return Optional.empty();
        }
        return tareaRepository.obtenerPorId(id);
    }

    public boolean actualizarTarea(Tarea tarea) {
        return tareaRepository.actualizar(tarea);
    }

    public boolean actualizarEstadoTarea(int id, AccionTarea accion) {
        Tarea tarea = tareaRepository.obtenerPorId(id).orElse(null);
        if (tarea == null) return false;
        EstadoTarea nuevoEstado = FlujoTarea.obtenerNuevoEstado(accion).orElse(null);
        if (nuevoEstado == null) return false;
        tarea.cambiarEstado(nuevoEstado);
        boolean actualizado = tareaRepository.actualizar(tarea);
        if (actualizado) {
            HistorialTarea historial = HistorialTarea.builder()
                    .tareaId(tarea.getId())
                    .accion(accion)
                    .fecha(LocalDateTime.now())
                    .comentario("Estado cambiado a " + nuevoEstado)
                    .build();
            historialRepository.insertar(historial);
        }
        return actualizado;
    }

    public boolean eliminarTarea(int id) {
        return tareaRepository.eliminar(id);
    }

    public boolean eliminarTareaLogicamente(int id) {
        return tareaRepository.eliminarLogicamente(id);
    }
}
