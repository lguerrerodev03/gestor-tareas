package org.example.view.scrolls;

import org.example.view.componentes.TareaCard;

import javax.swing.*;

public class ScrollPaneTareas extends JScrollPane {

    private JPanel panelTareas;

    public ScrollPaneTareas() {
        panelTareas = new JPanel();
        panelTareas.setBorder(BorderFactory.createTitledBorder("Tareas"));
        panelTareas.setLayout(new BoxLayout(panelTareas, BoxLayout.Y_AXIS));
        this.setViewportView(panelTareas);
        this.setBounds(20, 110, 550, 550);
        this.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        this.getVerticalScrollBar().setUnitIncrement(30);

        cargarTareas(50); // Cargar tareas de ejemplo
    }

    public void cargarTareas(int cantidad) {
        panelTareas.removeAll();
        for (int i = 1; i <= cantidad; i++) {
            String estado;
            int valor = i % 4;
            if (valor == 0) {
                estado = "Pendiente";
            } else if (valor == 1) {
                estado = "En progreso";
            } else if (valor == 2) {
                estado = "Completada";
            } else {
                estado = "Cancelada";
            }

            TareaCard tareaCard = new TareaCard("Tarea " + i, estado, "2024-12-31");
            panelTareas.add(tareaCard);
            panelTareas.add(Box.createVerticalStrut(10)); // Espacio entre cards
        }
        panelTareas.revalidate();
        panelTareas.repaint();
    }
}
