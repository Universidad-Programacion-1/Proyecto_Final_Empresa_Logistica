package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;

import co.edu.uniquindio.proyecto_final_empresa_logistica.Application;
import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.LoginController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.EnviarCorreo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Random;
import java.util.ResourceBundle;

public class OlvidoContrasenaViewController {

    @FXML
    private ResourceBundle resources;
    Application app;
    LoginController loginController;

    @FXML
    private Label txtVolver;

    @FXML
    private Button btnEnviarCorreo;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtCorreo;

    @FXML
    void onEnviarCorreo() {
        enviarCorreo();
    }

    @FXML
    void onVolver() {app.openViewLogin();}

    public void setApp(Application app) {
        this.app = app;
    }


    @FXML
    void initialize() {
        loginController = new LoginController(app.empresaLogistica);
    }

    private void limpiarCampos() {
        txtTelefono.clear();
        txtCorreo.clear();
    }

    private void enviarCorreo(){
        EnviarCorreo envio = new EnviarCorreo("ruizcarlosandre1@gmail.com", "kpnquvrdvnknrmwf");
        Random random = new Random();
        if (loginController.validarCorreoTelefono(txtCorreo.getText(), txtTelefono.getText())){

            int codigo = 100 + random.nextInt(900);

            System.out.println("Código aleatorio: " + codigo);

            envio.enviarCorreo(txtCorreo.getText(), "Correo de Recuperacion de Contraseña", "Esta es la contraseña temporal cuando ingreses, por favor, modifícala:  "+codigo);
            String password = String.valueOf(codigo);
            loginController.contrasenaTemporal(password,txtCorreo.getText());
            limpiarCampos();

        }
    }
}
