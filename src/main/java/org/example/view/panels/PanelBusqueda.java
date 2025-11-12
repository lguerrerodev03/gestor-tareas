package org.example.view.panels;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.util.Date;

public class PanelBusqueda extends JPanel {

    private JTextField txtNombreTarea;
    private JTextField txtDescripcionTarea;
    private JDateChooser fechaCreacionChooser;
    private JDateChooser fechaVencimientoChooser;
    private JComboBox<String> cmbEstadoTarea;

    public PanelBusqueda() {
        setLayout(null);
        //setBounds(20, 50, 960, 50);
        setBorder(BorderFactory.createTitledBorder("Búsqueda de Tareas"));

        JLabel lblNombreTarea = new JLabel("Nombre:");
        lblNombreTarea.setBounds(10, 20, 50, 25);
        add(lblNombreTarea);

        txtNombreTarea = new JTextField();
        txtNombreTarea.setBounds(60, 20, 120, 25);
        add(txtNombreTarea);

        //JLabel lblDescripcionTarea = new JLabel("Descripción:");
        //lblDescripcionTarea.setBounds(190, 20, 80, 25);
        //add(lblDescripcionTarea);
//
        //txtDescripcionTarea = new JTextField();
        //txtDescripcionTarea.setBounds(270, 20, 120, 25);
        //add(txtDescripcionTarea);

        JLabel lblFechaCreacion = new JLabel("Fecha de Creación:");
        lblFechaCreacion.setBounds(190, 20, 120, 25);
        add(lblFechaCreacion);

        fechaCreacionChooser = new JDateChooser();
        fechaCreacionChooser.setBounds(310, 20, 120, 25);
        add(fechaCreacionChooser);

        JLabel lblFechaVencimiento = new JLabel("Fecha de Vencimiento:");
        lblFechaVencimiento.setBounds(440, 20, 130, 25);
        add(lblFechaVencimiento);

        fechaVencimientoChooser = new JDateChooser();
        fechaVencimientoChooser.setBounds(570, 20, 120, 25);
        add(fechaVencimientoChooser);

        JLabel lblEstadoTarea = new JLabel("Estado de la Tarea:");
        lblEstadoTarea.setBounds(700, 20, 120, 25);
        add(lblEstadoTarea);

        cmbEstadoTarea = new JComboBox<>(new String[]{"", "Pendiente", "En progreso", "Completada", "Cancelada"});
        cmbEstadoTarea.setBounds(820, 20, 120, 25);
        add(cmbEstadoTarea);
    }

    public Date getFechaCreacion() {
        return fechaCreacionChooser.getDate();
    }

    public Date getFechaVencimiento() {
        return fechaVencimientoChooser.getDate();
    }

}