package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;


import co.edu.uniquindio.proyecto_final_empresa_logistica.Application;
import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.EnvioController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.decorator.*;
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

import java.time.LocalDate;

public class PaquetesRepartidorViewController {

    EnvioController envioController;
    Repartidor repartidor;
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
    private ComboBox<TipoEstadoEnvio> cbxEstadoEnvio;

    @FXML
    private TableView<Envio> tblListEnvios;

    @FXML
    private TableColumn<Envio, String> tbcDestino;

    @FXML
    private TableColumn<Envio, String> tbcDimencion;

    @FXML
    private TableColumn<Envio, String> tbcOrigen;

    @FXML
    private Button btnActualizarEstado;

    @FXML
    private TableColumn<Envio, String> tbcFecha;

    @FXML
    private TableColumn<Envio, String> tbcEstado;

    @FXML
    private TableColumn<Envio, String> tbcFechaEntrega;

    @FXML
    void onActualizarEstado() {
        actualizarEstadoEnvio();
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

    private void obtenerEnviosRepartidor() {
        System.out.println("Entro a obtener envios");
        envios.addAll(envioController.obtenerEnviosRepartidor(repartidor.getId()));
    }

    private void obtenerRepartidor() {
        repartidor = envioController.obtenerRepartidor();
    }

    private void listenerSelection() {
        tblListEnvios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedEnvio = newSelection;
            mostrarInfoEnvio(newSelection);
        });
    }

    private void mostrarInfoEnvio(Envio envio) {
        if (envio != null) {
            if (selectedEnvio != null) {
                cbxEstadoEnvio.setValue(envio.getTipoEstadoEnvio());
            }
        }
    }

    private void actualizarEstadoEnvio() {
        System.out.println("Actualizando estado envio");
        if (selectedEnvio != null && envioController.actualizarEstadoEnvio(selectedEnvio.getIdEnvio(), cbxEstadoEnvio.getValue())) {
            int index = envios.indexOf(selectedEnvio);
            if (index >= 0) {
                envios.set(index, selectedEnvio);
            }
            tblListEnvios.refresh();
            limpiarSeleccion();
            limpiarCampo();
        }
    }

    private void limpiarSeleccion() {
        tblListEnvios.getSelectionModel().clearSelection();
        limpiarCampo();
    }

    private void initView() {

        obtenerRepartidor();

        // Traer los datos del cliente a la tabla
        initDataBinding();

        // Obtiene la lista
        obtenerEnviosRepartidor();

        // Limpiar la tabla
        tblListEnvios.getItems().clear();

        // Agregar los elementos a la tabla
        tblListEnvios.setItems(envios);

        // Seleccionar elemento de la tabla
        listenerSelection();
    }


    private void limpiarCampo() {
        cbxEstadoEnvio.setValue(null);
    }

    @FXML
    void initialize() {
        envioController = new EnvioController(app.empresaLogistica);
        cbxEstadoEnvio.getItems().addAll(
            TipoEstadoEnvio.En_Ruta,
            TipoEstadoEnvio.Entregado,
            TipoEstadoEnvio.Incidencia
        );
        initView();
    }

}
