package co.edu.uniquindio.proyecto_final_empresa_logistica.controller;

import co.edu.uniquindio.proyecto_final_empresa_logistica.factory.ModelFactory;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.EmpresaLogistica;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Envio;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Pago;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Repartidor;
import co.edu.uniquindio.proyecto_final_empresa_logistica.utils.TipoEstadoEnvio;

import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

    public Collection<Envio> obtenerEnviosUsuario(String idUsuario) {
        return modelFactory.obtenerEnviosUsuario(idUsuario);
    }

    public Repartidor obtenerRepartidor() {
        return modelFactory.getRepartidor();
    }

    public boolean actualizarEstadoEnvio(String id, TipoEstadoEnvio estado) {
        return modelFactory.actualizarEstadoEnvio(id, estado);
    }

    public boolean realizarPago(Envio envio, String metodoPago, String idUsuario) {
        return modelFactory.realizarPago(envio, metodoPago, idUsuario);
    }
    public int obtenerCantidadTotalEnvios() {
        return obtenerEnvios() != null ? obtenerEnvios().size() : 0;
    }


    public double obtenerTotalFacturado() {
        double total = 0;
        if (obtenerEnvios() != null) {
            for (Envio envio : obtenerEnvios()) {
                total += envio.getCosto();
            }
        }
        return total;
    }


    public int obtenerCantidadPorEstado(TipoEstadoEnvio estado) {
        int cantidad = 0;
        if (obtenerEnvios() != null) {
            for (Envio envio : obtenerEnvios()) {
                if (envio.getTipoEstadoEnvio() == estado) {
                    cantidad++;
                }
            }
        }
        return cantidad;
    }

    public double calcularTiempoPromedioEntrega() {
        Collection<Envio> lista = obtenerEnvios();
        if (lista == null || lista.isEmpty()) return 0;

        long totalDias = 0;
        int contador = 0;

        for (Envio envio : lista) {
            if (envio.getFechaCreacion() != null && envio.getFechaEstimadaEntrega() != null) {
                long dias = ChronoUnit.DAYS.between(envio.getFechaCreacion(), envio.getFechaEstimadaEntrega());
                totalDias += dias;
                contador++;
            }
        }
        return contador > 0 ? (double) totalDias / contador : 0;
    }

    public Map<String, Integer> obtenerConteoServicios() {
        Map<String, Integer> conteo = new HashMap<>();
        conteo.put("Cartón", 0);
        conteo.put("Burbujas", 0);
        conteo.put("Impermeable", 0);

        if (obtenerEnvios() != null) {
            for (Envio envio : obtenerEnvios()) {
                if(envio.getCosto() > 5000) conteo.put("Cartón", conteo.get("Cartón") + 1);
                if(envio.getCosto() > 8000) conteo.put("Burbujas", conteo.get("Burbujas") + 1);
                if(envio.getCosto() > 10000) conteo.put("Impermeable", conteo.get("Impermeable") + 1);
            }
        }
        return conteo;
    }

    public Map<String, Double> obtenerIngresosPorMes() {
        Map<String, Double> ingresos = new TreeMap<>();

        if (obtenerEnvios() != null) {
            for (Envio envio : obtenerEnvios()) {
                if (envio.getFechaCreacion() != null) {

                    String mes = envio.getFechaCreacion().getYear() + "-" + envio.getFechaCreacion().getMonthValue();
                    ingresos.put(mes, ingresos.getOrDefault(mes, 0.0) + envio.getCosto());
                }
            }
        }
        return ingresos;
    }
    public Collection<Repartidor> obtenerRepartidoresDisponibles() {
        return modelFactory.listaRepartidor();
    }


    public Collection<Pago> obtenerPagos(String idUsuario) {
        return modelFactory.obtenerPagos(idUsuario);
    }

    public Usuario getUsuario() {
        return modelFactory.getUsuario();
    }
}