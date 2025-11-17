package co.edu.uniquindio.proyecto_final_empresa_logistica.decorator;

public abstract class EnvioDecorator implements IEnvio {
    protected IEnvio envioDecorado;

    EmpaqueCartonDecorator empaqueCartonDecorator;

    public EnvioDecorator(IEnvio envio) {
        this.envioDecorado = envio;
    }

    @Override
    public String descripcion() {
        return envioDecorado.descripcion();
    }

    @Override
    public double costo() {
        return envioDecorado.costo();
    }
}
