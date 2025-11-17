package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;

import co.edu.uniquindio.proyecto_final_empresa_logistica.Application;
import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.LoginController;
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
    private Label txtLogin;

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

    @FXML
    void onLogin() throws IOException {
        this.app.openRegistroUsuario();
    }

    public void setApp(Application app) {
        System.out.println("setApp"+app);
        this.app = app;
    }


    @FXML
    void initialize() {

        loginController = new LoginController(app.empresaLogistica);
    }

    private void redirect() throws IOException {
        System.out.println("Entro a redirec "+ txtCorreo.getText()+ txtContrasena.getText());
        int tipo = this.loginController.login(this.txtCorreo.getText(), this.txtContrasena.getText());
        System.out.println("Tipo persona " + tipo);
        this.app.newVista(tipo);
    }
}
