package com.diegomartinez.system.controller;

import com.diegomartinez.system.utils.ViewFactory;
import com.diegomartinez.system.utils.AlertInformation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController implements Initializable{
    private final ViewFactory viewFactory = new ViewFactory();
    private AlertInformation alerta = new AlertInformation();
    
    @FXML
    private TextField txtUsuario;
    
    @FXML
    private Hyperlink lnkRegistro;
    
    @FXML
    private void onRegister(MouseEvent event) {
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewRegistro();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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