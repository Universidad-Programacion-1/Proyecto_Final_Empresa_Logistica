package co.edu.uniquindio.proyecto_final_empresa_logistica.strategy;

public class TotalCriterio {

    private ICriteriosStrategy strategy;

    public TotalCriterio(ICriteriosStrategy strategy) {
        this.strategy = strategy;
    }
    public double totalCalculadoCriterio(long peso, long distancia){
        //strategy.calcularPrecioCriterios(peso, distancia);
        return strategy.calcularPrecioCriterios(peso, distancia);
    }
}
