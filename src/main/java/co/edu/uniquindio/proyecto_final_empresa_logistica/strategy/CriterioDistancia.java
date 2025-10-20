package co.edu.uniquindio.proyecto_final_empresa_logistica.strategy;

public class CriterioDistancia implements ICriteriosStrategy{
    @Override
    public double calcularPrecioCriterios(long peso, long distancia) {
        double precioTotalCriteriosDistancia = 0;
        if(distancia <= 10.0){
            precioTotalCriteriosDistancia= distancia*1.000;
        }
        else if(distancia <= 20.0){
            precioTotalCriteriosDistancia= distancia*1.000;
        }
        else if(distancia <= 30.0){
            precioTotalCriteriosDistancia= distancia*1.000;
        }
        return precioTotalCriteriosDistancia;

    }
}
