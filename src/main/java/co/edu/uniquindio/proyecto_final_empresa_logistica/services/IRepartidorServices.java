package co.edu.uniquindio.proyecto_final_empresa_logistica.services;

import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Repartidor;

public interface IRepartidorServices {

    boolean agregarRepartidor(String id,
                           String nombre,
                           String correo,
                           String telefono,
                           String password);

    Repartidor obtenerRepartidor(String id);

    boolean eliminarRepartidor(String id);

    boolean actualizarRepartidor(String DNI, String nuevoNombre,
                              String nuevoCorreo,
                              String nuevoTelefono,
                              String nuevoPassword);
}
