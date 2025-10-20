package co.edu.uniquindio.proyecto_final_empresa_logistica.decorator;

public class EmpaqueCartonDecorator extends EnvioDecorator{
    public EmpaqueCartonDecorator(IEnvio envioDecorado) {
        super(envioDecorado);
    }

    @Override
    public String getDescripcion() {
        return envioDecorado.getDescripcion() + " + Empaque de cartón";
    }

    @Override
    public double getCosto() {
        return envioDecorado.getCosto() + 2000;
    }
}
