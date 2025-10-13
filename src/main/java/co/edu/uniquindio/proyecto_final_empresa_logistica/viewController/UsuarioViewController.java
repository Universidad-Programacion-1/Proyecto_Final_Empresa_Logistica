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

public class UsuarioViewController {

    UsuarioController usuarioController;
    private Application application;
    ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
    Usuario selectedUsuario;


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
        actualizarUsuario();
    }

    @FXML
    void onAgregarUsuario() {
        agregarUsuario();

    }

    @FXML
    void onEliminarUsuario() {
        eliminarUsuario();

    }
    @FXML
    void onOpenMenu() {
        application.openViewLogin();
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    @FXML
    void initialize() {
        usuarioController = new UsuarioController(application.empresaLogistica);
        initView();
    }
    private void actualizarUsuario() {

        if (selectedUsuario != null && usuarioController.actualizarUsuario(selectedUsuario.getId(), buildUsuario())) {

            int index = usuarios.indexOf(selectedUsuario);
            System.out.println("fff"+usuarios);
            if (index >= 0) {
                usuarios.set(index, buildUsuario());
            }

            tblListUsuarios.refresh();
            limpiarSeleccion();
            limpiarCamposUsuario();
        }
    }
    private void limpiarSeleccion() {
        tblListUsuarios.getSelectionModel().clearSelection();
        limpiarCamposUsuario();
    }
    private void limpiarCamposUsuario() {
        txtNumeroTelefono.clear();
        txtIdUsuario.clear();
        txtPasswore.clear();
        txtCorreoElectronico.clear();
        txtNombreCompleto.clear();

    }
    private Usuario buildUsuario() {

        Usuario usuario = new Usuario(txtIdUsuario.getText(), txtNombreCompleto.getText(), txtCorreoElectronico.getText(),  txtNumeroTelefono.getText(), txtPasswore.getText());
        return usuario;

    }
    private void eliminarUsuario() {
        if (usuarioController.eliminarUsuario(txtIdUsuario.getText())) {
            usuarios.remove(selectedUsuario);
            limpiarCamposUsuario();
            limpiarSeleccion();

        }
    }
    private void agregarUsuario() {
        Usuario usuario = buildUsuario();
        System.out.println(usuario);
        if (usuarioController.agregarUsuario(usuario)) {
            usuarios.add(usuario);
            limpiarCamposUsuario();
        }
    }
    private void obtenerUsuario() {
        usuarios.addAll(usuarioController.obtenerUsuario());
    }
    private void mostrarInfoUsuario(Usuario usuario) {
        if (usuario != null) {


            txtIdUsuario.setText(String.valueOf(usuario.getId()));
            txtNombreCompleto.setText(String.valueOf(usuario.getNombre()));
            txtPasswore.setText(String.valueOf(usuario.getPassword()));
            txtCorreoElectronico.setText(String.valueOf(usuario.getCorreo()));
            txtNumeroTelefono.setText(String.valueOf(usuario.getTelefono()));
        }
    }
    private void initDataBinding() {


        tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbcIdUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        tbcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        tbcCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
        //Password
    }

    private void listenerSelection() {
        tblListUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedUsuario = newSelection;
            mostrarInfoUsuario(newSelection);
        });
    }
    private void initView() {
        // Traer los datos del cliente a la tabla
        initDataBinding();

        // Obtiene la lista
        obtenerUsuario();

        // Limpiar la tabla
        tblListUsuarios.getItems().clear();

        // Agregar los elementos a la tabla
        tblListUsuarios.setItems(usuarios);

        // Seleccionar elemento de la tabla
        listenerSelection();
    }

}
