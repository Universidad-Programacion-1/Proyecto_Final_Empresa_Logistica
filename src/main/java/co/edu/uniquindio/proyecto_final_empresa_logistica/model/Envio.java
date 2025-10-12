package co.edu.uniquindio.proyecto_final_empresa_logistica.model;

import java.time.LocalDate;

public class Envio {

    private String idEnvio;
    private String origen;
    private String destino;
    private double peso;
    private String dimenciones;
    private double costo;
    //private TipoEstadoEnvio tipoEstadoEnvio;
    private LocalDate fechaCreacion;
    private LocalDate fechaEstimadaEntrega;
}
