package org.example.view.scrolls;

import org.example.model.Tarea;
import org.example.model.enums.EstadoTarea;
import org.example.view.componentes.TareaCard;

import javax.swing.*;
import java.time.LocalDateTime;

public class ScrollPaneTareas extends JScrollPane {

    private JPanel panelTareas;

    public ScrollPaneTareas() {
        panelTareas = new JPanel();
        panelTareas.setBorder(BorderFactory.createTitledBorder("Tareas"));
        panelTareas.setLayout(new BoxLayout(panelTareas, BoxLayout.Y_AXIS));
        this.setViewportView(panelTareas);
        //this.setBounds(20, 110, 550, 550);
        this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        this.getVerticalScrollBar().setUnitIncrement(30);

        cargarTareas(50); // Cargar tareas de ejemplo
    }

    public void cargarTareas(int cantidad) {
        panelTareas.removeAll();
        for (int i = 1; i <= cantidad; i++) {
            EstadoTarea estado;
            int valor = i % 4;
            if (valor == 0) {
                estado = EstadoTarea.PENDIENTE;
            } else if (valor == 1) {
                estado = EstadoTarea.EN_PROGRESO;
            } else if (valor == 2) {
                estado = EstadoTarea.COMPLETADA;
            } else {
                estado = EstadoTarea.CANCELADA;
            }
            Tarea tarea = Tarea.builder()
                    .id(i)
                    .nombre("Tarea " + i)
                    .estado(estado)
                    .fechaVencimiento(LocalDateTime.parse("2024-12-31T00:00:00"))
                    .build();

            TareaCard tareaCard = new TareaCard(tarea);
            panelTareas.add(tareaCard);
            panelTareas.add(Box.createVerticalStrut(10)); // Espacio entre cards
        }
        panelTareas.revalidate();
        panelTareas.repaint();
    }
}
