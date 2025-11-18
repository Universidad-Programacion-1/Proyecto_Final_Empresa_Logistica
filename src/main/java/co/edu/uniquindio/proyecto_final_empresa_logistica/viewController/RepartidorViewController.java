package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;

import co.edu.uniquindio.proyecto_final_empresa_logistica.Application;
import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.RepartidorController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Repartidor;
import co.edu.uniquindio.proyecto_final_empresa_logistica.utils.TipoEstadoDisponible;
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

    @FXML private TableView<Repartidor> tblListRepartidor;
    @FXML private TableColumn<Repartidor, String> tbcNombre;
    @FXML private TableColumn<Repartidor, String> tbcIdRepartidor;
    @FXML private TableColumn<Repartidor, String> tbcTelefono;
    @FXML private TableColumn<Repartidor, String> tbcCorreo;
    @FXML private TableColumn<Repartidor, String> tbcZonaCobertura;
    @FXML private TableColumn<Repartidor, String> tbcEstado;

    @FXML private TextField txtNombreCompleto;
    @FXML private TextField txtIdRepartidor;
    @FXML private TextField txtNumeroTelefono;
    @FXML private TextField txtCorreoElectronico;
    @FXML private TextField txtPassword;
    @FXML private TextField txtZonaCobertura;

    // CAMBIO: Usamos el Enum directamente en el ComboBox
    @FXML private ComboBox<TipoEstadoDisponible> cbxEstado;

    @FXML private Button btnAgregarRepartidor;
    @FXML private Button btnActualizarRepartidor;
    @FXML private Button btnEliminarRepartidor;

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
        if(app != null) {
            repartidorController = new RepartidorController(app.empresaLogistica);
        }

        // CAMBIO: Llenamos el combo con los valores del Enum
        cbxEstado.getItems().addAll(TipoEstadoDisponible.values());

        initView();
    }

    private void actualizarRepartidor() {
        if (selectedRepartidor != null) {
            Repartidor repartidorActualizado = buildRepartidor();

            if(repartidorController.actualizarRepartidor(selectedRepartidor.getId(), repartidorActualizado)){
                int index = repartidores.indexOf(selectedRepartidor);
                if (index >= 0) {
                    repartidores.set(index, repartidorActualizado);
                }
                tblListRepartidor.refresh();
                limpiarSeleccion();
                limpiarCamposRepartidor();
            }
        }
    }

    private void limpiarSeleccion() {
        tblListRepartidor.getSelectionModel().clearSelection();
        selectedRepartidor = null;
        limpiarCamposRepartidor();
    }

    private void limpiarCamposRepartidor() {
        txtNumeroTelefono.clear();
        txtIdRepartidor.clear();
        txtPassword.clear();
        txtCorreoElectronico.clear();
        txtNombreCompleto.clear();
        txtZonaCobertura.clear();
        cbxEstado.setValue(null);
    }

    private Repartidor buildRepartidor() {

        return new Repartidor(
                txtIdRepartidor.getText(),
                txtNombreCompleto.getText(),
                txtCorreoElectronico.getText(),
                txtNumeroTelefono.getText(),
                txtPassword.getText(),
                cbxEstado.getValue(),
                txtZonaCobertura.getText()
        );
    }

    private void eliminarRepartidor() {
        if (selectedRepartidor != null) {
            if (repartidorController.eliminarRepartidor(selectedRepartidor.getId())) {
                repartidores.remove(selectedRepartidor);
                limpiarCamposRepartidor();
                limpiarSeleccion();
            }
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
        if(repartidorController != null) {
            repartidores.clear();
            repartidores.addAll(repartidorController.obtenerRepartidores());
        }
    }

    private void mostrarInfoRepartidor(Repartidor repartidor) {
        if (repartidor != null) {
            txtIdRepartidor.setText(repartidor.getId());
            txtNombreCompleto.setText(repartidor.getNombre());
            txtPassword.setText(repartidor.getPassword());
            txtCorreoElectronico.setText(repartidor.getCorreo());
            txtNumeroTelefono.setText(repartidor.getTelefono());
            txtZonaCobertura.setText(repartidor.getZonaCobertura());
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
            return new SimpleStringProperty(
                    (repartidor != null && repartidor.getEstadoDisponible() != null)
                            ? repartidor.getEstadoDisponible().toString()
                            : ""
            );
        });
    }

    private void listenerSelection() {
        tblListRepartidor.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedRepartidor = newSelection;
            mostrarInfoRepartidor(newSelection);
        });
    }

    private void initView() {
        initDataBinding();
        obtenerRepartidores();
        tblListRepartidor.setItems(repartidores);
        listenerSelection();
    }
}