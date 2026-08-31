package com.diegomartinez.system.controller;

import com.diegomartinez.system.service.UserService;
import com.diegomartinez.system.service.UserStatus;
import com.diegomartinez.system.utils.AlertInformation;
import com.diegomartinez.system.utils.Validations;
import com.diegomartinez.system.utils.ViewFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class RegisterController implements Initializable {

    @FXML
    private PasswordField pwdConfirmPassword;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtUser;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnCreateUser;
    private Validations validate = new Validations();
    private AlertInformation alertInfo = new AlertInformation();
    private UserService userService = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void onCancelRegister(MouseEvent event) {
        ViewFactory viewFacto = new ViewFactory();
        viewFacto.viewLogin();
    }

    @FXML
    public void OnRegisterUser(MouseEvent event) {
        String email = txtEmail.getText().trim();
        boolean validEmail = validate.validateEmail(email);
        if (validEmail == false) {
            alertInfo.viewAlert("ERROR", "ERROR DE EMAIL", "ERROR CAMPO EMAIL", "INGRESASTE UN EMAIL INCORRECTO");
            return;
        }

        String user, name, lastName, password, confirmPassword;
        name = txtName.getText().trim();
        lastName = txtLastName.getText().trim();
        user = txtUser.getText().trim();
        password = pwdPassword.getText().trim();
        confirmPassword = pwdConfirmPassword.getText().trim();

        if (validate.validateTextEmpty(user) == true
                || validate.validateTextEmpty(name) == true
                || validate.validateTextEmpty(email) == true
                || validate.validateTextEmpty(lastName) == true
                || validate.validateTextEmpty(password) == true
                || validate.validateTextEmpty(confirmPassword) == true) {
            alertInfo.viewAlert("ERROR", "ERROR DE CAMPO", "ERROR CAMPOS VACÍOS", "NO LLENASTE TODOS LOS CAMPOS");
            return;
        }

        String msgfield = "";
        if (validate.validateTextLength(user, 25) == false) {
            msgfield = "El campo USUARIO es mayor a 25 letras";
        }
        if (validate.validateTextLength(name, 50) == false) {
            msgfield = "El campo NOMBRE es mayor a 50 letras";
        }
        if (validate.validateTextLength(lastName, 50) == false) {
            msgfield = "El campo APELLIDO es mayor a 50 letras";
        }
        if (validate.validateTextLength(email, 50) == false) {
            msgfield = "El campo EMAIL es mayor a 50 letras";
        }
        if (validate.validateTextLength(password, 35) == false) {
            msgfield = "El campo CONTRASEÑA es mayor a 35 letras";
        }
        if (validate.validateTextLength(confirmPassword, 35) == false) {
            msgfield = "El campo COMFIRMAR CONTRASEÑA es mayor a 35 letras";
        }
        if (msgfield.isEmpty() == false) {
            alertInfo.viewAlert("ERROR", "ERROR DE CAMPO", "ERROR LONGITUD DE CAMPO", msgfield);
            return;
        }
        
        if(validate.equalsText(password, confirmPassword) == false) {
            alertInfo.viewAlert("ERROR", "ERROR DE CONTRASEÑA", "ERROR AL CONFIRMAR CONTRASEÑA", "LAS CONTRASEÑAS NO COINCIDEN");
            return;
        }
        UserStatus status = userService.createUser(user, name, lastName, email, password);
        switch (status) {
            case USER_CREATED -> {
                alertInfo.viewAlert("INFO", "USUARIO CREADO", "REGISTRO EXITOSO", "EL USUARIO SE CREÓ CORRECTAMENTE");
                ViewFactory viewFacto = new ViewFactory();
                viewFacto.viewLogin();
            }
            case ERROR_USER_CREATE ->
                alertInfo.viewAlert("ERROR", "ERROR AL CREAR", "NO SE PUDO CREAR EL USUARIO", "INTENTA NUEVAMENTE");
            default ->
                alertInfo.viewAlert("ERROR", "ERROR", "ERROR DESCONOCIDO", "OCURRIÓ UN ERROR INESPERADO");
        }
    }
}
