package org.example.view;

import org.example.view.componentes.BotonCuadrado;
import org.example.view.componentes.TareaCard;
import org.example.view.panels.PanelBotones;
import org.example.view.scrolls.ScrollPaneTareas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {

    private JLabel lblTitulo;
    private JPanel panelBusqueda, panelInformacion;
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

        // labels
        lblTitulo = new JLabel();
        lblTitulo.setText("DASHBOARD DE TAREAS");
        lblTitulo.setBounds(300, 20, 800, 25);
        lblTitulo.setForeground(new Color(9, 92, 209));
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 24));

        // panels
        panelBusqueda = new JPanel();
        panelBusqueda.setBounds(20, 50, 950, 50);
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Búsqueda"));
        panelBusqueda.setLayout(null);

        scrollPaneTareas = new ScrollPaneTareas();

        panelInformacion = new JPanel();
        panelInformacion.setBounds(570, 110, 300, 550);
        panelInformacion.setBorder(BorderFactory.createTitledBorder("Información"));
        panelInformacion.setLayout(null);

        panelBotones = new PanelBotones(this.getWidth(), this.getHeight());

        this.add(lblTitulo);
        this.add(panelBusqueda);
        this.add(scrollPaneTareas);
        this.add(panelInformacion);
        this.add(panelBotones);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
