package org.example.view.panels;

import javax.swing.*;
import java.awt.*;

public class PanelTitulo extends JPanel {

    private JLabel tituloLabel;

    public PanelTitulo() {
        setLayout(new BorderLayout());
        setBackground(Color.white);
        //setBounds((int) (0.02 * dashboardDimensionX), (int) (0.01 * dashboardDimensionY), (int) (0.96 * dashboardDimensionX), (int) (0.05 * dashboardDimensionY));

        tituloLabel = new JLabel("Gestor de Tareas", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(9, 92, 209));
        add(tituloLabel);
    }
}
