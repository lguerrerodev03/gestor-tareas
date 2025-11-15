package org.example.view.componentes;

import org.example.model.Tarea;
import org.example.model.enums.AccionTarea;
import org.example.model.enums.EstadoTarea;
import org.example.rules.FlujoTarea;
import org.example.view.FrameEditarTarea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TareaCard extends JPanel {
    private Tarea tarea;
    private JLabel lblNombre;
    private JLabel lblEstado;
    private JLabel lblFechaVencimiento;
    //private List<BotonPersonalizado> botonesAccion = new ArrayList<>();
    private EstadoTarea estadoTarea;
    private BotonCuadrado btnEditar;
    private BotonCuadrado btnEliminar;

    public TareaCard(Tarea tarea) {
        this.estadoTarea = tarea.getEstado();
        // Estilo general de la tarjeta
        this.setLayout(null);
        this.setPreferredSize(new Dimension(500, 100));
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        this.setBackground(getColorPorEstado(tarea.getEstado()));

        // Etiquetas
        lblNombre = new JLabel("Nombre: " + tarea.getNombre());
        lblNombre.setBounds(10, 10, 300, 20);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));

        lblEstado = new JLabel("Estado: " + tarea.getEstado().toString());
        lblEstado.setBounds(10, 40, 300, 20);
        lblEstado.setFont(new Font("Arial", Font.PLAIN, 13));

        lblFechaVencimiento = new JLabel("Vence: " + tarea.getFechaVencimiento().toString());
        lblFechaVencimiento.setBounds(10, 70, 300, 20);
        lblFechaVencimiento.setFont(new Font("Arial", Font.ITALIC, 12));

        btnEditar = new BotonCuadrado("Editar");
        btnEditar.setBounds(300, 10, 80, 80);
        btnEditar.setColorNormal(Color.ORANGE);
        btnEditar.setColorHover(new Color(255, 165, 0));

        btnEliminar = new BotonCuadrado("Eliminar");
        btnEliminar.setBounds(400, 10, 80, 80);
        btnEliminar.setColorNormal(Color.RED);
        btnEliminar.setColorHover(new Color(255, 69, 0));

        this.add(lblNombre);
        this.add(lblEstado);
        this.add(lblFechaVencimiento);
        this.add(btnEditar);
        this.add(btnEliminar);

        // para clics
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Has seleccionado la tarea:\n" + tarea.getNombre(),
                        "Tarea seleccionada",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(getColorHover(tarea.getEstado()));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(getColorPorEstado(tarea.getEstado()));
            }
        });

        btnEditar.addActionListener(e -> {
            System.out.println("Editar tarea N°" + tarea.getId());
            FrameEditarTarea frameEditarTarea = new FrameEditarTarea(tarea, this);
            frameEditarTarea.setVisible(true);
        });

        btnEliminar.addActionListener(e -> {
            System.out.println("Eliminar tarea N°" + tarea.getId());
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

    public void actualizarTarea(Tarea tarea) {
        this.tarea = tarea;
        this.estadoTarea = tarea.getEstado();
        lblNombre.setText("Nombre: " + tarea.getNombre());
        lblEstado.setText("Estado: " + tarea.getEstado().toString());
        lblFechaVencimiento.setText("Vence: " + tarea.getFechaVencimiento().toString());
        this.setBackground(getColorPorEstado(tarea.getEstado()));
        this.revalidate();
        this.repaint();
    }
}
