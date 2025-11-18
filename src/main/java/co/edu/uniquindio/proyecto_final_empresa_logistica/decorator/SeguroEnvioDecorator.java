package co.edu.uniquindio.proyecto_final_empresa_logistica.decorator;

public class SeguroEnvioDecorator extends EnvioDecorator {

    public SeguroEnvioDecorator(IEnvio envioDecorado) {
        super(envioDecorado);
    }

    @Override
    public String descripcion() {
        return envioDecorado.descripcion() + " + Seguro de envío";
    }

    @Override
    public double costo() {
        return envioDecorado.costo() + 5000;
    }
}