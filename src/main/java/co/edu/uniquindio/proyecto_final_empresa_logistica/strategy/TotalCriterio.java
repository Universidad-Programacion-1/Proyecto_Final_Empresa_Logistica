package co.edu.uniquindio.proyecto_final_empresa_logistica.strategy;

import co.edu.uniquindio.proyecto_final_empresa_logistica.decorator.EmpaqueCartonDecorator;
import co.edu.uniquindio.proyecto_final_empresa_logistica.decorator.EnvioDecorator;

public class TotalCriterio {

    private ICriteriosStrategy strategy;
    private CriterioPeso  criterioPeso;
    private CriterioDistancia  criterioDistancia;
    EnvioDecorator envioDecorado;

    public TotalCriterio(CriterioPeso criterioPeso, CriterioDistancia criterioDistancia) {
        this.criterioPeso  = criterioPeso;
        this.criterioDistancia = criterioDistancia;
    }

    public double precioTotalCriterio(long peso, long distancia) {
        double precioTotal = 0;
        precioTotal = criterioDistancia.calcularPrecioCriterios( peso,  distancia)+criterioPeso.calcularPrecioCriterios( peso,  distancia);

        return precioTotal;
    }
}
