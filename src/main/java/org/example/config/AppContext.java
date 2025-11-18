package org.example.config;

import org.example.controller.TareaController;
import org.example.service.TareaService;
import org.example.view.Dashboard;

public enum AppContext {
    INSTANCE;

    private final Dashboard dashboard;
    private final TareaService tareaService;
    private final TareaController tareaController;

    AppContext() {
        this.dashboard = new Dashboard();
        this.tareaService = new TareaService();
        this.tareaController = new TareaController();
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public TareaService getTareaService() {
        return tareaService;
    }

    public TareaController getTareaController() {
        return tareaController;
    }
}
