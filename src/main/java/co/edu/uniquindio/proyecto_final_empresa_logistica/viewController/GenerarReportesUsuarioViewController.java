package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;


import co.edu.uniquindio.proyecto_final_empresa_logistica.Application;
import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.EnvioController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.UsuarioController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Envio;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Repartidor;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Usuario;
import co.edu.uniquindio.proyecto_final_empresa_logistica.utils.TipoEstadoEnvio;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GenerarReportesUsuarioViewController {

    EnvioController envioController;
    UsuarioController usuarioController;
    Usuario usuario;
    private Application app;
    ObservableList<Envio> envios = FXCollections.observableArrayList();
    Envio selectedEnvio;

    @FXML
    private TableColumn<Envio, String> tbcId;

    @FXML
    private TableColumn<Envio, Double> tbcPeso;

    @FXML
    private TableColumn<Envio, Double> tbcCosto;

    @FXML
    private ComboBox<String> cbxTipoDocumento;

    @FXML
    private TableView<Envio> tblListEnvios;

    @FXML
    private TableColumn<Envio, String> tbcDestino;

    @FXML
    private TableColumn<Envio, String> tbcDimencion;

    @FXML
    private TableColumn<Envio, String> tbcOrigen;

    @FXML
    private Button btnGenerarReportes;

    @FXML
    private TableColumn<Envio, String> tbcFecha;

    @FXML
    private TableColumn<Envio, String> tbcEstado;

    @FXML
    private TableColumn<Envio, String> tbcFechaEntrega;

    @FXML
    void onGenerarReportes() {
        generarReportes();
    }

    private void initDataBinding() {

        tbcId.setCellValueFactory(cellData -> {
            Envio envio = cellData.getValue();
            return new SimpleStringProperty(envio != null ? envio.getIdEnvio() : "");
        });

        tbcOrigen.setCellValueFactory(cellData -> {
            Envio envio = cellData.getValue();
            return new SimpleStringProperty(envio != null ? envio.getOrigen() : "");
        });

        tbcDestino.setCellValueFactory(cellData -> {
            Envio envio = cellData.getValue();
            return new SimpleStringProperty(envio != null ? envio.getDestino() : "");
        });

        tbcPeso.setCellValueFactory(cellData -> {
            Envio envio = cellData.getValue();
            return new SimpleObjectProperty(envio!= null ? envio.getPeso() : "");
        });

        tbcDimencion.setCellValueFactory(cellData -> {
            Envio envio = cellData.getValue();
            return new SimpleStringProperty(envio != null ? envio.getDimenciones() : "");
        });

        tbcCosto.setCellValueFactory(cellData -> {
            Envio envio = cellData.getValue();
            return new SimpleObjectProperty(envio != null ? envio.getCosto() : "");
        });

        tbcFecha.setCellValueFactory(cellData -> {
            Envio envio = cellData.getValue();
            return new SimpleObjectProperty(envio != null ? envio.getFechaCreacion() : "");
        });

        tbcFechaEntrega.setCellValueFactory(cellData -> {
            Envio envio = cellData.getValue();
            return new SimpleObjectProperty(envio != null ? envio.getFechaEstimadaEntrega() : "");
        });

        tbcEstado.setCellValueFactory(cellData -> {
            Envio envio = cellData.getValue();
            return new SimpleObjectProperty(envio != null ? envio.getTipoEstadoEnvio() : "");
        });
    }

    private void obtenerEnviosUsuario() {
        System.out.println("Entro a obtener envios");
        envios.addAll(envioController.obtenerEnviosUsuario(usuario.getId()));
    }

    private void obtenerUsuario() {
        usuario = usuarioController.obtenerUsuario();
    }

    private void listenerSelection() {
        tblListEnvios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedEnvio = newSelection;
        });
    }

    private void generarReportes() {
        System.out.println("Actualizando estado envio");
        if (usuarioController.generarReporteUsuario(usuario.getId(), cbxTipoDocumento.getSelectionModel().getSelectedItem())) {

        }
    }

    private void initView() {

        obtenerUsuario();

        // Traer los datos del cliente a la tabla
        initDataBinding();

        // Obtiene la lista
        obtenerEnviosUsuario();

        // Limpiar la tabla
        tblListEnvios.getItems().clear();

        // Agregar los elementos a la tabla
        tblListEnvios.setItems(envios);

        // Seleccionar elemento de la tabla
        listenerSelection();
    }

    @FXML
    void initialize() {
        envioController = new EnvioController(app.empresaLogistica);
        usuarioController = new UsuarioController(app.empresaLogistica);
        cbxTipoDocumento.getItems().addAll("PDF", "CSV");
        initView();
    }

}
