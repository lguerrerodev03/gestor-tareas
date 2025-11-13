package org.example.view.componentes;

import org.example.model.enums.AccionTarea;
import org.example.model.enums.EstadoTarea;
import org.example.rules.FlujoTarea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TareaCard extends JPanel {
    private JLabel lblNombre;
    private JLabel lblEstado;
    private JLabel lblFechaVencimiento;
    private List<BotonPersonalizado> botonesAccion = new ArrayList<>();
    private EstadoTarea estadoTarea;

    public TareaCard(String nombre, EstadoTarea estado, String fechaVencimiento) {
        this.estadoTarea = estado;
        // Estilo general de la tarjeta
        this.setLayout(null);
        this.setPreferredSize(new Dimension(500, 100));
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        this.setBackground(getColorPorEstado(estado));

        // Etiquetas
        lblNombre = new JLabel("Nombre: " + nombre);
        lblNombre.setBounds(10, 10, 300, 20);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));

        lblEstado = new JLabel("Estado: " + estado);
        lblEstado.setBounds(10, 40, 300, 20);
        lblEstado.setFont(new Font("Arial", Font.PLAIN, 13));

        lblFechaVencimiento = new JLabel("Vence: " + fechaVencimiento);
        lblFechaVencimiento.setBounds(10, 70, 300, 20);
        lblFechaVencimiento.setFont(new Font("Arial", Font.ITALIC, 12));

        this.add(lblNombre);
        this.add(lblEstado);
        this.add(lblFechaVencimiento);

        // Crear botones según las acciones válidas
        int posX = 220;
        for (AccionTarea accion : FlujoTarea.obtenerAccionesPosibles(estado)) {
            BotonPersonalizado boton = new BotonPersonalizado(accion.name());
            boton.setBounds(posX, 20, 100, 30);
            boton.setFont(new Font("Verdana", Font.PLAIN, 11));
            boton.setColorNormal(new Color(100, 149, 237));
            boton.setColorHover(new Color(70, 130, 180));
            add(boton);
            botonesAccion.add(boton);
            posX += 110;
        }

        // para clics
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Has seleccionado la tarea:\n" + nombre,
                        "Tarea seleccionada",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(getColorHover(estado));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(getColorPorEstado(estado));
            }
        });
    }

    private Color getColorPorEstado(EstadoTarea estado) {
        switch (estado) {
            case PENDIENTE: return new Color(255, 245, 204);
            case EN_PROGRESO: return new Color(204, 229, 255);
            case COMPLETADA: return new Color(204, 255, 204);
            case CANCELADA: return new Color(255, 204, 204);
            default: return Color.WHITE;
        }
    }

    private Color getColorHover(EstadoTarea estado) {
        Color base = getColorPorEstado(estado);
        return base.darker(); // un tono más oscuro
    }

}
