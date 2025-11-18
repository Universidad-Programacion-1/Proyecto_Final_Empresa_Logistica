package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;

import co.edu.uniquindio.proyecto_final_empresa_logistica.Application;
import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.EnvioController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Pago;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Usuario;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.util.Collection;

public class HistorialPagosViewController {

    EnvioController envioController;
    private Application application;
    private ObservableList<Pago> pagosObservableList = FXCollections.observableArrayList();

    @FXML
    private TableView<Pago> tblPagos;
    @FXML
    private TableColumn<Pago, String> colIdPago;
    @FXML
    private TableColumn<Pago, String> colIdEnvio;
    @FXML
    private TableColumn<Pago, LocalDate> colFecha;
    @FXML
    private TableColumn<Pago, Double> colMonto;
    @FXML
    private TableColumn<Pago, String> colMetodo;

    @FXML
    void initialize() {
        envioController = new EnvioController(Application.empresaLogistica);

        this.colIdPago.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getIdPago())
        );
        this.colIdEnvio.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getIdEnvio())
        );
        this.colFecha.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getFecha())
        );
        this.colMonto.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getMonto())
        );
        this.colMetodo.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getMetodoPago())
        );

        onRefrescar();
    }

    public void setApp(Application application) {
        this.application = application;
    }

    @FXML
    void onRefrescar() {
        tblPagos.getItems().clear();
        Usuario usuarioLogueado = envioController.getUsuario();
        if (usuarioLogueado != null) {
            String idUsuario = usuarioLogueado.getId();
            Collection<Pago> pagos = envioController.obtenerPagos(idUsuario);
            pagosObservableList.setAll(pagos);
            tblPagos.setItems(pagosObservableList);
        } else {
            mostrarMensaje("Error", "Error de Sesión", "No se pudo identificar al usuario logueado.", Alert.AlertType.ERROR);
        }
        tblPagos.refresh();
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}