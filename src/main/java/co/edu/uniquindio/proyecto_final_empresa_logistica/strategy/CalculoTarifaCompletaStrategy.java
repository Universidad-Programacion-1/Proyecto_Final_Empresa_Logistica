package co.edu.uniquindio.proyecto_final_empresa_logistica.strategy;

public class CalculoTarifaCompletaStrategy implements ICalculoTarifaStrategy {

    @Override
    public double calcularCostoBase(long peso, long distancia, String volumen, String prioridad) {
        double costo = 0;

        costo += distancia * 50;
        costo += peso * 100;


        try {
            double vol = Double.parseDouble(volumen);
            costo += vol * 2000; // Asumamos 2000 pesos por m³
        } catch (NumberFormatException e) {
            // si el volumen es texto (pequeño, mediano...)
            if (volumen.equalsIgnoreCase("pequeño")) costo += 2000;
            else if (volumen.equalsIgnoreCase("mediano")) costo += 4000;
            else if (volumen.equalsIgnoreCase("grande")) costo += 6000;
        }


        if (prioridad == null) {
            prioridad = "normal";
        }

        switch (prioridad.toLowerCase()) {
            case "baja":
                costo *= 0.9;
                break;
            case "normal":
                // Sin cambios
                break;
            case "alta":
                costo *= 1.2;
                break;
            case "express":
                costo *= 1.4;
                break;
            default:
                break;
        }

        return costo;
    }
}
