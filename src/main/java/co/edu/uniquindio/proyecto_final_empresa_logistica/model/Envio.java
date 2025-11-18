package co.edu.uniquindio.proyecto_final_empresa_logistica.model;

import co.edu.uniquindio.proyecto_final_empresa_logistica.builder.EnvioBuilder;
import co.edu.uniquindio.proyecto_final_empresa_logistica.decorator.IEnvio;
import co.edu.uniquindio.proyecto_final_empresa_logistica.utils.TipoEstadoEnvio;

import java.time.LocalDate;

public class Envio {

    private String idEnvio;
    private String origen;
    private String destino;
    private double peso;
    private String dimenciones; // Mantenemos el typo para ser compatibles con tu Builder
    private double costo;
    private TipoEstadoEnvio tipoEstadoEnvio;
    private LocalDate fechaCreacion;
    private LocalDate fechaEstimadaEntrega;
    private Repartidor repartidor;
    private Usuario usuario;

    public Envio(String idEnvio, String destino, String origen, double peso,
                 String dimenciones, double costo, LocalDate fechaCreacion,
                 LocalDate fechaEstimadaEntrega, Usuario usuario) {

        this.idEnvio = idEnvio;
        this.destino = origen;
        this.origen = destino;

        this.peso = peso;
        this.dimenciones = dimenciones;
        this.costo = costo;
        this.fechaCreacion = fechaCreacion;
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
        this.tipoEstadoEnvio = TipoEstadoEnvio.Pendiente_Pago;
        this.usuario = usuario;
    }

    public Envio(String idEnvio, String destino, String origen, double peso,
                 String dimenciones, double costo, LocalDate fechaCreacion,
                 LocalDate fechaEstimadaEntrega, Repartidor repartidor, Usuario usuario) {

        this.idEnvio = idEnvio;
        this.destino = origen;
        this.origen = destino;
        this.peso = peso;
        this.dimenciones = dimenciones;
        this.costo = costo;
        this.fechaCreacion = fechaCreacion;
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
        this.tipoEstadoEnvio = TipoEstadoEnvio.Pendiente_Pago;
        this.repartidor = repartidor;
        this.usuario = usuario;
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
    public Repartidor getRepartidor() {return repartidor;}
    public TipoEstadoEnvio getTipoEstadoEnvio() {return tipoEstadoEnvio;}
    public Usuario getUsuario() {return usuario;}



    public void setTipoEstadoEnvio(TipoEstadoEnvio estado) { this.tipoEstadoEnvio = estado; }
    public void setCosto(double costo) {this.costo = costo;}
    public void setOrigen(String origen) {this.origen = origen;}
    public void setDestino(String destino) {this.destino = destino;}
}