package org.example.view.panels;

import org.example.view.componentes.TareaCard;

import javax.swing.*;

public class PanelTareas extends JPanel {
    private JPanel panelContenido;
    private JScrollPane scrollPane;

    public PanelTareas() {
        setLayout(null);
        setBorder(BorderFactory.createTitledBorder("Tareas"));
        setBounds(20, 110, 550, 550);

        panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(panelContenido);
        scrollPane.setBounds(10, 20, 520, 510);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);

        add(scrollPane);
    }

    public void agregarTareasDemo() {
        panelContenido.removeAll();
        for (int i = 1; i <= 50; i++) {
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
            panelContenido.add(tareaCard);
            panelContenido.add(Box.createVerticalStrut(10));
        }
        panelContenido.revalidate();
        panelContenido.repaint();
    }
}
