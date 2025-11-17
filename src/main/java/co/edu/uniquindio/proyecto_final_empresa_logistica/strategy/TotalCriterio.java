package co.edu.uniquindio.proyecto_final_empresa_logistica.strategy;

public class TotalCriterio {

    private ICriteriosStrategy strategy;
    private CriterioPeso  criterioPeso;
    private CriterioDistancia  criterioDistancia;

    // BORRADO: EnvioDecorator envioDecorado; (Esto causaba el error porque era null)

    public TotalCriterio(CriterioPeso criterioPeso, CriterioDistancia criterioDistancia) {
        this.criterioPeso  = criterioPeso;
        this.criterioDistancia = criterioDistancia;
    }

    public double precioTotalCriterio(long peso, long distancia) {
        double precioTotal = 0;


        precioTotal = criterioDistancia.calcularPrecioCriterios(peso, distancia)
                + criterioPeso.calcularPrecioCriterios(peso, distancia);

        return precioTotal;
    }
}
