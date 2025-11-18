package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;

import co.edu.uniquindio.proyecto_final_empresa_logistica.Application;
import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.EnvioController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Envio;
import co.edu.uniquindio.proyecto_final_empresa_logistica.utils.TipoEstadoEnvio;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public class HistorialEnviosViewController {

    EnvioController envioController;
    private Application application;

    private ObservableList<Envio> enviosObservableList = FXCollections.observableArrayList();
    private FilteredList<Envio> filteredData;

    @FXML
    private TableView<Envio> tblEnvios;
    @FXML
    private TableColumn<Envio, String> colId;
    @FXML
    private TableColumn<Envio, String> colOrigen;
    @FXML
    private TableColumn<Envio, String> colDestino;
    @FXML
    private TableColumn<Envio, Double> colCosto;
    @FXML
    private TableColumn<Envio, String> colEstado;

    @FXML
    private ComboBox<String> cmbEstadoFiltro;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaFin;
    @FXML
    private Button btnFiltrar;
    @FXML
    private Button btnLimpiarFiltros;

    @FXML
    private Button btnRastrear;
    @FXML
    private Button btnPagar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnRefrescar;

    @FXML
    void initialize() {
        envioController = new EnvioController(Application.empresaLogistica);

        configurarColumnas();
        configurarFiltros();

        filteredData = new FilteredList<>(enviosObservableList, p -> true);
        tblEnvios.setItems(filteredData);

        configurarListenersBotones();
        onRefrescar();
    }

    private void configurarColumnas() {
        this.colId.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getIdEnvio())
        );
        this.colOrigen.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getOrigen())
        );
        this.colDestino.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDestino())
        );
        this.colCosto.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getCosto())
        );
        this.colEstado.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTipoEstadoEnvio().name())
        );
    }

    private void configurarFiltros() {
        cmbEstadoFiltro.getItems().add("Todos");
        for (TipoEstadoEnvio estado : TipoEstadoEnvio.values()) {
            cmbEstadoFiltro.getItems().add(estado.name());
        }
        cmbEstadoFiltro.setValue("Todos");
    }

    private void configurarListenersBotones() {
        btnRastrear.setDisable(true);
        btnPagar.setDisable(true);
        btnModificar.setDisable(true);
        btnCancelar.setDisable(true);

        tblEnvios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            boolean isSelected = (newSelection != null);
            boolean isPending = (isSelected && newSelection.getTipoEstadoEnvio() == TipoEstadoEnvio.Pendiente_Pago);

            btnRastrear.setDisable(!isSelected);
            btnPagar.setDisable(!isPending);
            btnModificar.setDisable(!isPending);
            btnCancelar.setDisable(!isPending);
        });
    }

    public void setApp(Application application) {
        this.application = application;
    }

    @FXML
    void onRefrescar() {
        enviosObservableList.clear();
        Collection<Envio> envios = envioController.obtenerEnvios();
        enviosObservableList.setAll(envios);
        tblEnvios.refresh();
        onLimpiarFiltros();
    }

    @FXML
    void onFiltrar() {
        filteredData.setPredicate(envio -> {
            boolean estadoCoincide = true;
            boolean fechaCoincide = true;

            String estadoFiltro = cmbEstadoFiltro.getValue();
            if (estadoFiltro != null && !estadoFiltro.equals("Todos")) {
                estadoCoincide = envio.getTipoEstadoEnvio().name().equals(estadoFiltro);
            }

            LocalDate fechaInicio = dpFechaInicio.getValue();
            LocalDate fechaFin = dpFechaFin.getValue();
            LocalDate fechaEnvio = envio.getFechaCreacion();

            if (fechaInicio != null && fechaFin != null) {
                fechaCoincide = !fechaEnvio.isBefore(fechaInicio) && !fechaEnvio.isAfter(fechaFin);
            } else if (fechaInicio != null) {
                fechaCoincide = !fechaEnvio.isBefore(fechaInicio);
            } else if (fechaFin != null) {
                fechaCoincide = !fechaEnvio.isAfter(fechaFin);
            }

            return estadoCoincide && fechaCoincide;
        });
    }

    @FXML
    void onLimpiarFiltros() {
        cmbEstadoFiltro.setValue("Todos");
        dpFechaInicio.setValue(null);
        dpFechaFin.setValue(null);
        filteredData.setPredicate(p -> true);
    }

    @FXML
    void onCancelar() {
        Envio envioSeleccionado = tblEnvios.getSelectionModel().getSelectedItem();
        if (envioSeleccionado == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Cancelación");
        alert.setHeaderText("¿Está seguro de que desea cancelar el envío " + envioSeleccionado.getIdEnvio() + "?");
        alert.setContentText("Esta acción no se puede deshacer.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean cancelado = envioController.actualizarEstadoEnvio(envioSeleccionado.getIdEnvio(), TipoEstadoEnvio.Cancelado);
            if (cancelado) {
                mostrarMensaje("Éxito", "Envío Cancelado", "El envío ha sido cancelado.", Alert.AlertType.INFORMATION);
                onRefrescar();
            } else {
                mostrarMensaje("Error", "Error", "No se pudo cancelar el envío.", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    void onModificar() {
        Envio envioSeleccionado = tblEnvios.getSelectionModel().getSelectedItem();
        if (envioSeleccionado == null) return;

        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("ModificarEnvio.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage modalStage = new Stage();
            modalStage.setTitle("Modificar Envío");
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(btnModificar.getScene().getWindow());
            Scene scene = new Scene(page);
            modalStage.setScene(scene);

            ModificarEnvioViewController controller = loader.getController();
            controller.initData(envioSeleccionado, modalStage, envioController);
            modalStage.showAndWait();
            onRefrescar();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarMensaje("Error", "Error al abrir", "No se pudo abrir la ventana de modificación.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onPagar() {
        Envio envioSeleccionado = tblEnvios.getSelectionModel().getSelectedItem();
        if (envioSeleccionado == null) return;

        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("PasarelaPago.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage modalStage = new Stage();
            modalStage.setTitle("Realizar Pago");
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(btnPagar.getScene().getWindow());
            Scene scene = new Scene(page);
            modalStage.setScene(scene);

            PasarelaPagoViewController controller = loader.getController();
            controller.initData(envioSeleccionado, modalStage, envioController);
            modalStage.showAndWait();
            onRefrescar();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarMensaje("Error", "Error al Pagar", "No se pudo abrir la pasarela de pago.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void onRastrear() {
        Envio envioSeleccionado = tblEnvios.getSelectionModel().getSelectedItem();
        if (envioSeleccionado == null) return;

        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("RastrearEnvio.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage modalStage = new Stage();
            modalStage.setTitle("Rastrear Envío");
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(btnRastrear.getScene().getWindow());
            Scene scene = new Scene(page);
            modalStage.setScene(scene);

            RastrearEnvioViewController controller = loader.getController();
            controller.initData(envioSeleccionado);

            modalStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarMensaje("Error", "Error al Rastrear", "No se pudo abrir la ventana de rastreo.", Alert.AlertType.ERROR);
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}