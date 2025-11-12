package org.example;

import org.example.view.Dashboard;
import org.example.view.Login;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
//        Login login = new Login();
//        login.setVisible(true);
//        login.setLocationRelativeTo(null);

        Dashboard dashboard = new Dashboard();
        dashboard.setVisible(true);
        dashboard.setLocationRelativeTo(null);
    }
}