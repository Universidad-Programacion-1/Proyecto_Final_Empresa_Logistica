package co.edu.uniquindio.proyecto_final_empresa_logistica.decorator;

public class EnvolturaImpermeableDecorator extends EnvioDecorator {
    public EnvolturaImpermeableDecorator(IEnvio envioDecorado) {
        super(envioDecorado);
    }

    @Override
    public String getDescripcion() {
        return envioDecorado.getDescripcion() + " + Envoltura impermeable";
    }

    @Override
    public double getCosto() {
        return envioDecorado.getCosto() + 2500;
    }
}
