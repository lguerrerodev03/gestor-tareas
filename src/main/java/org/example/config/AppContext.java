package org.example.config;

import org.example.controller.TareaController;
import org.example.database.DatabaseInitializer;
import org.example.service.TareaService;
import org.example.view.Dashboard;

public enum AppContext {
    INSTANCE;

    private final TareaService tareaService;
    private final TareaController tareaController;

    AppContext() {
        DatabaseInitializer.inicializar();

        this.tareaService = new TareaService();
        this.tareaController = new TareaController(tareaService);
    }

    public TareaService getTareaService() {
        return tareaService;
    }

    public TareaController getTareaController() {
        return tareaController;
    }
}
