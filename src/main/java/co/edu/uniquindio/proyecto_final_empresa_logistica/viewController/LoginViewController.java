package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;

import co.edu.uniquindio.proyecto_final_empresa_logistica.Application;
import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController {

    @FXML
    private ResourceBundle resources;

    Application app;

    LoginController loginController;

    @FXML
    private URL location;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private Label txtTitleLogin;

    @FXML
    private TextField txtContrasena;

    @FXML
    private Label txtOlvidoContrasena;

    @FXML
    private TextField txtCorreo;

    @FXML
    void onIniciarSesion() throws IOException {
        redirect();
    }

    @FXML
    void onOlvidoContrasena() {

        //app.openViewOlvidoContrasena();
    }

    public void setApp(Application app) {
        this.app = app;
    }

    @FXML
    void initialize() {

        loginController = new LoginController(app.empresaLogistica);
    }

    private void redirect() throws IOException {
        System.out.println("Entro a redirec "+ txtCorreo.getText()+ txtContrasena.getText());
          int tipo = loginController.login(txtCorreo.getText(), txtContrasena.getText());
//        System.out.println("Tipo persona "+ tipo);
//
        if (tipo == 1) {
            app.openMenuAdministrador();
        }else if (tipo == 2) {
            app.openMenuUsuario();
        }else if (tipo == 3) {
            app.openMenuRepartidor();
        }
    }
}
