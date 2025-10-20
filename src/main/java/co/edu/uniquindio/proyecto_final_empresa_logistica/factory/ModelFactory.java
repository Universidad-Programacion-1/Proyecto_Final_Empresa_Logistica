package co.edu.uniquindio.proyecto_final_empresa_logistica.factory;

import co.edu.uniquindio.proyecto_final_empresa_logistica.ConexionBD.Conexion;
import co.edu.uniquindio.proyecto_final_empresa_logistica.decorator.EnvioDecorator;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.*;
import co.edu.uniquindio.proyecto_final_empresa_logistica.services.IModelFactoryServices;
import co.edu.uniquindio.proyecto_final_empresa_logistica.strategy.CriterioDistancia;
import co.edu.uniquindio.proyecto_final_empresa_logistica.strategy.CriterioPeso;
import co.edu.uniquindio.proyecto_final_empresa_logistica.strategy.TotalCriterio;

import java.util.Collection;

public class ModelFactory implements IModelFactoryServices {

    private static ModelFactory instance;
    Conexion conexion = Conexion.getInstancia();

    EmpresaLogistica empresaLogistica;
    TotalCriterio totalCriterio;
    TotalCriterio totalCriterio1;
    EnvioDecorator envioDecorado;

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
        TotalCriterio totalCriterio = new TotalCriterio(new CriterioPeso(), new CriterioDistancia());
        this.totalCriterio = totalCriterio;
        this.empresaLogistica = empresaLogistica;
    }

    public int login (String correo, String password) {
        conexion.conectar();
        int c = empresaLogistica.login(correo, password);;
        conexion.desconectar();
        return c;
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
    public boolean agregarUsuario(Usuario  usuario) {
        System.out.println("Model Factory");
        return empresaLogistica.agregarUsuario(usuario);
    }

    public Collection<Usuario> listaUsuarios() {
        return empresaLogistica.getUsuarios();
    }

    @Override
    public Usuario obtenerUsuario(String id) {
        return null;
    }

    @Override
    public boolean eliminarUsuario(String id) {
        return empresaLogistica.eliminarUsuario(id);
    }

    @Override
    public boolean actualizarUsuario(String id, Usuario actualizado) {
        return empresaLogistica.actualizarUsuario(id, actualizado);
    }
//    @Override
//    public double calcularPrecioCriteriosDistancia( long peso, long distancia){
//        return criterioDistancia.calcularPrecioCriterios( peso,  distancia);
//    }
//    @Override
//    public double calcularPrecioCriteriosPeso( long peso, long distancia){
//        return criterioPeso.calcularPrecioCriterios( peso,  distancia);
//    }

    @Override
    public boolean verificarUsuario(String id) {
        return false;
    }

    @Override
    public double calcularPrecioCriterios(long peso, long distancia) {
        return totalCriterio.precioTotalCriterio(peso, distancia);
    }


    @Override
    public String descripcion() {
        return envioDecorado.descripcion();
    }

    @Override
    public double costo() {
        return envioDecorado.costo();
    }
}
