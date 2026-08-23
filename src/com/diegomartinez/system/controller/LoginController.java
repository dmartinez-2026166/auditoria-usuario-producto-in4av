package com.diegomartinez.system.controller;

import com.diegomartinez.system.utils.ViewFactory;
import com.diegomartinez.system.utils.AlertInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class LoginController {
    private final ViewFactory viewFactory = new ViewFactory();
    private AlertInformation alerta = new AlertInformation();
    
    @FXML
    private TextField txtUsuario;
    
    @FXML
    private Hyperlink lnkRegistro;
    
    @FXML
    private void onRegistroClick() {
        viewFactory.viewRegistro();
    }
    
    @FXML
    public void validarLogin(ActionEvent evento) {
        if(txtUsuario.getText().isEmpty()) {
            txtUsuario.getStyleClass().add("error-campo");
            alerta.mostrarAlerta("error", "No sabes llenar un campo", "Error de campos vacios");
        } else {
            txtUsuario.getStyleClass().remove("error-campo");
            alerta.mostrarAlerta("confirm", "Nitido", "Eres exitoso");
        }
    }
}