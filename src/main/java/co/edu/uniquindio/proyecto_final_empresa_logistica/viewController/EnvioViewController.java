package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;

import co.edu.uniquindio.proyecto_final_empresa_logistica.Application;
import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.EnvioController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.decorator.*;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Envio;
import co.edu.uniquindio.proyecto_final_empresa_logistica.strategy.CalculoTarifaCompletaStrategy;
import co.edu.uniquindio.proyecto_final_empresa_logistica.strategy.ICalculoTarifaStrategy;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EnvioViewController {

    EnvioController envioController;
    private Application application;
    Envio selectedEnvio;

    private ICalculoTarifaStrategy calculoStrategy;

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
    private TextField txtVolumen;
    @FXML
    private ComboBox<String> cmbPrioridad;

    @FXML
    private CheckBox chbSeguro;
    @FXML
    private CheckBox chbFragil;
    @FXML
    private CheckBox chbFirma;


    @FXML
    void onCalcular() {
        mostrarCostoTotal();
    }

    @FXML
    void initialize() {
        envioController = new EnvioController(Application.empresaLogistica);

        calculoStrategy = new CalculoTarifaCompletaStrategy();

        cmbPrioridad.getItems().addAll("Baja", "Normal", "Alta", "Express");
        cmbPrioridad.setValue("Normal");
    }

    public void setApp(Application application) {
        this.application = application;
    }

    private void mostrarCostoTotal() {
        try {
            long peso = Long.parseLong(txtPeso.getText());
            long distancia = Long.parseLong(txtDistancia.getText());
            String volumen = txtVolumen.getText();
            String prioridad = cmbPrioridad.getValue();

            if (volumen.isEmpty()) {
                lblCosto.setText("Volumen requerido");
                return;
            }

            double costoBase = calculoStrategy.calcularCostoBase(peso, distancia, volumen, prioridad);

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
            if (chbSeguro.isSelected()) {
                envio = new SeguroEnvioDecorator(envio);
            }
            if (chbFragil.isSelected()) {
                envio = new ManejoFragilDecorator(envio);
            }
            if (chbFirma.isSelected()) {
                envio = new FirmaRequeridaDecorator(envio);
            }

            lblCosto.setText("$ " + String.format("%.2f", envio.costo()));

        } catch (NumberFormatException e) {
            lblCosto.setText("Datos numéricos inválidos");
        } catch (Exception e) {
            lblCosto.setText("Error en datos");
            e.printStackTrace();
        }
    }


    @FXML
    void onCrearEnvio() {
        try {
            if (txtOrigen.getText().isEmpty() || txtDestino.getText().isEmpty() ||
                    txtPeso.getText().isEmpty() || txtDistancia.getText().isEmpty() ||
                    txtVolumen.getText().isEmpty()) {

                mostrarMensaje("Alerta", "Campos Vacíos", "Por favor ingrese todos los datos del trayecto.", Alert.AlertType.WARNING);
                return;
            }

            String origen = txtOrigen.getText();
            String destino = txtDestino.getText();
            long peso = Long.parseLong(txtPeso.getText());
            long distancia = Long.parseLong(txtDistancia.getText());
            String volumen = txtVolumen.getText();
            String prioridad = cmbPrioridad.getValue();
            String idUsuario = "2";

            double costoBase = calculoStrategy.calcularCostoBase(peso, distancia, volumen, prioridad);

            IEnvio envioCalculado = new EnvioBase(costoBase);
            if (chbEmpaqueCarton.isSelected()) envioCalculado = new EmpaqueCartonDecorator(envioCalculado);
            if (chbPlasticoBurbujas.isSelected()) envioCalculado = new PlasticoBurbujaDecorator(envioCalculado);
            if (chbEmbolturaCarton.isSelected()) envioCalculado = new EnvolturaImpermeableDecorator(envioCalculado);
            if (chbSeguro.isSelected()) envioCalculado = new SeguroEnvioDecorator(envioCalculado);
            if (chbFragil.isSelected()) envioCalculado = new ManejoFragilDecorator(envioCalculado);
            if (chbFirma.isSelected()) envioCalculado = new FirmaRequeridaDecorator(envioCalculado);

            double costoFinal = envioCalculado.costo();

            boolean llevaCarton = chbEmpaqueCarton.isSelected();
            boolean llevaBurbuja = chbPlasticoBurbujas.isSelected();
            boolean llevaImpermeable = chbEmbolturaCarton.isSelected();

            String idEnvio = String.valueOf(System.currentTimeMillis());

            Envio nuevoEnvio = envioController.crearEnvio(
                    idEnvio, origen, destino, peso, volumen,
                    costoFinal,
                    llevaCarton, llevaBurbuja, llevaImpermeable,
                    "0", idUsuario
            );

            if(nuevoEnvio != null){
                nuevoEnvio.setCosto(costoFinal);
                mostrarMensaje("Éxito", "Envío Creado", "El envío se ha guardado con éxito.\nCosto Total: $" + String.format("%.2f", nuevoEnvio.getCosto()), Alert.AlertType.INFORMATION);
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
        txtVolumen.setText("");
        cmbPrioridad.setValue("Normal");
        chbEmpaqueCarton.setSelected(false);
        chbPlasticoBurbujas.setSelected(false);
        chbEmbolturaCarton.setSelected(false);
        chbSeguro.setSelected(false);
        chbFragil.setSelected(false);
        chbFirma.setSelected(false);
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