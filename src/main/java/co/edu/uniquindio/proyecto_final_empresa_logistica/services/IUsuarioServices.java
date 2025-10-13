package co.edu.uniquindio.proyecto_final_empresa_logistica.services;

import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Usuario;

public interface IUsuarioServices {

    boolean agregarUsuario(Usuario usuario);

    Usuario obtenerUsuario(String id);

    boolean eliminarUsuario(String id);

    boolean actualizarUsuario(String id,
                              Usuario actualizado);
    boolean verificarUsuario(String id);
}
