package org.example.view.componentes;

import java.awt.*;

public class BotonCuadrado extends BotonPersonalizado {

    private int lado = 80;

    public BotonCuadrado(String texto) {
        super(texto);

        // Propiedades básicas
        //setPreferredSize(new Dimension(lado, lado));

        setMaximumSize(new Dimension(lado, lado));
        setMinimumSize(new Dimension(lado, lado));

        // Bordes redondeados (opcional)
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, lado, lado);
    }

    public void setLado(int lado) {
        this.lado = lado;
        setMaximumSize(new Dimension(lado, lado));
        setMinimumSize(new Dimension(lado, lado));
        revalidate();
        repaint();
    }
}