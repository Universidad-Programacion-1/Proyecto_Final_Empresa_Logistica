package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;


import co.edu.uniquindio.proyecto_final_empresa_logistica.Application;
import co.edu.uniquindio.proyecto_final_empresa_logistica.decorator.*;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Envio;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PaquetesRepartidorViewController {

    private Application application;

    @FXML
    private TableColumn<Envio, String> tbcId;

    @FXML
    private TableColumn<Envio, ?> tbcPeso;

    @FXML
    private TableColumn<Envio, ?> tbcCosto;

    @FXML
    private ComboBox<String> cbxEstadoEnvio;

    @FXML
    private TableView<Envio> tblListUsuarios;

    @FXML
    private TableColumn<Envio, String> tbcDestino;

    @FXML
    private TableColumn<Envio, ?> tbcDimencion;

    @FXML
    private TableColumn<Envio, String> tbcOrigen;

    @FXML
    private Button btnActualizarEstado;

    @FXML
    private TableColumn<Envio, ?> tbcFecha;

    @FXML
    private TableColumn<Envio, String> tbcEstado;

    @FXML
    private TableColumn<Envio, String> tbcFechaEntrega;

    @FXML
    void onActualizarUsuario() {

    }

    @FXML
    void initialize() {

        cbxEstadoEnvio.getItems().addAll("RECOGIDO", "EN CAMINO", "ENTREGADO");

    }

}
