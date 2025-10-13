package co.edu.uniquindio.proyecto_final_empresa_logistica.controller;

import co.edu.uniquindio.proyecto_final_empresa_logistica.model.EmpresaLogistica;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Usuario;

import java.util.Collection;

public class UsuarioController {
    EmpresaLogistica empresaLogistica;

    public UsuarioController(EmpresaLogistica empresaLogistica) {
        this.empresaLogistica = empresaLogistica;
    }

    public boolean agregarUsuario(Usuario usuario) {
        return empresaLogistica.agregarUsuario(usuario);
    }

    public Collection<Usuario> obtenerUsuario(String id) {
        return empresaLogistica.getUsuarios();
    }

    public boolean eliminarUsuario(String id) {
        return empresaLogistica.eliminarUsuario(id);
    }

    public boolean actualizarUsuario(String id, Usuario actualizado) {
        return empresaLogistica.actualizarUsuario(id, actualizado);
    }

    public boolean verificarUsuario(String id) {
        return empresaLogistica.verificarUsuario(id);
    }
}
