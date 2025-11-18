package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;

import co.edu.uniquindio.proyecto_final_empresa_logistica.Application;
import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.EnvioController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Envio;
import co.edu.uniquindio.proyecto_final_empresa_logistica.utils.TipoEstadoEnvio;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PaquetesRepartidorViewController {

    EnvioController envioController;
    private Application app;
    ObservableList<Envio> envios = FXCollections.observableArrayList();
    Envio selectedEnvio;


    @FXML
    private TableView<Envio> tblListEnvios;

    @FXML
    private TableColumn<Envio, String> tbcIdEnvio; // Antes era tbcId

    @FXML
    private TableColumn<Envio, String> tbcDestino;

    @FXML
    private TableColumn<Envio, String> tbcEstado;

    @FXML
    private TableColumn<Envio, String> tbcDescripcion; // Antes era tbcDimencion

    // --- CAMPOS DE GESTIÓN ---
    @FXML
    private ComboBox<TipoEstadoEnvio> cbxEstadoEnvio;

    @FXML
    private Button btnActualizarEstado;


    @FXML
    void onActualizarEstado() {
        actualizarEstadoEnvio();
    }

    private void initDataBinding() {
        // ID
        tbcIdEnvio.setCellValueFactory(cellData -> {
            Envio envio = cellData.getValue();
            return new SimpleStringProperty(envio != null ? envio.getIdEnvio() : "");
        });

        // DESTINO
        tbcDestino.setCellValueFactory(cellData -> {
            Envio envio = cellData.getValue();
            return new SimpleStringProperty(envio != null ? envio.getDestino() : "");
        });

        // DESCRIPCIÓN / DIMENSIONES
        tbcDescripcion.setCellValueFactory(cellData -> {
            Envio envio = cellData.getValue();
            return new SimpleStringProperty(envio != null ? envio.getDimenciones() : "");
        });

        // ESTADO (Convertimos el Enum a String)
        tbcEstado.setCellValueFactory(cellData -> {
            Envio envio = cellData.getValue();
            return new SimpleStringProperty(envio != null && envio.getTipoEstadoEnvio() != null
                    ? envio.getTipoEstadoEnvio().toString()
                    : "");
        });
    }

    // Método adaptado para el ADMINISTRADOR (Trae todo)
    private void obtenerEnviosAdmin() {
        System.out.println("Obteniendo todos los envíos para el Admin...");
        if (envioController.obtenerEnvios() != null) {
            envios.addAll(envioController.obtenerEnvios());
        }
    }

    private void listenerSelection() {
        tblListEnvios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedEnvio = newSelection;
            mostrarInfoEnvio(newSelection);
        });
    }

    private void mostrarInfoEnvio(Envio envio) {
        if (envio != null) {
            cbxEstadoEnvio.setValue(envio.getTipoEstadoEnvio());
        }
    }

    private void actualizarEstadoEnvio() {
        System.out.println("Actualizando estado envio...");

        if (selectedEnvio != null && cbxEstadoEnvio.getValue() != null) {

            // 1. Actualizar el objeto en memoria
            selectedEnvio.setTipoEstadoEnvio(cbxEstadoEnvio.getValue());

            // 3. Refrescar la tabla visualmente
            tblListEnvios.refresh();

            mostrarMensaje("Éxito", "Estado actualizado correctamente.");
            limpiarSeleccion();
        } else {
            mostrarMensaje("Error", "Seleccione un envío y un estado.");
        }
    }


    private void limpiarSeleccion() {
        tblListEnvios.getSelectionModel().clearSelection();
        selectedEnvio = null;
        // cbxEstadoEnvio.setValue(null); // Opcional
    }

    private void initView() {
        // Traer los datos del cliente a la tabla
        initDataBinding();

        // Obtiene la lista (MODO ADMIN)
        obtenerEnviosAdmin();

        // Limpiar la tabla
        tblListEnvios.getItems().clear();

        // Agregar los elementos a la tabla
        tblListEnvios.setItems(envios);

        // Seleccionar elemento de la tabla
        listenerSelection();
    }

    @FXML
    void initialize() {
        // Inicializamos el controlador
        envioController = new EnvioController(Application.empresaLogistica);

        // Llenamos el combo con los estados disponibles
        cbxEstadoEnvio.setItems(FXCollections.observableArrayList(TipoEstadoEnvio.values()));

        initView();
    }

    private void mostrarMensaje(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

}