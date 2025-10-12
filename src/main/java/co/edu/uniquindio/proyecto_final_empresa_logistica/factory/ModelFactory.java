package co.edu.uniquindio.proyecto_final_empresa_logistica.factory;

import co.edu.uniquindio.proyecto_final_empresa_logistica.model.EmpresaLogistica;

public class ModelFactory {

    private static ModelFactory instance;
    EmpresaLogistica empresaLogistica;

    private ModelFactory() {}

    public static ModelFactory getInstance() {
        if (instance == null) {
            instance = new ModelFactory();
        }
        return instance;
    }


}
