package co.edu.uniquindio.proyecto_final_empresa_logistica.decorator;

public class FirmaRequeridaDecorator extends EnvioDecorator {

    public FirmaRequeridaDecorator(IEnvio envioDecorado) {
        super(envioDecorado);
    }

    @Override
    public String descripcion() {
        return envioDecorado.descripcion() + " + Firma requerida en entrega";
    }

    @Override
    public double costo() {
        return envioDecorado.costo() + 1500;
    }
}