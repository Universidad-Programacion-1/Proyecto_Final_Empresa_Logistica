package co.edu.uniquindio.proyecto_final_empresa_logistica.viewController;

import co.edu.uniquindio.proyecto_final_empresa_logistica.Application;
import co.edu.uniquindio.proyecto_final_empresa_logistica.controller.EnvioController;
import co.edu.uniquindio.proyecto_final_empresa_logistica.utils.TipoEstadoEnvio;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.util.Map;

public class MetricasViewController {

    @FXML private Label lblTotalEnvios;
    @FXML private Label lblTotalFacturado;
    @FXML private Label lblTiempoPromedio; // Nuevo

    @FXML private BarChart<String, Number> graficaEstados;
    @FXML private PieChart graficaServicios; // Nuevo
    @FXML private AreaChart<String, Number> graficaIngresos; // Nuevo

    EnvioController envioController;

    @FXML
    void initialize() {
        envioController = new EnvioController(Application.empresaLogistica);
        cargarMetricas();
    }

    private void cargarMetricas() {
        // 1. KPIs Numéricos
        int total = envioController.obtenerCantidadTotalEnvios();
        lblTotalEnvios.setText(String.valueOf(total));

        double dinero = envioController.obtenerTotalFacturado();
        lblTotalFacturado.setText("$ " + String.format("%,.0f", dinero));

        double diasPromedio = envioController.calcularTiempoPromedioEntrega();
        lblTiempoPromedio.setText(String.format("%.1f Días", diasPromedio));

        // 2. Cargar Gráficas
        cargarGraficaEstados();
        cargarGraficaServicios();
        cargarGraficaIngresos();
    }

    private void cargarGraficaEstados() {
        graficaEstados.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Envíos");

        for (TipoEstadoEnvio estado : TipoEstadoEnvio.values()) {
            int cantidad = envioController.obtenerCantidadPorEstado(estado);
            if (cantidad > 0) {
                series.getData().add(new XYChart.Data<>(estado.toString(), cantidad));
            }
        }
        graficaEstados.getData().add(series);
    }

    private void cargarGraficaServicios() {
        graficaServicios.getData().clear();
        Map<String, Integer> servicios = envioController.obtenerConteoServicios();

        for (Map.Entry<String, Integer> entry : servicios.entrySet()) {
            if(entry.getValue() > 0){
                graficaServicios.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
            }
        }
    }

    private void cargarGraficaIngresos() {
        graficaIngresos.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Ingresos");

        Map<String, Double> ingresos = envioController.obtenerIngresosPorMes();

        for (Map.Entry<String, Double> entry : ingresos.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        graficaIngresos.getData().add(series);
    }
}