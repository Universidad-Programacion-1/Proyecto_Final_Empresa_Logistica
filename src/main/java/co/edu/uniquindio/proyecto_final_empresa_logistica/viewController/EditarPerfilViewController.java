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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EditarPerfilViewController {

    UsuarioController usuarioController;
    Usuario usuario;
    private Application app;
    ObservableList<String> direcciones = FXCollections.observableArrayList();
    String selectedDireccion;


    @FXML
    private TableColumn<String, String> tbcIDireccion;

    @FXML
    private TextField txtDireccion;

    @FXML
    private Button btnActualizarPerfil;

    @FXML
    private Button btnAgregarDireccion;

    @FXML
    private TextField txtNombreCompleto;

    @FXML
    private TableView<String> tblListDirecciones;

    @FXML
    private TextField txtNumeroTelefono;

    @FXML
    private TextField txtCorreoElectronico;

    @FXML
    private Button btnEliminaDireccion;

    @FXML
    void onActualizarPerfil() {
        actualizarPerfil();
    }

    @FXML
    void onAgregarDireccion() {
        agregarDireccion();
    }

    @FXML
    void onEliminarDireccion() {
        eliminarDireccion();
    }

    @FXML
    void onOpenMenu() {
        app.openViewLogin();
    }

    public void setApp(Application app) {
        this.app = app;
    }

    @FXML
    void initialize() {
        usuarioController = new UsuarioController(app.empresaLogistica);
        initView();
    }
    private void actualizarPerfil() {
        if (usuarioController.actualizarUsuario(usuario.getId(), buildUsuario())) {
            limpiarCamposUsuario();
            mostrarInfoUsuario();
        }
    }

    private void agregarDireccion() {
        if (usuarioController.agregarDireccion(buildDireccion())) {
            direcciones.add(buildDireccion());
            System.out.println("fff"+direcciones);
            limpiarCamposUsuario();
        }
    }

    private void limpiarSeleccion() {
        tblListDirecciones.getSelectionModel().clearSelection();
        limpiarCamposUsuario();
    }
    private void limpiarCamposUsuario() {
        txtDireccion.clear();

    }
    private Usuario buildUsuario() {
        ArrayList<String> direcion = new ArrayList<>();
        direcion.add(txtDireccion.getText());
        Usuario usuario = new Usuario(null, txtNombreCompleto.getText(), txtCorreoElectronico.getText(),  txtNumeroTelefono.getText(), null);
        return usuario;

    }
    private String buildDireccion() {
        return txtDireccion.getText();
    }

    private void eliminarDireccion() {

        if (usuarioController.eliminarDireccion(selectedDireccion)) {
            direcciones.remove(selectedDireccion);
            limpiarCamposUsuario();
            limpiarSeleccion();

        }
    }

    private void obtenerUsuario() {
        usuario = usuarioController.obtenerUsuario();
    }

    private void obtenerDirecciones() {

        direcciones.addAll(usuario.getDirecciones());
        System.out.println("usuario"+usuario.getDirecciones());
    }

    private void mostrarInfoUsuario() {

        if (usuario != null) {
            txtNombreCompleto.setText(String.valueOf(usuario.getNombre()));
            txtCorreoElectronico.setText(String.valueOf(usuario.getCorreo()));
            txtNumeroTelefono.setText(String.valueOf(usuario.getTelefono()));
            if (selectedDireccion != null) {
                txtDireccion.setText(String.valueOf(selectedDireccion));
            }
        }
    }
    private void initDataBinding() {

        tbcIDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

        //tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
    }

    private void listenerSelection() {
        mostrarInfoUsuario();
        tblListDirecciones.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedDireccion = newSelection;
            mostrarInfoUsuario();
        });
    }
    private void initView() {

        obtenerUsuario();

        // Traer los datos del cliente a la tabla
        initDataBinding();

        // Obtiene la lista
        obtenerDirecciones();

        // Limpiar la tabla
        tblListDirecciones.getItems().clear();

        // Agregar los elementos a la tabla
        tblListDirecciones.setItems(direcciones);

        // Seleccionar elemento de la tabla
        listenerSelection();
    }

}
