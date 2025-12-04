package org.example.util;

import javax.swing.*;

public class DialogUtils {

    public static final String CONFIRM_EXIT = "¿Estás seguro de que deseas salir de la aplicación?";

    public static void mostrarMensaje(String mensaje, TipoMensaje tipo, String titulo) {
        JOptionPane optionPane = new JOptionPane(mensaje);

        switch (tipo) {
            case INFO:
                optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
                break;
            case WARNING:
                optionPane.setMessageType(JOptionPane.WARNING_MESSAGE);
                break;
            case ERROR:
                optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
                break;
            default:
                optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
                break;
        }
        JDialog dialog = optionPane.createDialog(titulo);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    public static boolean confirmarAccion(String accion, String titulo) {
        String mensaje = "¿Estás seguro de que deseas " + accion + "?";

        int resultado = JOptionPane.showConfirmDialog(
                null,
                mensaje,
                titulo,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        return resultado == JOptionPane.YES_OPTION;
    }

}
