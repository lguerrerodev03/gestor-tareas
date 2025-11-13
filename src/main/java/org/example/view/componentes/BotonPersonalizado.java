package org.example.view.componentes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BotonPersonalizado extends JButton {

    private Color colorNormal = new Color(9, 92, 209);
    private Color colorHover = new Color(125, 120, 240);
    private Color colorTexto = Color.WHITE;

    public BotonPersonalizado(String texto) {
        super(texto);

        // Propiedades básicas
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setBackground(colorNormal);
        setForeground(colorTexto);
        setFont(new Font("Verdana", Font.BOLD, 14));

        // Bordes redondeados (opcional)
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        // Efecto hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(colorHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(colorNormal);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Convertimos Graphics en Graphics2D para mejores efectos
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo cuadrado o redondeado
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // esquinas redondeadas

        // Borde blanco
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 20, 20);

        g2.dispose();
        super.paintComponent(g);
    }

    public void setColorNormal(Color colorNormal) {
        this.colorNormal = colorNormal;
        setBackground(colorNormal);
    }

    public void setColorHover(Color colorHover) {
        this.colorHover = colorHover;
    }

    public void setColorTexto(Color colorTexto) {
        this.colorTexto = colorTexto;
        setForeground(colorTexto);
    }
}