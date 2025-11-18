package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;

import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.EnvioController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Envio;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModificarEnvioViewController {

    private Envio envioSeleccionado;
    private Stage modalStage;
    private EnvioController envioController;

    @FXML
    private TextField txtOrigen;
    @FXML
    private TextField txtDestino;


    public void initData(Envio envio, Stage modalStage, EnvioController envioController) {
        this.envioSeleccionado = envio;
        this.modalStage = modalStage;
        this.envioController = envioController;
        txtOrigen.setText(envio.getOrigen());
        txtDestino.setText(envio.getDestino());
    }

    @FXML
    void onGuardar() {

        if (txtOrigen.getText().isEmpty() || txtDestino.getText().isEmpty()) {
            mostrarMensaje("Error", "Campos vacíos", "Origen y Destino no pueden estar vacíos.", Alert.AlertType.WARNING);
            return;
        }


        envioSeleccionado.setOrigen(txtOrigen.getText());
        envioSeleccionado.setDestino(txtDestino.getText());


        mostrarMensaje("Éxito", "Cambios Guardados", "Los cambios han sido guardados (en memoria).", Alert.AlertType.INFORMATION);

        modalStage.close();
    }

    @FXML
    void onCerrar() {
        modalStage.close();
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
