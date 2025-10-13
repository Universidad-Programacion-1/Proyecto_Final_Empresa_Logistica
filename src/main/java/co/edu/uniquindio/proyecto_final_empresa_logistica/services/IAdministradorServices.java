package co.edu.uniquindio.proyecto_final_empresa_logistica.services;

import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Administrador;

public interface IAdministradorServices {

    boolean agregarAdministrador(String id,
                              String nombre,
                              String correo,
                              String telefono,
                              String password);

    Administrador obtenerAdministrador(String id);

    boolean eliminarAdministrador(String id);

    boolean actualizarAdministrador(String DNI, String nuevoNombre,
                                 String nuevoCorreo,
                                 String nuevoTelefono,
                                 String nuevoPassword);
}
