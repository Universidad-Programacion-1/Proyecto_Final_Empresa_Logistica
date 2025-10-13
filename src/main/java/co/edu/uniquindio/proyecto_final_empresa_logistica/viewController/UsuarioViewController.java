package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;

import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UsuarioViewController {

    @FXML
    private TextField txtIdUsuario;

    @FXML
    private TextField txtPasswore;

    @FXML
    private TableColumn<Usuario, String> tbcIdUsuario;

    @FXML
    private TableView<Usuario> tblListUsuarios;

    @FXML
    private TableColumn<Usuario, String> tbcCorreo;

    @FXML
    private TableColumn<Usuario, String> tbcTelefono;

    @FXML
    private Button btnActualizarUsuario;

    @FXML
    private Button dtnAtras;

    @FXML
    private TextField txtNombreCompleto;

    @FXML
    private Button btnAgregarUsuario;

    @FXML
    private TableColumn<Usuario, String> tbcNombre;

    @FXML
    private TextField txtNumeroTelefono;

    @FXML
    private TextField txtCorreoElectronico;

    @FXML
    void onActualizarUsuario() {

    }

    @FXML
    void onAgregarUsuario() {

    }

    @FXML
    void onEliminarUsuario() {

    }
}
