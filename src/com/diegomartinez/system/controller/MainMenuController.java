package com.diegomartinez.system.controller;

import com.diegomartinez.system.utils.ViewFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class MainMenuController implements Initializable {

    @FXML
    private StackPane panelCentral;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void onInicio(MouseEvent event) {
        // Aquí más adelante se carga el formulario de inicio dentro de panelCentral
    }

    @FXML
    public void onPerfil(MouseEvent event) {
        // Aquí más adelante se carga el formulario de perfil dentro de panelCentral
    }

    @FXML
    public void onConfiguracion(MouseEvent event) {
        // Aquí más adelante se carga el formulario de configuración dentro de panelCentral
    }

    @FXML
    public void onCerrarSesion(MouseEvent event) {
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewLogin();
    }
}
