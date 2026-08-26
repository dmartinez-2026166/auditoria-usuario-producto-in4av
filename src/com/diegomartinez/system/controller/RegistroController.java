package com.diegomartinez.system.controller;

import com.diegomartinez.system.utils.AlertInformation;
import com.diegomartinez.system.utils.ViewFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class RegistroController implements Initializable{
    private final String RUTA_ESTILOS = "/com/diegomartinez/system/resources/styles/";
    private AlertInformation alerta = new AlertInformation();
    @FXML
    private VBox contenedor;
    @FXML
    private Hyperlink link;
    @FXML
    private TextField txtNombre;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCSS("RegistroStyles.css");
    }
    
    public void cargarCSS(String nombre) {
        contenedor.getStylesheets().add(RUTA_ESTILOS + nombre);
    }
    
    @FXML
    public void validarRegistro(ActionEvent evento) {
        if(txtNombre.getText().isEmpty()) {
            txtNombre.getStylesheets().add("error-campo");
            alerta.mostrarAlerta("error", "No sabes llenar un campo", "Error de campos vacios");
        } else {
            txtNombre.getStylesheets().remove("error-campo");
            alerta.mostrarAlerta("confirm", "Nitido", "Eres exitoso");
        }
    }
    
    @FXML
    public void retornarLogin() {
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewLogin();
    }
}
