package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;

import co.edu.uniquindio.proyecto_final_empresa_logistica.Application;
import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.RepartidorController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Repartidor;
import co.edu.uniquindio.proyecto_final_empresa_logistica.utils.TipoEstadoDisponible;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EditarPerfilRepartidorViewController {

    RepartidorController repartidorController;
    Repartidor repartidor;
    private Application app;

    @FXML
    private Button btnActualizarPerfil;

    @FXML
    private TextField txtNombreCompleto;

    @FXML
    private TextField txtNumeroTelefono;

    @FXML
    private ComboBox<TipoEstadoDisponible> cbxEstadoDisponible;

    @FXML
    private TextField txtZonaCobertura;

    @FXML
    private TextField txtCorreoElectronico;

    @FXML
    void onActualizarPerfil() {

    }

    public void setApp(Application app) {
        this.app = app;
    }

    @FXML
    void initialize() {
        repartidorController = new RepartidorController(app.empresaLogistica);
        cbxEstadoDisponible.getItems().addAll(TipoEstadoDisponible.values());

        initView();
    }
    private void actualizarPerfil() {
        if (repartidorController.actualizarRepartidor(repartidor.getId(), buildRepartidor())) {
            mostrarInfoUsuario();
        }
    }

    private Repartidor buildRepartidor() {
        Repartidor repartidor = new Repartidor(null, txtNombreCompleto.getText(), txtCorreoElectronico.getText(),  txtNumeroTelefono.getText(),null, cbxEstadoDisponible.getValue(), txtZonaCobertura.getText());
        return repartidor;

    }

    private void obtenerRepartidor() {
        repartidor = repartidorController.obtenerRepartidor();
    }

    private void mostrarInfoUsuario() {

        if (repartidor != null) {
            txtNombreCompleto.setText(String.valueOf(repartidor.getNombre()));
            txtCorreoElectronico.setText(String.valueOf(repartidor.getCorreo()));
            txtNumeroTelefono.setText(String.valueOf(repartidor.getTelefono()));
            txtZonaCobertura.setText(String.valueOf(repartidor.getZonaCobertura()));
            cbxEstadoDisponible.setValue(repartidor.getEstadoDisponible());
        }
    }

    private void initView() {
        obtenerRepartidor();
        mostrarInfoUsuario();
    }

}
