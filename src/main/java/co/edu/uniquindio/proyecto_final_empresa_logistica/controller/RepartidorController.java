package co.edu.uniquindio.proyecto_final_empresa_logistica.controller;

import co.edu.uniquindio.proyecto_final_empresa_logistica.factory.ModelFactory;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.EmpresaLogistica;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Repartidor;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Usuario;

import java.util.Collection;

public class RepartidorController {

    ModelFactory modelFactory = ModelFactory.getInstance();
    EmpresaLogistica empresaLogistica;

    public RepartidorController(EmpresaLogistica empresaLogistica) {
        this.empresaLogistica = empresaLogistica;
    }

    public boolean agregarRepartidor(Repartidor repartidor) {
        return modelFactory.agregarRepartidor(repartidor);
    }

    public Collection<Repartidor> obtenerRepartidor() {
        return modelFactory.listaRepartidor();
    }

    public boolean eliminarRepartidor(String id) {
        return modelFactory.eliminarRepartidor(id);
    }

    public boolean actualizarRepartidor(String id, Repartidor actualizado) {
        return modelFactory.actualizarRepartidor(id, actualizado);
    }

    public boolean verificarRepartidor(String id) {
        return empresaLogistica.verificarUsuario(id);
    }
}
