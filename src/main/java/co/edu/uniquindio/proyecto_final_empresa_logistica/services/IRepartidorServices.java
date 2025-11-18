package co.edu.uniquindio.proyecto_final_empresa_logistica.services;

import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Repartidor;

import java.util.Collection;

public interface IRepartidorServices {

    boolean agregarRepartidor(Repartidor repartidor);

    Repartidor obtenerRepartidor(String id);
    Collection<Repartidor> obtenerRepartidoresDisponibles();

    boolean eliminarRepartidor(String id);

    boolean actualizarRepartidor(String id, Repartidor repartidor);
}
