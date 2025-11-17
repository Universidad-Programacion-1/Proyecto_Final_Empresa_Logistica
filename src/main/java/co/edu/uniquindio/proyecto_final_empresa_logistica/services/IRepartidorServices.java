package co.edu.uniquindio.proyecto_final_empresa_logistica.services;

import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Repartidor;

public interface IRepartidorServices {

    boolean agregarRepartidor(Repartidor repartidor);

    Repartidor obtenerRepartidor(String id);

    boolean eliminarRepartidor(String id);

    boolean actualizarRepartidor(String id, Repartidor repartidor);
}
