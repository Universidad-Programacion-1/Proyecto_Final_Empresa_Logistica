package co.edu.uniquindio.proyecto_final_empresa_logistica.decorator;

import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Envio;
import co.edu.uniquindio.proyecto_final_empresa_logistica.strategy.TotalCriterio;

public abstract class EnvioDecorator implements IEnvio {
    protected IEnvio envioDecorado;
    EnvolturaImpermeableDecorator envolturaImpermeableDecorator;
    PlasticoBurbujaDecorator  plasticoBurbujaDecorador;

    EmpaqueCartonDecorator empaqueCartonDecorator;

    public EnvioDecorator(EmpaqueCartonDecorator empaqueCartonDecorator, EnvolturaImpermeableDecorator envolturaImpermeableDecorator, PlasticoBurbujaDecorator plasticoBurbujaDecorator) {
        this.empaqueCartonDecorator = empaqueCartonDecorator;
        this.envolturaImpermeableDecorator = envolturaImpermeableDecorator;
        this.plasticoBurbujaDecorador = plasticoBurbujaDecorador;
    }

    public EnvioDecorator(IEnvio envioDecorado) {
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
