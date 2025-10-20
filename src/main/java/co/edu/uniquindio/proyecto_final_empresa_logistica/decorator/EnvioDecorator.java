package co.edu.uniquindio.proyecto_final_empresa_logistica.decorator;

import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Envio;

public abstract class EnvioDecorator implements IEnvio {
    protected IEnvio envioDecorado;
    EmpaqueCartonDecorator empaqueCartonDecorator;

    public EnvioDecorator(IEnvio envio) {
        this.envioDecorado = envio;
    }

    @Override
    public String getDescripcion() {
        return empaqueCartonDecorator.getDescripcion();
    }

    @Override
    public double getCosto() {
        return envioDecorado.getCosto();
    }
}
