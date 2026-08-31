package com.diegomartinez.system.controller;

import com.diegomartinez.system.service.UserService;
import com.diegomartinez.system.service.UserStatus;
import com.diegomartinez.system.utils.AlertInformation;
import com.diegomartinez.system.utils.Validations;
import com.diegomartinez.system.utils.ViewFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController implements Initializable{

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField pwdContrasena;

    private AlertInformation alertInfo = new AlertInformation();
    private Validations validate = new Validations();
    private UserService userService = new UserService();

    @Override 
    public void initialize(URL url, ResourceBundle rb){
    
    }
    
    @FXML
    public void onRegister(MouseEvent event){
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewRegister();
    }

    @FXML
    public void onIngresar(MouseEvent event) {
        String usuario = txtUsuario.getText().trim();
        String contrasena = pwdContrasena.getText().trim();

        if (validate.validateTextEmpty(usuario) == true || validate.validateTextEmpty(contrasena) == true) {
            alertInfo.viewAlert("ERROR", "ERROR DE CAMPO", "ERROR CAMPOS VACÍOS", "INGRESA USUARIO Y CONTRASEÑA");
            return;
        }

        UserStatus status = userService.login(usuario, contrasena);
        switch (status) {
            case LOGIN_SUCCESS -> {
                ViewFactory viewFacto = new ViewFactory();
                viewFacto.viewMainMenu();
            }
            case LOGIN_ERROR ->
                alertInfo.viewAlert("ERROR", "ERROR DE ACCESO", "CREDENCIALES INCORRECTAS", "EL USUARIO O LA CONTRASEÑA NO SON VÁLIDOS");
            default ->
                alertInfo.viewAlert("ERROR", "ERROR", "ERROR DESCONOCIDO", "OCURRIÓ UN ERROR INESPERADO");
        }
    }
}