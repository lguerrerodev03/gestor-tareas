package org.example.rules;

import org.example.model.enums.AccionTarea;
import org.example.model.enums.EstadoTarea;

import java.util.*;

public class FlujoTarea {

    private static final Map<EstadoTarea, List<AccionTarea>> flujoAcciones = new HashMap<>();

    static {
        flujoAcciones.put(EstadoTarea.PENDIENTE, Arrays.asList(AccionTarea.INICIAR, AccionTarea.CANCELAR));
        flujoAcciones.put(EstadoTarea.EN_PROGRESO, Arrays.asList(AccionTarea.COMPLETAR, AccionTarea.CANCELAR));
        flujoAcciones.put(EstadoTarea.COMPLETADA, Arrays.asList(AccionTarea.REABRIR));
        flujoAcciones.put(EstadoTarea.CANCELADA, Arrays.asList(AccionTarea.REABRIR));
    }

    // Método para obtener las acciones posibles según el estado de la tarea
    public static List<AccionTarea> obtenerAccionesPosibles(EstadoTarea estado) {
        return flujoAcciones.getOrDefault(estado, Collections.emptyList());
    }
}
