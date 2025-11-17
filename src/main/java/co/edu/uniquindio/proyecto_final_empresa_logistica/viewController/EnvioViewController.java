package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;

import co.edu.uniquindio.proyecto_final_empresa_logistica.Application;
import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.EnvioController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.decorator.*;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Envio;
import co.edu.uniquindio.proyecto_final_empresa_logistica.strategy.TotalCriterio;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EnvioViewController {

    EnvioController envioController;
    TotalCriterio totalCriterio;
    private Application application;
    Envio selectedEnvio;

    @FXML
    private TextField txtOrigen;

    @FXML
    private TextField txtDestino;

    @FXML
    private Button btnCrearEnvio;

    @FXML
    private TextField txtPeso;

    @FXML
    private Button btnCalcular;

    @FXML
    private CheckBox chbPlasticoBurbujas;

    @FXML
    private CheckBox chbEmpaqueCarton;

    @FXML
    private TextField txtDistancia;

    @FXML
    private CheckBox chbEmbolturaCarton;

    @FXML
    private Label lblCosto;

    @FXML
    void onCalcular() {
        mostrarCostoTotal();
    }

    @FXML
    void initialize() {
        // CAMBIO: Usamos la referencia estática de Application para evitar NullPointerException
        envioController = new EnvioController(Application.empresaLogistica);
    }

    // Método necesario para la navegación entre ventanas
    public void setApp(Application application) {
        this.application = application;
    }

    private double enviarInformacion() {
        long peso = Long.parseLong(txtPeso.getText());
        long distancia = Long.parseLong(txtDistancia.getText());
        return envioController.costoTotal(peso, distancia);
    }

    private void mostrarCostoTotal() {
        try {
            long peso = Long.parseLong(txtPeso.getText());
            long distancia = Long.parseLong(txtDistancia.getText());

            double costoBase = envioController.costoTotal(peso, distancia);

            IEnvio envio = new EnvioBase(costoBase);

            if (chbEmpaqueCarton.isSelected()) {
                envio = new EmpaqueCartonDecorator(envio);
            }
            if (chbPlasticoBurbujas.isSelected()) {
                envio = new PlasticoBurbujaDecorator(envio);
            }
            if (chbEmbolturaCarton.isSelected()) {
                envio = new EnvolturaImpermeableDecorator(envio);
            }

            lblCosto.setText("$ " + envio.costo());

        } catch (NumberFormatException e) {
            lblCosto.setText("Datos inválidos");
        }
    }

    private void empaque(){
        if (chbEmpaqueCarton.isSelected()) {
            envioController.costoEnvio();
        }
        if (chbPlasticoBurbujas.isSelected()) {
            envioController.costoEnvio();
        }
        if (chbEmbolturaCarton.isSelected()) {
            envioController.costoEnvio();
        }
    }

    @FXML
    void onCrearEnvio() {
        try {
            // 1. VALIDACIÓN: Verificar que los campos no estén vacíos
            if (txtOrigen.getText().isEmpty() || txtDestino.getText().isEmpty() || txtPeso.getText().isEmpty()) {
                mostrarMensaje("Alerta", "Campos Vacíos", "Por favor ingrese origen, destino, peso y distancia.", Alert.AlertType.WARNING);
                return;
            }

            // 2. RECOLECCIÓN DE DATOS (Aquí tomamos lo que el usuario escribió)
            String origen = txtOrigen.getText();
            String destino = txtDestino.getText();
            long peso = Long.parseLong(txtPeso.getText());
            long distancia = Long.parseLong(txtDistancia.getText());
            String dimensiones = "Estándar";

            // ID del usuario (En una app real, esto vendría del login. Lo dejamos quemado por ahora como pediste)
            String idUsuario = "2";

            // 3. CÁLCULOS
            double costoBase = envioController.costoTotal(peso, distancia);
            boolean llevaCarton = chbEmpaqueCarton.isSelected();
            boolean llevaBurbuja = chbPlasticoBurbujas.isSelected();
            boolean llevaImpermeable = chbEmbolturaCarton.isSelected();

            String idEnvio = String.valueOf(System.currentTimeMillis()); // Genera ID único

            // 4. LLAMADA AL CONTROLADOR
            Envio nuevoEnvio = envioController.crearEnvio(
                    idEnvio, origen, destino, peso, dimensiones,
                    costoBase, llevaCarton, llevaBurbuja, llevaImpermeable,
                    "0", idUsuario
            );

            if(nuevoEnvio != null){
                mostrarMensaje("Éxito", "Envío Creado", "El envío se ha guardado con éxito en la BD.\nCosto Total: $" + nuevoEnvio.getCosto(), Alert.AlertType.INFORMATION);
                limpiarCampos();
            }

        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "Datos inválidos", "Por favor ingrese números válidos en peso y distancia", Alert.AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        txtPeso.setText("");
        txtDistancia.setText("");
        txtOrigen.setText("");
        txtDestino.setText("");
        chbEmpaqueCarton.setSelected(false);
        chbPlasticoBurbujas.setSelected(false);
        chbEmbolturaCarton.setSelected(false);
        lblCosto.setText("$ 0.0");
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}