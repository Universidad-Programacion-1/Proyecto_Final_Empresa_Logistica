package co.edu.uniquindio.proyecto_final_empresa_logistica.controller;

import co.edu.uniquindio.proyecto_final_empresa_logistica.decorator.EnvioDecorator;
import co.edu.uniquindio.proyecto_final_empresa_logistica.factory.ModelFactory;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.EmpresaLogistica;
import co.edu.uniquindio.proyecto_final_empresa_logistica.strategy.TotalCriterio;

public class EnvioController {
    ModelFactory modelFactory = ModelFactory.getInstance();
    EmpresaLogistica empresaLogistica;

    public EnvioController(EmpresaLogistica empresaLogistica) {
        this.empresaLogistica = empresaLogistica;
    }

    public double costoTotal(long peso, long distancia) {
        return modelFactory.calcularPrecioCriterios(peso, distancia);
    }

    public String descripcionTotal() {
        return modelFactory.descripcion();
    }
    public double costoEnvio() {
        return modelFactory.costo();
    }

}
