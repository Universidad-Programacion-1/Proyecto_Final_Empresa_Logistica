package co.edu.uniquindio.proyecto_final_empresa_logistica.model;

public class Tarifa {

    private long distancia;
    private long peso;
    private String volumen;
    private String recargoAdicional;


    public Tarifa(long distancia, String recargoAdicional, String volumen, long peso) {
        this.distancia = distancia;
        this.recargoAdicional = recargoAdicional;
        this.volumen = volumen;
        this.peso = peso;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(long distancia) {
        this.distancia = distancia;
    }

    public String getRecargoAdicional() {
        return recargoAdicional;
    }

    public void setRecargoAdicional(String recargoAdicional) {
        this.recargoAdicional = recargoAdicional;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(long peso) {
        this.peso = peso;
    }
}
