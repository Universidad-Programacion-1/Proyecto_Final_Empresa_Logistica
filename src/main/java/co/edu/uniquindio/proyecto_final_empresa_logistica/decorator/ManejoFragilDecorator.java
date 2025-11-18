package co.edu.uniquindio.proyecto_final_empresa_logistica.decorator;

public class ManejoFragilDecorator extends EnvioDecorator {

    public ManejoFragilDecorator(IEnvio envioDecorado) {
        super(envioDecorado);
    }

    @Override
    public String descripcion() {
        return envioDecorado.descripcion() + " + Manejo frágil";
    }

    @Override
    public double costo() {
        return envioDecorado.costo() + 3000;
    }
}