package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;

import co.edu.uniquindio.proyecto_final_empresa_logistica.factory.ModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CotizarTarifaViewController {

    @FXML
    private TextField txtOrigen;
    @FXML
    private TextField txtDestino;
    @FXML
    private TextField txtPeso;

    @FXML
    private ComboBox<String> comboVolumen;
    @FXML
    private ComboBox<String> comboPrioridad;

    @FXML
    private Button btnCalcular;

    @FXML
    private Label lblResultado;

    private ModelFactory modelFactory = ModelFactory.getInstance();

    @FXML
    public void initialize() {
        comboVolumen.getItems().addAll("PEQUEÑO", "MEDIANO", "GRANDE");
        comboPrioridad.getItems().addAll("NORMAL", "EXPRESS", "URGENTE");
    }

    @FXML
    private void calcularTarifa() {
        try {
            String origen = txtOrigen.getText();
            String destino = txtDestino.getText();
            String volumen = comboVolumen.getValue();
            String prioridad = comboPrioridad.getValue();
            double peso = Double.parseDouble(txtPeso.getText());

            if (origen.isEmpty() || destino.isEmpty() || volumen == null || prioridad == null) {
                lblResultado.setText("Complete todos los campos.");
                return;
            }

            double total = modelFactory.cotizarEnvio(origen, destino, peso, volumen, prioridad);

            lblResultado.setText("Total: $" + total);

        } catch (Exception e) {
            lblResultado.setText("Error: verifique los datos.");
        }
    }
}
