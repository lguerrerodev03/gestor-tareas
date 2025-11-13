package org.example.view;

import org.example.view.componentes.BotonCuadrado;
import org.example.view.componentes.TareaCard;
import org.example.view.panels.PanelBotones;
import org.example.view.panels.PanelBusqueda;
import org.example.view.panels.PanelInformacion;
import org.example.view.panels.PanelTitulo;
import org.example.view.scrolls.ScrollPaneTareas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {

    private PanelTitulo panelTitulo;
    private PanelInformacion panelInformacion;
    private PanelBusqueda panelBusqueda;
    private PanelBotones panelBotones;
    private ScrollPaneTareas scrollPaneTareas;

    public Dashboard() {
        super();
        configurarVentana();
        iniciarControles();
    }

    private void configurarVentana() {
        this.setTitle("DASHBOARD");
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void iniciarControles() {

        // panels
        panelTitulo = new PanelTitulo();
        panelBusqueda = new PanelBusqueda();
        scrollPaneTareas = new ScrollPaneTareas();
        panelInformacion = new PanelInformacion(12);
        panelBotones = new PanelBotones();

        definirDimensiones(this.getWidth(), this.getHeight());

        this.add(panelTitulo);
        this.add(panelBusqueda);
        this.add(scrollPaneTareas);
        this.add(panelInformacion);
        this.add(panelBotones);
    }

    private void definirDimensiones(int dashboardDimensionX, int dashboardDimensionY) {
        panelTitulo.setBounds(
                porcentaje(2, dashboardDimensionX),
                porcentaje(1, dashboardDimensionY),
                porcentaje(96, dashboardDimensionX),
                porcentaje(5, dashboardDimensionY)
        );

        panelBusqueda.setBounds(
                porcentaje(2, dashboardDimensionX),
                porcentaje(8, dashboardDimensionY),
                porcentaje(96, dashboardDimensionX),
                porcentaje(7, dashboardDimensionY)
        );

        scrollPaneTareas.setBounds(
                porcentaje(2, dashboardDimensionX),
                porcentaje(16, dashboardDimensionY),
                porcentaje(55, dashboardDimensionX),
                porcentaje(78, dashboardDimensionY)
        );

        panelInformacion.setBounds(
                porcentaje(57, dashboardDimensionX),
                porcentaje(16, dashboardDimensionY),
                porcentaje(30, dashboardDimensionX),
                porcentaje(78, dashboardDimensionY)
        );

        panelBotones.setBounds(
                porcentaje(89, dashboardDimensionX),
                porcentaje(16, dashboardDimensionY),
                porcentaje(10, dashboardDimensionX),
                porcentaje(78, dashboardDimensionY)
        );
    }

    private int porcentaje(double porcentaje, int dimension) {
        return (int) (porcentaje * dimension/100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
