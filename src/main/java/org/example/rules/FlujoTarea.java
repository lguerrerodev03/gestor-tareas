package org.example.rules;

import org.example.model.enums.AccionTarea;
import org.example.model.enums.EstadoTarea;

import java.util.*;

public class FlujoTarea {

    private static final Map<EstadoTarea, List<AccionTarea>> flujoAcciones = new HashMap<>();
    private static final Map<AccionTarea, EstadoTarea> transicionesEstado = new HashMap<>();

    static {
        // Acciones permitidas para cada estado de la tarea
        flujoAcciones.put(EstadoTarea.PENDIENTE, Arrays.asList(AccionTarea.INICIAR, AccionTarea.CANCELAR));
        flujoAcciones.put(EstadoTarea.EN_PROGRESO, Arrays.asList(AccionTarea.COMPLETAR, AccionTarea.CANCELAR));
        flujoAcciones.put(EstadoTarea.COMPLETADA, Arrays.asList(AccionTarea.REABRIR));
        flujoAcciones.put(EstadoTarea.CANCELADA, Arrays.asList(AccionTarea.REABRIR));

        // Acciones generales permitidas en cualquier estado
        flujoAcciones.put(EstadoTarea.PENDIENTE, Arrays.asList(AccionTarea.VER));
        flujoAcciones.put(EstadoTarea.EN_PROGRESO, Arrays.asList(AccionTarea.VER));
        flujoAcciones.put(EstadoTarea.COMPLETADA, Arrays.asList(AccionTarea.VER));
        flujoAcciones.put(EstadoTarea.CANCELADA, Arrays.asList(AccionTarea.VER));

        // Acciones de edición permitidas en cualquier estado
        flujoAcciones.put(EstadoTarea.PENDIENTE, Arrays.asList(AccionTarea.EDITAR));
        flujoAcciones.put(EstadoTarea.EN_PROGRESO, Arrays.asList(AccionTarea.EDITAR));
        flujoAcciones.put(EstadoTarea.COMPLETADA, Arrays.asList(AccionTarea.EDITAR));
        flujoAcciones.put(EstadoTarea.CANCELADA, Arrays.asList(AccionTarea.EDITAR));

        // Acción de creación permitida en cualquier estado
        flujoAcciones.put(EstadoTarea.PENDIENTE, Arrays.asList(AccionTarea.CREAR));
        flujoAcciones.put(EstadoTarea.EN_PROGRESO, Arrays.asList(AccionTarea.CREAR));

        // Definición de transiciones de estado basadas en acciones
        transicionesEstado.put(AccionTarea.INICIAR, EstadoTarea.EN_PROGRESO);
        transicionesEstado.put(AccionTarea.COMPLETAR, EstadoTarea.COMPLETADA);
        transicionesEstado.put(AccionTarea.CANCELAR, EstadoTarea.CANCELADA);
        transicionesEstado.put(AccionTarea.REABRIR, EstadoTarea.PENDIENTE);
    }

    // Método para obtener las acciones posibles según el estado de la tarea
    public static List<AccionTarea> obtenerAccionesPosibles(EstadoTarea estado) {
        return flujoAcciones.getOrDefault(estado, Collections.emptyList());
    }

    // Método para obtener el nuevo estado según la acción realizada
    public static Optional<EstadoTarea> obtenerNuevoEstado(AccionTarea accion) {
        return Optional.ofNullable(transicionesEstado.get(accion));
    }

    // Método para validar si una acción es permitida en el estado actual
    public static boolean esAccionPermitida(EstadoTarea estado, AccionTarea accion) {
        return obtenerAccionesPosibles(estado).contains(accion);
    }
}
