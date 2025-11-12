package org.example.view.panels;

import org.example.view.componentes.BotonCuadrado;

import javax.swing.*;
import java.awt.*;

public class PanelBotones extends JPanel {

    private BotonCuadrado btnHistorial;
    private BotonCuadrado btnInformacion;
    private JSeparator separator;
    private BotonCuadrado btnAgregar;
    private BotonCuadrado btnEliminar;
    private BotonCuadrado btnEditar;

    public PanelBotones() {

        //this.setBounds(dashboardDimensionX - 120, 110, 100, dashboardDimensionY - 150);
        this.setBorder(BorderFactory.createTitledBorder("Botones"));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        btnInformacion = new BotonCuadrado("Info");
        btnHistorial = new BotonCuadrado("Historial");
        separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1)); // grosor de 1px
        separator.setPreferredSize(new Dimension(100, 1));
        btnAgregar = new BotonCuadrado("Agregar");
        btnEditar = new BotonCuadrado("Editar");
        btnEliminar = new BotonCuadrado("Eliminar");

        btnInformacion.setColorNormal(Color.MAGENTA);
        btnInformacion.setColorHover(new Color(255, 105, 180));

        btnHistorial.setColorNormal(Color.GREEN);
        btnHistorial.setColorHover(new Color(60, 179, 113));

        btnAgregar.setColorNormal(Color.BLUE);
        btnAgregar.setColorHover(new Color(100, 149, 237));

        btnEditar.setColorNormal(Color.ORANGE);
        btnEditar.setColorHover(new Color(255, 165, 0));

        btnEliminar.setColorNormal(Color.RED);
        btnEliminar.setColorHover(new Color(255, 69, 0));


        btnInformacion.setAlignmentX(CENTER_ALIGNMENT);
        btnHistorial.setAlignmentX(CENTER_ALIGNMENT);
        separator.setAlignmentX(CENTER_ALIGNMENT);
        btnAgregar.setAlignmentX(CENTER_ALIGNMENT);
        btnEditar.setAlignmentX(CENTER_ALIGNMENT);
        btnEliminar.setAlignmentX(CENTER_ALIGNMENT);

        this.add(Box.createVerticalStrut(20));
        this.add(btnInformacion);
        this.add(Box.createVerticalStrut(15));
        this.add(btnHistorial);
        this.add(Box.createVerticalStrut(20));
        this.add(separator);
        this.add(Box.createVerticalStrut(20));
        this.add(btnAgregar);
        this.add(Box.createVerticalStrut(15));
        this.add(btnEditar);
        this.add(Box.createVerticalStrut(15));
        this.add(btnEliminar);

        btnAgregar.addActionListener(e -> {
            System.out.println("Botón Agregar presionado");
        });

        btnInformacion.addActionListener(e -> {
            System.out.println("Botón Información presionado");
        });

        btnEditar.addActionListener(e -> {
            System.out.println("Botón Editar presionado");
        });

        btnEliminar.addActionListener(e -> {
            System.out.println("Botón Eliminar presionado");
        });

        btnHistorial.addActionListener(e -> {
            System.out.println("Botón Historial presionado");
        });
    }
}
