package co.edu.uniquindio.proyecto_final_empresa_logistica.decorator;

public class EnvioBase implements IEnvio {

    private double costoBase;

    public EnvioBase(double costoBase) {
        this.costoBase = costoBase;
    }

    @Override
    public String descripcion() {
        return "Envío base";
    }

    @Override
    public double costo() {
        return costoBase;
    }
}
