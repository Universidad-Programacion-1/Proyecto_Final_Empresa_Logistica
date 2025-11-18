package co.edu.uniquindio.proyecto_final_empresa_logistica.model;

import co.edu.uniquindio.proyecto_final_empresa_logistica.utils.TipoEstadoDisponible;

public class Repartidor extends Persona {

    private TipoEstadoDisponible estadoDisponible;
    private String zonaCobertura;

    public Repartidor() {
    }


    public Repartidor(String id, String nombre, String correo, String telefono, String password, TipoEstadoDisponible estadoDisponible, String zonaCobertura) {
        super(id, nombre, correo, telefono, password);
        this.estadoDisponible = estadoDisponible;
        this.zonaCobertura = zonaCobertura;
    }


    public TipoEstadoDisponible getEstadoDisponible() {
        return estadoDisponible;
    }


    public void setEstadoDisponible(TipoEstadoDisponible estadoDisponible) {
        this.estadoDisponible = estadoDisponible;
    }

    public String getZonaCobertura() {
        return zonaCobertura;
    }

    public void setZonaCobertura(String zonaCobertura) {
        this.zonaCobertura = zonaCobertura;
    }


    @Override
    public String toString() {
        return getNombre();
    }
}