package co.edu.uniquindio.proyecto_final_empresa_logistica.strategy;

public class CriterioDistancia implements ICriteriosStrategy{
    @Override
    public double calcularPrecioCriterios(long peso, long distancia) {
        double precioTotalCriteriosDistancia = 0;
        if(distancia <= 10.0){
            precioTotalCriteriosDistancia= distancia*100;
        }
        else if(distancia <= 20.0){
            precioTotalCriteriosDistancia= distancia*100;
        }
        else if(distancia <= 30.0){
            precioTotalCriteriosDistancia= distancia*100;
        }
        return precioTotalCriteriosDistancia;

    }
}
