package co.edu.uniquindio.proyecto_final_empresa_logistica.strategy;

public class CriterioPeso implements ICriteriosStrategy{


    @Override
    public double calcularPrecioCriterios(long peso, long distancia) {
        double precioTotalCriteriosPeso = 0;
        if(peso <= 10.0){
            precioTotalCriteriosPeso= peso*200;
        }
        else if(peso <= 20.0){
            precioTotalCriteriosPeso= peso*200;
        }
        else if(peso <= 30.0){
            precioTotalCriteriosPeso= peso*200;
        }
        return precioTotalCriteriosPeso;

    }
}
