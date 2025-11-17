package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;

import co.edu.uniquindio.proyecto_final_empresa_logistica.Application;
import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.RepartidorController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.UsuarioController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Envio;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Repartidor;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Usuario;
import co.edu.uniquindio.proyecto_final_empresa_logistica.utils.TipoEstadoDisponible;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RepartidorViewController {

    RepartidorController repartidorController;
    private Application app;
    ObservableList<Repartidor> repartidores = FXCollections.observableArrayList();
    Repartidor selectedRepartidor;


    @FXML
    private TableColumn<Repartidor, String> tbcEstado;

    @FXML
    private Button btnEliminarRepartidor;

    @FXML
    private TextField txtIdRepartidor;

    @FXML
    private TableColumn<Repartidor, String> tbcIdRepartidor;

    @FXML
    private Button btnAgregarRepartidor;

    @FXML
    private TableView<Repartidor> tblListRepartidor;

    @FXML
    private TableColumn<Repartidor, String> tbcCorreo;

    @FXML
    private TableColumn<Repartidor, String> tbcTelefono;

    @FXML
    private TableColumn<Repartidor, String> tbcZonaCobertura;

    @FXML
    private TextField txtNombreCompleto;

    @FXML
    private TableColumn<Repartidor, String> tbcNombre;

    @FXML
    private TextField txtNumeroTelefono;

    @FXML
    private ComboBox<TipoEstadoDisponible> cbxEstado;

    @FXML
    private TextField txtZonaCobertura;

    @FXML
    private TextField txtCorreoElectronico;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnActualizarRepartidor;



    @FXML
    void onActualizarRepartidor() {
        actualizarRepartidor();
    }

    @FXML
    void onAgregarRepartidor() {
        agregarRepartidor();
    }

    @FXML
    void onEliminarRepartidor() {
        eliminarRepartidor();

    }

    public void setApp(Application app) {this.app = app;}

    @FXML
    void initialize() {
        repartidorController = new RepartidorController(app.empresaLogistica);
        cbxEstado.getItems().addAll(TipoEstadoDisponible.values());
        initView();
    }
    private void actualizarRepartidor() {
        System.out.println("RepartidorViewController.actualizarRepartidor()");
        if (selectedRepartidor != null && repartidorController.actualizarRepartidor(selectedRepartidor.getId(), buildRepartidor())) {

            int index = repartidores.indexOf(selectedRepartidor);
            if (index >= 0) {
                repartidores.set(index, buildRepartidor());
            }

            tblListRepartidor.refresh();
            limpiarSeleccion();
            limpiarCamposRepartidor();
        }
    }

    private void limpiarSeleccion() {
        tblListRepartidor.getSelectionModel().clearSelection();
        limpiarCamposRepartidor();
    }

    private void limpiarCamposRepartidor() {
        System.out.println("RepartidorViewController.limpiarCamposRepartidor()");
        txtNumeroTelefono.clear();
        txtIdRepartidor.clear();
        txtPassword.clear();
        txtCorreoElectronico.clear();
        txtNombreCompleto.clear();
        txtZonaCobertura.clear();
        cbxEstado.setValue(null);
    }

    private Repartidor buildRepartidor() {
        Repartidor repartidor = new Repartidor(txtIdRepartidor.getText(), txtNombreCompleto.getText(), txtCorreoElectronico.getText(),  txtNumeroTelefono.getText(), txtPassword.getText(), cbxEstado.getValue(), txtZonaCobertura.getText());
        return repartidor;
    }

    private void eliminarRepartidor() {
        if (repartidorController.eliminarRepartidor(txtIdRepartidor.getText())) {
            repartidores.remove(selectedRepartidor);
            limpiarCamposRepartidor();
            limpiarSeleccion();
        }
    }

    private void agregarRepartidor() {
        Repartidor repartidor = buildRepartidor();
        if (repartidorController.agregarRepartidor(repartidor)) {
            repartidores.add(repartidor);
            limpiarCamposRepartidor();
        }
    }

    private void obtenerRepartidores() {
        repartidores.addAll(repartidorController.obtenerRepartidores());
        System.out.println("repartidores: " + repartidores);
    }

    private void mostrarInfoRepartidor(Repartidor repartidor) {
        if (repartidor != null) {
            txtIdRepartidor.setText(String.valueOf(repartidor.getId()));
            txtNombreCompleto.setText(String.valueOf(repartidor.getNombre()));
            txtPassword.setText(String.valueOf(repartidor.getPassword()));
            txtCorreoElectronico.setText(String.valueOf(repartidor.getCorreo()));
            txtNumeroTelefono.setText(String.valueOf(repartidor.getTelefono()));
            txtZonaCobertura.setText(String.valueOf(repartidor.getZonaCobertura()));
            cbxEstado.setValue(repartidor.getEstadoDisponible());
        }
    }

    private void initDataBinding() {
        tbcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tbcIdRepartidor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        tbcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        tbcCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
        tbcZonaCobertura.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getZonaCobertura()));
        tbcEstado.setCellValueFactory(cellData -> {
            Repartidor repartidor = cellData.getValue();
            return new SimpleObjectProperty(repartidor != null ? repartidor.getEstadoDisponible() : "");
        });
    }

    private void listenerSelection() {
        tblListRepartidor.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedRepartidor = newSelection;
            mostrarInfoRepartidor(newSelection);
        });
    }

    private void initView() {
        // Traer los datos del cliente a la tabla
        initDataBinding();

        // Obtiene la lista
        obtenerRepartidores();

        // Limpiar la tabla
        tblListRepartidor.getItems().clear();

        // Agregar los elementos a la tabla
        tblListRepartidor.setItems(repartidores);

        // Seleccionar elemento de la tabla
        listenerSelection();
    }

}
