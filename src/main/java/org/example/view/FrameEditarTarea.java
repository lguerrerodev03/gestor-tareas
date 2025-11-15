package org.example.view;

import com.toedter.calendar.JDateChooser;
import org.example.model.Tarea;
import org.example.view.componentes.BotonPersonalizado;
import org.example.view.componentes.TareaCard;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class FrameEditarTarea extends JFrame {
    private TareaCard card;
    private JLabel lblTitulo = new JLabel("Editar Tarea", SwingConstants.CENTER);
    private JLabel lblNombreTarea = new JLabel("Nombre de la Tarea:");
    private JTextField txtNombreTarea = new JTextField();
    private JLabel lblDescripcionTarea = new JLabel("Descripción de la Tarea:");
    private JTextArea txtDescripcionTarea = new JTextArea();
    private JLabel lblFechaVencimiento = new JLabel("Fecha de Vencimiento:");
    private JDateChooser fechaVencimientoChooser = new JDateChooser();
    private BotonPersonalizado btnGuardarCambios = new BotonPersonalizado("Guardar Cambios");
    private BotonPersonalizado btnCancelar = new BotonPersonalizado("Cancelar");

    public FrameEditarTarea(Tarea tarea, TareaCard tareaCard) {
        this.card = tareaCard;
        setTitle("Editar Tarea");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Titulo
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 20));
        lblTitulo.setBounds(0, 10, 400, 30);
        add(lblTitulo);

        // Nombre de la Tarea
        lblNombreTarea.setBounds(20, 60, 150, 25);
        add(lblNombreTarea);

        txtNombreTarea.setText(tarea.getNombre());
        txtNombreTarea.setBounds(20, 90, 350, 25);
        add(txtNombreTarea);

        // Descripción de la Tarea
        lblDescripcionTarea.setBounds(20, 130, 150, 25);
        add(lblDescripcionTarea);

        txtDescripcionTarea.setText(tarea.getDescripcion());
        txtDescripcionTarea.setBounds(20, 160, 350, 100);
        add(txtDescripcionTarea);

        // Fecha de Vencimiento
        lblFechaVencimiento.setBounds(20, 280, 150, 25);
        add(lblFechaVencimiento);

        Date fechaVencimiento = Date.from(tarea.getFechaVencimiento().atZone(java.time.ZoneId.systemDefault()).toInstant());

        fechaVencimientoChooser.setDate(fechaVencimiento);
        fechaVencimientoChooser.setBounds(20, 310, 200, 25);
        add(fechaVencimientoChooser);

        // Botones
        btnGuardarCambios.setBounds(50, 370, 130, 30);
        add(btnGuardarCambios);

        btnCancelar.setBounds(220, 370, 130, 30);
        add(btnCancelar);

        // Evento cancelar
        btnCancelar.addActionListener(e -> dispose());

        // Evento guardar cambios
        btnGuardarCambios.addActionListener(e -> {
            // Aquí iría la lógica para guardar los cambios en la tarea
            tarea.setNombre(txtNombreTarea.getText());
            tarea.setDescripcion(txtDescripcionTarea.getText());
            tarea.setFechaVencimiento(fechaVencimientoChooser.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());

            card.actualizarTarea(tarea);

            // Cerrar el frame después de guardar
            System.out.println("Tarea actualizada: " + tarea.getNombre());
            System.out.println("Descripción: " + tarea.getDescripcion());
            System.out.println("Fecha de Vencimiento: " + tarea.getFechaVencimiento());
            dispose();
        });

    }
}
