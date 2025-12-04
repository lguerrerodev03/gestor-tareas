package org.example.view.panels;

import org.example.config.AppContext;
import org.example.controller.TareaController;
import org.example.model.Tarea;
import org.example.model.enums.AccionTarea;
import org.example.model.enums.EstadoTarea;
import org.example.rules.FlujoTarea;
import org.example.util.DialogUtils;
import org.example.util.TipoMensaje;
import org.example.view.componentes.BotonPersonalizado;
import org.example.view.listeners.TareaSeleccionadaListener;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PanelInformacion extends JPanel implements TareaSeleccionadaListener {
    TareaController tareaController;
    private JLabel lblNombreTarea;
    private JTextArea txtDescripcionTarea;
    private JLabel lblFechaCreacion;
    private JLabel lblFechaVencimiento;
    private JLabel lblEstadoTarea;
    private List<BotonPersonalizado> botonesAccion = new ArrayList<>();;

    public PanelInformacion(int idTarea) {
        tareaController = AppContext.INSTANCE.getTareaController();
        setBounds(570, 110, 300, 550);
        setBorder(BorderFactory.createTitledBorder("Información"));
        setLayout(null);

        Tarea tarea = obtenerTareaPorId(idTarea);

        lblNombreTarea = new JLabel("Nombre: " + tarea.getNombre());
        lblNombreTarea.setBounds(10, 30, 280, 25);
        add(lblNombreTarea);
        txtDescripcionTarea = new JTextArea("Descripción: " + tarea.getDescripcion());
        txtDescripcionTarea.setBounds(10, 70, 280, 150);
        txtDescripcionTarea.setLineWrap(true);
        txtDescripcionTarea.setWrapStyleWord(true);
        add(txtDescripcionTarea);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        lblFechaCreacion = new JLabel("Creación: " + tarea.getFechaCreacion().format(formato));
        lblFechaCreacion.setBounds(10, 230, 280, 25);
        add(lblFechaCreacion);
        lblFechaVencimiento = new JLabel("Vencimiento: " + tarea.getFechaVencimiento().format(formato));
        lblFechaVencimiento.setBounds(10, 270, 280, 25);
        add(lblFechaVencimiento);
        lblEstadoTarea = new JLabel("Estado: " + tarea.getEstado().name());
        lblEstadoTarea.setBounds(10, 310, 280, 25);
        add(lblEstadoTarea);

        // Crear botones según las acciones válidas
        int posX = 20;
        for (AccionTarea accion : FlujoTarea.obtenerAccionesPosibles(tarea.getEstado())) {
            BotonPersonalizado boton = new BotonPersonalizado(accion.name());
            boton.setBounds(posX, 400, 100, 30);
            boton.setFont(new Font("Verdana", Font.PLAIN, 11));
            boton.setColorNormal(new Color(100, 149, 237));
            boton.setColorHover(new Color(70, 130, 180));
            add(boton);
            botonesAccion.add(boton);
            posX += 110;
        }
    }

    private Tarea obtenerTareaPorId(int idTarea) {
        // Aquí iría la lógica para obtener la tarea desde la base de datos o modelo
        // Por ahora, devolvemos una tarea de ejemplo
        return new Tarea(idTarea, "Tarea Ejemplo", "Descripción de la tarea ejemplo",
                EstadoTarea.PENDIENTE, LocalDateTime.now(), LocalDateTime.now().plusDays(7), false, new ArrayList<>());
    }

    public void actualizarInformacion(Tarea tarea) {
        lblNombreTarea.setText("Nombre: " + tarea.getNombre());
        txtDescripcionTarea.setText("Descripción: " + tarea.getDescripcion());

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        lblFechaCreacion.setText("Creación: " + tarea.getFechaCreacion().format(formato));
        lblFechaVencimiento.setText("Vencimiento: " + tarea.getFechaVencimiento().format(formato));
        lblEstadoTarea.setText("Estado: " + tarea.getEstado().name());

        // Eliminar botones anteriores
        for (BotonPersonalizado boton : botonesAccion) {
            remove(boton);
        }
        botonesAccion.clear();

        // Crear nuevos botones según el estado actual
        int posX = 10;
        int posY = 360;
        for (AccionTarea accion : FlujoTarea.obtenerAccionesPosibles(tarea.getEstado())) {
            BotonPersonalizado boton = new BotonPersonalizado(accion.name());
            boton.setBounds(posX, posY, 120, 30);
            boton.setFont(new Font("Verdana", Font.PLAIN, 11));
            boton.setColorNormal(new Color(100, 149, 237));
            boton.setColorHover(new Color(70, 130, 180));
            boton.addActionListener(e -> manejarAccion(accion, tarea)); // Ejemplo de uso
            add(boton);
            botonesAccion.add(boton);
            posX += 130;
        }

        SwingUtilities.invokeLater(() -> {
            revalidate();
            repaint();
        });
    }

    private void manejarAccion(AccionTarea accion, Tarea tarea) {
        System.out.println("Acción seleccionada: " + accion + " para la tarea " + tarea.getNombre());
        boolean confirmado = DialogUtils.confirmarAccion(
                accion.name().toLowerCase(),
                "Confirmar Acción"
        );

        if (confirmado) {
            // Aquí iría la lógica para manejar la acción seleccionada
            // Por ejemplo, actualizar el estado de la tarea en el modelo o base de datos
            System.out.println("Acción confirmada: " + accion);

            boolean actualizado = tareaController.actualizarEstadoTarea(tarea.getId(), accion);

            if (actualizado) {
                Tarea tareaActualizada = tareaController.obtenerTareaPorId(tarea.getId()).orElse(tarea);
                actualizarInformacion(tareaActualizada);
                DialogUtils.mostrarMensaje(
                        "La acción '" + accion.name().toLowerCase() + "' se ha realizado correctamente.",
                        TipoMensaje.INFO,
                        "INFORMATION_MESSAGE"
                );
            } else {
                DialogUtils.mostrarMensaje(
                        "No se pudo realizar la acción '" + accion.name().toLowerCase() + "'.",
                        TipoMensaje.ERROR,
                        "ERROR_MESSAGE"
                );
            }

        } else {
            System.out.println("Acción cancelada: " + accion);
        }

    }

    @Override
    public void onTareaSeleccionada(Tarea tarea) {
        actualizarInformacion(tarea);
    }
}
