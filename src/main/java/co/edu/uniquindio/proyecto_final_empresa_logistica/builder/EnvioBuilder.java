package co.edu.uniquindio.proyecto_final_empresa_logistica.builder;

import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Envio;

import java.time.LocalDate;

public class EnvioBuilder {

    protected String idEnvio;
    protected String origen;
    protected String destino;
    protected double peso;
    protected String dimenciones;
    protected double costo;
    //protected TipoEstadoEnvio tipoEstadoEnvio;
    protected LocalDate fechaCreacion;
    protected LocalDate fechaEstimadaEntrega;

    public EnvioBuilder idEnvio(String idEnvio) {
        this.idEnvio = idEnvio;
        return this;
    }

    public EnvioBuilder origen(String origen) {
        this.origen = origen;
        return this;
    }

    public EnvioBuilder destino(String destino) {
        this.destino = destino;
        return this;
    }

    public EnvioBuilder peso(double peso) {
        this.peso = peso;
        return this;
    }

    public EnvioBuilder dimenciones(String dimenciones) {
        this.dimenciones = dimenciones;
        return this;
    }

    public EnvioBuilder costo(double costo) {
        this.costo = costo;
        return this;
    }

    //public EnvioBuilder tipoEstadoEnvio(TipoEstadoEnvio tipoEstadoEnvio) {
    //    this.tipoEstadoEnvio = tipoEstadoEnvio;
    //    return this;
    //}

    public EnvioBuilder fechaCreacion(LocalDate fechaCreacion) {this.fechaCreacion = fechaCreacion;
        return this;
    }

    public EnvioBuilder fechaEstimadaEntrega(LocalDate fechaEstimadaEntrega) {
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
        return this;
    }

    public Envio build() {
        return new Envio(idEnvio, origen, destino, peso, dimenciones, costo, fechaCreacion, fechaEstimadaEntrega);
    }
}
