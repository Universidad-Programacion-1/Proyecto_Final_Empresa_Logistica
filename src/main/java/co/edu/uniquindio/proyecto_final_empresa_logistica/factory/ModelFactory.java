package co.edu.uniquindio.proyecto_final_empresa_logistica.factory;

import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Administrador;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.EmpresaLogistica;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Repartidor;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Usuario;
import co.edu.uniquindio.proyecto_final_empresa_logistica.services.IModelFactoryServices;

public class ModelFactory implements IModelFactoryServices {

    private static ModelFactory instance;
    EmpresaLogistica empresaLogistica;

    private ModelFactory() {}

    public static ModelFactory getInstance() {
        if (instance == null) {
            instance = new ModelFactory();
        }
        return instance;
    }

    public void inicializarDatos(){
        EmpresaLogistica empresaLogistica = new EmpresaLogistica("Repartimos Felicidad");
        Administrador administrador = new Administrador("123", "Carlos", "ruiz", "321", "123");
        empresaLogistica.getAdministradores().add(administrador);
        this.empresaLogistica = empresaLogistica;
    }

    public int login (String correo, String password) {
        return empresaLogistica.login(correo, password);
    }


    @Override
    public boolean agregarAdministrador(String id, String nombre, String correo, String telefono, String password) {
        return empresaLogistica.agregarAdministrador(id, nombre, correo, telefono, password);
    }

    @Override
    public Administrador obtenerAdministrador(String id) {
        return null;
    }

    @Override
    public boolean eliminarAdministrador(String id) {
        return false;
    }

    @Override
    public boolean actualizarAdministrador(String DNI, String nuevoNombre, String nuevoCorreo, String nuevoTelefono, String nuevoPassword) {
        return false;
    }

    @Override
    public boolean agregarRepartidor(String id, String nombre, String correo, String telefono, String password) {
        return false;
    }

    @Override
    public Repartidor obtenerRepartidor(String id) {
        return null;
    }

    @Override
    public boolean eliminarRepartidor(String id) {
        return false;
    }

    @Override
    public boolean actualizarRepartidor(String DNI, String nuevoNombre, String nuevoCorreo, String nuevoTelefono, String nuevoPassword) {
        return false;
    }

    @Override
    public boolean agregarUsuario(String id, String nombre, String correo, String telefono, String password) {
        return false;
    }

    @Override
    public Usuario obtenerUsuario(String id) {
        return null;
    }

    @Override
    public boolean eliminarUsuario(String id) {
        return false;
    }

    @Override
    public boolean actualizarUsuario(String DNI, String nuevoNombre, String nuevoCorreo, String nuevoTelefono, String nuevoPassword) {
        return false;
    }
}
