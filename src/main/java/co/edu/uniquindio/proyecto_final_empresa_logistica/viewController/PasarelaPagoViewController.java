package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;

import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.EnvioController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Envio;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PasarelaPagoViewController {

    private Envio envioSeleccionado;
    private Stage modalStage;
    private EnvioController envioController;

    @FXML
    private Label lblMonto;
    @FXML
    private ComboBox<String> cmbMetodoPago;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtFechaExp;
    @FXML
    private TextField txtCvv;
    @FXML
    private Button btnConfirmarPago;

    @FXML
    void initialize() {
        cmbMetodoPago.getItems().addAll("Tarjeta de Crédito", "Tarjeta Débito (PSE)");
        cmbMetodoPago.setValue("Tarjeta de Crédito");
    }

    public void initData(Envio envio, Stage modalStage, EnvioController envioController) {
        this.envioSeleccionado = envio;
        this.modalStage = modalStage;
        this.envioController = envioController;

        lblMonto.setText(String.format("$ %.2f", envio.getCosto()));
    }

    @FXML
    void onConfirmarPago() {
        if (txtNombre.getText().isEmpty() || txtNumero.getText().isEmpty() ||
                txtFechaExp.getText().isEmpty() || txtCvv.getText().isEmpty()) {
            mostrarMensaje("Error", "Campos vacíos", "Por favor llene todos los datos de pago.", Alert.AlertType.WARNING);
            return;
        }

        String metodo = cmbMetodoPago.getValue();

        Usuario usuarioLogueado = envioController.getUsuario();
        if (usuarioLogueado == null) {
            mostrarMensaje("Error", "Error de Sesión", "No se pudo identificar al usuario. Inicie sesión de nuevo.", Alert.AlertType.ERROR);
            return;
        }

        String idUsuario = usuarioLogueado.getId();

        boolean pagado = envioController.realizarPago(envioSeleccionado, metodo, idUsuario);

        if (pagado) {
            mostrarMensaje("Éxito", "Pago Procesado", "El pago se ha realizado con éxito.", Alert.AlertType.INFORMATION);
            modalStage.close();
        } else {
            mostrarMensaje("Error", "Error de Pago", "No se pudo procesar el pago.", Alert.AlertType.ERROR);
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