package co.edu.uniquindio.proyecto_final_empresa_logistica.model;

import co.edu.uniquindio.proyecto_final_empresa_logistica.factory.ModelFactory;

public class Tarifa {

    private long distancia;
    private String prioridad;
    private String volumen;
    private long peso;

    public Tarifa(long distancia, String prioridad, String volumen, long peso) {
        this.distancia = distancia;
        this.prioridad = prioridad;
        this.volumen = volumen;
        this.peso = peso;
    }

    public double calcularTarifa() {
        double costo = 0;

        costo += distancia * 50; // 50 pesos por km

        costo += peso * 100; // 100 pesos por kilo


        try {
            double vol = Double.parseDouble(volumen);
            costo += vol * 0.2;
        } catch (NumberFormatException e) {
            // si el volumen es texto (pequeño, mediano...)
            if (volumen.equalsIgnoreCase("pequeño")) costo += 2000;
            else if (volumen.equalsIgnoreCase("mediano")) costo += 4000;
            else if (volumen.equalsIgnoreCase("grande")) costo += 6000;
        }

        switch (prioridad.toLowerCase()) {
            case "baja":
                costo *= 0.9;
                break;
            case "normal":
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
