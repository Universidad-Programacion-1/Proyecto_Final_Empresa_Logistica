package co.edu.uniquindio.proyecto_final_empresa_logistica.decorator;

public class PlasticoBurbujaDecorator extends EnvioDecorator{

    public PlasticoBurbujaDecorator(IEnvio envioDecorado) {
        super(envioDecorado);
    }

    @Override
    public String getDescripcion() {
        return envioDecorado.getDescripcion() + " + Plástico de burbujas";
    }

    @Override
    public double getCosto() {
        return envioDecorado.getCosto() + 1500;
    }
}
