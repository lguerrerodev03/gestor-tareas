package org.example.view.componentes;

import javax.swing.*;
import java.awt.*;

public class TareaCard extends JPanel {
    private JLabel lblNombre;
    private JLabel lblEstado;
    private JLabel lblFechaVencimiento;

    public TareaCard(String nombre, String estado, String fechaVencimiento) {
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
    }

    private Color getColorPorEstado(String estado) {

        Color color = new Color(255, 255, 255); // blanco por defecto

        switch (estado.toLowerCase()) {
            case "pendiente":
                return new Color(255, 245, 204); // amarillo claro
            case "en progreso":
                return new Color(204, 229, 255); // azul claro
            case "completada":
                return new Color(204, 255, 204); // verde claro
            case "cancelada":
                return new Color(255, 204, 204); // rojo claro

        };
        return color;
    }
}
