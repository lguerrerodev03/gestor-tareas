package org.example.database;

import org.example.repository.HistorialTareaRepository;
import org.example.repository.TareaRepository;

public class DatabaseInitializer {

    public static void inicializar() {
        TareaRepository.crearTabla();
        HistorialTareaRepository.crearTablaHistorial();
        System.out.println("Base de datos inicializada.");
    }
}
