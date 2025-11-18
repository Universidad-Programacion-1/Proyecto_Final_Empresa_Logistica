package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;

import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Envio;
import co.edu.uniquindio.proyecto_final_empresa_logistica.utils.TipoEstadoEnvio;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class RastrearEnvioViewController {

    @FXML
    private Label lblIdEnvio;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label lblPagado;
    @FXML
    private Label lblAsignado;
    @FXML
    private Label lblEnRuta;
    @FXML
    private Label lblEntregado;
    @FXML
    private Label lblEstadoActual;

    private final String styleCompletado = "-fx-font-weight: bold; -fx-text-fill: #27ae60;";
    private final String stylePendiente = "-fx-font-weight: normal; -fx-text-fill: #95a5a6;";
    private final String styleIncidencia = "-fx-font-weight: bold; -fx-text-fill: #c0392b;";

    public void initData(Envio envio) {
        lblIdEnvio.setText(envio.getIdEnvio());
        lblEstadoActual.setText(envio.getTipoEstadoEnvio().name());

        actualizarProgreso(envio.getTipoEstadoEnvio());
    }

    private void actualizarProgreso(TipoEstadoEnvio estado) {

        lblPagado.setStyle(stylePendiente);
        lblAsignado.setStyle(stylePendiente);
        lblEnRuta.setStyle(stylePendiente);
        lblEntregado.setStyle(stylePendiente);

        switch (estado) {
            case Pendiente_Pago:
                progressBar.setProgress(0.0);
                lblEstadoActual.setStyle(stylePendiente);
                break;
            case Pagado:
                progressBar.setProgress(0.25);
                lblPagado.setStyle(styleCompletado);
                lblEstadoActual.setStyle(styleCompletado);
                break;
            case Asignado:
                progressBar.setProgress(0.5);
                lblPagado.setStyle(styleCompletado);
                lblAsignado.setStyle(styleCompletado);
                lblEstadoActual.setStyle(styleCompletado);
                break;
            case En_Ruta:
                progressBar.setProgress(0.75);
                lblPagado.setStyle(styleCompletado);
                lblAsignado.setStyle(styleCompletado);
                lblEnRuta.setStyle(styleCompletado);
                lblEstadoActual.setStyle(styleCompletado);
                break;
            case Entregado:
                progressBar.setProgress(1.0);
                lblPagado.setStyle(styleCompletado);
                lblAsignado.setStyle(styleCompletado);
                lblEnRuta.setStyle(styleCompletado);
                lblEntregado.setStyle(styleCompletado);
                lblEstadoActual.setStyle(styleCompletado);
                break;
            case Cancelado:
                progressBar.setProgress(0.0);
                progressBar.setStyle("-fx-accent: #c0392b;");
                lblEstadoActual.setStyle(styleIncidencia);
                break;
            case Incidencia:
                progressBar.setStyle("-fx-accent: #e67e22;");
                lblEstadoActual.setStyle(styleIncidencia);
                break;
        }
    }
}
