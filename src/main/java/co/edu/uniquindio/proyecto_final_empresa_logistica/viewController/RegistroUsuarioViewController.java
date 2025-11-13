package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;

import co.edu.uniquindio.proyecto_final_empresa_logistica.Application;
import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.UsuarioController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RegistroUsuarioViewController {

    UsuarioController usuarioController;
    private Application app;

    @FXML
    private TextField txtIdUsuario;

    @FXML
    private Button btnRegistrarUsuario;

    @FXML
    private TextField txtNombreCompleto;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtNumeroTelefono;

    @FXML
    private TextField txtCorreoElectronico;

    @FXML
    void onAgregarUsuario() {
        agregarUsuario();
    }

    @FXML
    void onVolver() {
        app.openViewLogin();
    }

    public void setApp(Application app) {
        this.app = app;
    }

    @FXML
    void initialize() {
        usuarioController = new UsuarioController(app.empresaLogistica);
    }

    private void agregarUsuario() {
        Usuario usuario = buildUsuario();
        System.out.println(usuario);
        if (usuarioController.agregarUsuario(usuario)) {
            onVolver();
        }
    }

    private Usuario buildUsuario() {
        Usuario usuario = new Usuario(txtIdUsuario.getText(), txtNombreCompleto.getText(), txtCorreoElectronico.getText(),  txtNumeroTelefono.getText(), txtPassword.getText());
        return usuario;
    }


}
