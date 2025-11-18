package co.edu.uniquindio.proyecto_final_empresa_logistica.strategy;

public interface ICalculoTarifaStrategy {
    double calcularCostoBase(long peso, long distancia, String volumen, String prioridad);
}
