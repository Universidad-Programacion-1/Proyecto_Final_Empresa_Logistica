package co.edu.uniquindio.proyecto_final_empresa_logistica.model;

import co.edu.uniquindio.proyecto_final_empresa_logistica.builder.EnvioBuilder;
import co.edu.uniquindio.proyecto_final_empresa_logistica.decorator.IEnvio;

import java.time.LocalDate;

public class Envio implements IEnvio {

    private String idEnvio;
    private String origen;
    private String destino;
    private double peso;
    private String dimenciones;
    private double costo;
    //private TipoEstadoEnvio tipoEstadoEnvio;
    private LocalDate fechaCreacion;
    private LocalDate fechaEstimadaEntrega;

    public Envio(String idEnvio, String destino, String origen, double peso,
                 String dimenciones, double costo, LocalDate fechaCreacion,
                 LocalDate fechaEstimadaEntrega) {

        this.idEnvio = idEnvio;
        this.destino = destino;
        this.origen = origen;
        this.peso = peso;
        this.dimenciones = dimenciones;
        this.costo = costo;
        this.fechaCreacion = fechaCreacion;
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
    }

    public static EnvioBuilder builder(){
        return new EnvioBuilder();
    }
    public String getOrigen() {return origen;}
    public String getIdEnvio() {return idEnvio;}
    public double getPeso() {return peso;}
    public String getDestino() {return destino;}
    public String getDimenciones() {return dimenciones;}
    public double getCosto() {return costo;}
    public LocalDate getFechaCreacion() {return fechaCreacion;}
    public LocalDate getFechaEstimadaEntrega() {return fechaEstimadaEntrega;}

    @Override
    public String getDescripcion() {
        return "Envío desde " + origen + " hasta " + destino + " (" + peso + "kg)";
    }
}
