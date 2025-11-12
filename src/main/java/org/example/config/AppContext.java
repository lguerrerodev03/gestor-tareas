package org.example.config;

import org.example.service.TareaService;
import org.example.view.Dashboard;

public enum AppContext {
    INSTANCE;

    private final Dashboard dashboard;
    private final TareaService tareaService;

    AppContext() {
        this.dashboard = new Dashboard();
        this.tareaService = new TareaService();
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public TareaService getTareaService() {
        return tareaService;
    }
}
