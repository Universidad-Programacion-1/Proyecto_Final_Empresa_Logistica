package co.edu.uniquindio.proyecto_final_empresa_logistica.decorator;

public class EnvolturaImpermeableDecorator extends EnvioDecorator {
    public EnvolturaImpermeableDecorator(IEnvio envioDecorado) {
        super(envioDecorado);
    }

    @Override
    public String descripcion() {
        return envioDecorado.descripcion() + " + Envoltura impermeable";
    }

    @Override
    public double costo() {
        return envioDecorado.costo() + 2500;
    }
}
