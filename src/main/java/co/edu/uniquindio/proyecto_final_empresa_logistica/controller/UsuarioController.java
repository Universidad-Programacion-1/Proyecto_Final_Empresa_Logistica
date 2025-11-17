package co.edu.uniquindio.proyecto_final_empresa_logistica.controller;

import co.edu.uniquindio.proyecto_final_empresa_logistica.factory.ModelFactory;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.EmpresaLogistica;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Usuario;

import java.util.Collection;

public class UsuarioController {

    ModelFactory modelFactory = ModelFactory.getInstance();
    EmpresaLogistica empresaLogistica;

    public UsuarioController(EmpresaLogistica empresaLogistica) {
        this.empresaLogistica = empresaLogistica;
    }

    public boolean agregarUsuario(Usuario usuario) {
        return modelFactory.agregarUsuario(usuario);
    }

    public Collection<Usuario> obtenerUsuarios() {
        return modelFactory.listaUsuarios();
    }

    public boolean eliminarUsuario(String id) {
        return modelFactory.eliminarUsuario(id);
    }

    public boolean actualizarUsuario(String id, Usuario actualizado) {
        return modelFactory.actualizarUsuario(id, actualizado);
    }

    public boolean verificarUsuario(String id) {
        return empresaLogistica.verificarUsuario(id);
    }


    public Usuario obtenerUsuario() {
        return modelFactory.getUsuario();
    }

    public boolean eliminarDireccion(String direccion) {
        return modelFactory.eliminarDireccion(direccion);
    }

    public boolean agregarDireccion( String direccion) {
        return modelFactory.agregarDireccion(direccion);
    }
}
