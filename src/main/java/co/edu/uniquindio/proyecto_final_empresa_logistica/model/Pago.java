package co.edu.uniquindio.proyecto_final_empresa_logistica.model;

import java.time.LocalDate;

public class Pago {

    private String idPago;
    private double monto;
    private LocalDate fecha;
    private String metodoPago;
    private String idEnvio;
    private String idUsuario;

    public Pago(String idPago, double monto, LocalDate fecha, String metodoPago, String idEnvio, String idUsuario) {
        this.idPago = idPago;
        this.monto = monto;
        this.fecha = fecha;
        this.metodoPago = metodoPago;
        this.idEnvio = idEnvio;
        this.idUsuario = idUsuario;
    }

    public String getIdPago() {
        return idPago;
    }

    public double getMonto() {
        return monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public String getIdEnvio() {
        return idEnvio;
    }

    public String getIdUsuario() {
        return idUsuario;
    }
}
