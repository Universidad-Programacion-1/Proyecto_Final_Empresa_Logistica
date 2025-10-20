package co.edu.uniquindio.proyecto_final_empresa_logistica.decorator;

public class PlasticoBurbujaDecorator extends EnvioDecorator{

    public PlasticoBurbujaDecorator(IEnvio envioDecorado) {
        super(envioDecorado);
    }

    @Override
    public String descripcion() {
        return envioDecorado.descripcion() + " + Plástico de burbujas";
    }

    @Override
    public double costo() {
        return envioDecorado.costo() + 1500;
    }
}
