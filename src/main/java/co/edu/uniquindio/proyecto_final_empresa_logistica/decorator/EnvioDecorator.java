package co.edu.uniquindio.proyecto_final_empresa_logistica.decorator;

public abstract class EnvioDecorator implements IEnvio {

    protected IEnvio envioDecorado;

    public EnvioDecorator(IEnvio envioDecorado) {
        this.envioDecorado = envioDecorado; // <--- ¡ESTO FALTABA!
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