package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;


import co.edu.uniquindio.proyecto_final_empresa_logistica.Application;
import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.EnvioController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.RepartidorController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Envio;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Repartidor;
import co.edu.uniquindio.proyecto_final_empresa_logistica.utils.TipoEstadoDisponible;
import co.edu.uniquindio.proyecto_final_empresa_logistica.utils.TipoEstadoEnvio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.*;

public class PaquetesAdministradorViewController {

    EnvioController envioController;
    RepartidorController repartidorController;

    ObservableList<Envio> envios = FXCollections.observableArrayList();
    Envio selectedEnvio;


    @FXML private TableView<Envio> tblListEnvios;
    @FXML private TableColumn<Envio, String> tbcIdEnvio;
    @FXML private TableColumn<Envio, String> tbcDestino;
    @FXML private TableColumn<Envio, String> tbcEstado;
    @FXML private TableColumn<Envio, String> tbcDescripcion;
    @FXML private TableColumn<Envio, String> tbcFecha;
    @FXML private TableColumn<Envio, String> tbcRepartidor;


    @FXML private ComboBox<TipoEstadoEnvio> cbxEstadoEnvio;
    @FXML private ComboBox<Repartidor> cbxRepartidores;
    @FXML private Button btnActualizarEstado;

    @FXML
    void onActualizarEstado() {
        actualizarEstadoEnvio();
    }

    private void initDataBinding() {

        tbcIdEnvio.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getIdEnvio()));

        tbcDestino.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDestino()));

        tbcDescripcion.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDimenciones()));


        tbcFecha.setCellValueFactory(cellData -> {
            Envio envio = cellData.getValue();
            return new SimpleStringProperty(envio.getFechaEstimadaEntrega() != null
                    ? envio.getFechaEstimadaEntrega().toString()
                    : "Pendiente");
        });


        tbcEstado.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTipoEstadoEnvio().toString()));


        tbcRepartidor.setCellValueFactory(cellData -> {
            Envio envio = cellData.getValue();
            return new SimpleStringProperty(envio.getRepartidor() != null
                    ? envio.getRepartidor().getNombre()
                    : "Sin Asignar");
        });
    }

    private void obtenerEnviosRepartidor() {
        System.out.println("Entro a obtener envios");
        //envios.addAll(envioController.obtenerEnviosRepartidor(repartidor.getId()));
    }

    private void obtenerEnviosAdmin() {
        envios.clear();
        if (envioController.obtenerEnvios() != null) {
            envios.addAll(envioController.obtenerEnvios());
        }
        tblListEnvios.setItems(envios);
    }

    private void listenerSelection() {
        tblListEnvios.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            selectedEnvio = newVal;
            if (selectedEnvio != null) {



                cbxEstadoEnvio.setValue(selectedEnvio.getTipoEstadoEnvio());

            }
        });
    }

    private void actualizarEstadoEnvio() {
        if (selectedEnvio != null) {
            boolean huboCambios = false;


            if (cbxRepartidores.getValue() != null) {
                Repartidor repartidorElegido = cbxRepartidores.getValue();

                selectedEnvio.builder().repartidor(repartidorElegido);
                repartidorElegido.setEstadoDisponible(TipoEstadoDisponible.En_Ruta);
                repartidorElegido.setEstadoDisponible(TipoEstadoDisponible.Inactivo);


                if(selectedEnvio.getTipoEstadoEnvio() == TipoEstadoEnvio.Solicitado){
                    selectedEnvio.setTipoEstadoEnvio(TipoEstadoEnvio.Asignado);
                    cbxEstadoEnvio.setValue(TipoEstadoEnvio.Asignado);
                }
                huboCambios = true;
            }


            if (cbxEstadoEnvio.getValue() != null) {

                if (cbxEstadoEnvio.getValue() == TipoEstadoEnvio.Entregado && selectedEnvio.getRepartidor() != null) {
                    selectedEnvio.getRepartidor().setEstadoDisponible(TipoEstadoDisponible.disponible);
                    selectedEnvio.getRepartidor().setEstadoDisponible(TipoEstadoDisponible.Activo);
                }

                selectedEnvio.setTipoEstadoEnvio(cbxEstadoEnvio.getValue());
                huboCambios = true;
            }

            if (huboCambios) {

                tblListEnvios.refresh();
                cargarCombos();
                limpiarSeleccion();
                mostrarMensaje("Éxito", "Envío actualizado correctamente.");
            }
        } else {
            mostrarMensaje("Error", "Selecciona un envío primero.");
        }
    }

    private void cargarCombos() {
        cbxEstadoEnvio.setItems(FXCollections.observableArrayList(TipoEstadoEnvio.values()));

        if (envioController.obtenerRepartidoresDisponibles() != null) {
            cbxRepartidores.setItems(FXCollections.observableArrayList(envioController.obtenerRepartidoresDisponibles()));
        }
    }

    private void limpiarSeleccion() {
        tblListEnvios.getSelectionModel().clearSelection();
        selectedEnvio = null;
        cbxEstadoEnvio.setValue(null);
        cbxRepartidores.setValue(null);
    }

    @FXML
    void initialize() {

        envioController = new EnvioController(Application.empresaLogistica);
        repartidorController = new RepartidorController(Application.empresaLogistica);

        initDataBinding();
        obtenerEnviosAdmin();
        cargarCombos();
        listenerSelection();
    }

    private void mostrarMensaje(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}