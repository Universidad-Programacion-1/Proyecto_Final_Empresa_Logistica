package co.edu.uniquindio.proyecto_final_empresa_logistica.controller;

import co.edu.uniquindio.proyecto_final_empresa_logistica.decorator.EnvioDecorator;
import co.edu.uniquindio.proyecto_final_empresa_logistica.factory.ModelFactory;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.EmpresaLogistica;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Envio;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Repartidor;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Usuario;
import co.edu.uniquindio.proyecto_final_empresa_logistica.strategy.TotalCriterio;
import co.edu.uniquindio.proyecto_final_empresa_logistica.utils.TipoEstadoEnvio;

import java.util.Collection;

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
    public Envio crearEnvio(String id, String origen, String destino, double peso, String dimensiones,
                            double costoBase, boolean carton, boolean burbuja, boolean impermeable,
                            String idRepartidor, String idUsuario){
        return modelFactory.crearEnvio( id,  origen,  destino,  peso, dimensiones,
         costoBase,  carton,  burbuja,  impermeable,
         idRepartidor, idUsuario);
    }

    public Collection<Envio> obtenerEnvios() {
        return modelFactory.listaEnvios();
    }

    public Collection<Envio> obtenerEnviosRepartidor(String idRepartidor) {
        return modelFactory.obtenerEnviosRepartidor(idRepartidor);
    }

    public Repartidor obtenerRepartidor() {
        return modelFactory.getRepartidor();
    }

    public boolean actualizarEstadoEnvio(String id, TipoEstadoEnvio estado) {
        return modelFactory.actualizarEstadoEnvio(id, estado);
    }




}
