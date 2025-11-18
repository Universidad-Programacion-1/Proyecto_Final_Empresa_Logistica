package co.edu.uniquindio.proyecto_final_empresa_logistica.decorator;

public class EmpaqueCartonDecorator extends EnvioDecorator{

    public EmpaqueCartonDecorator(IEnvio envioDecorado) {
        super(envioDecorado);
    }

    @Override
    public String descripcion() {
        return envioDecorado.descripcion() + " Empaque de cartón";
    }

    @Override
    public double costo() {
        return envioDecorado.costo() + 2000;
    }
}
