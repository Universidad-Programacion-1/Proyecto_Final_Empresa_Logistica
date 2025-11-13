package co.edu.uniquindio.proyecto_final_empresa_logistica.model;

public class Repartidor extends Persona {

    private boolean estadoDisponible;
    private String zonaCobertura;

    public Repartidor() {
    }

    public Repartidor(String id, String nombre, String correo, String telefono, String password, boolean estadoDisponible, String zonaCobertura) {
        super(id, nombre, correo, telefono, password);
        this.estadoDisponible = estadoDisponible;
        this.zonaCobertura = zonaCobertura;
    }
}
