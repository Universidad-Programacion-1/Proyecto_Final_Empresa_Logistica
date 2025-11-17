package co.edu.uniquindio.proyecto_final_empresa_logistica.factory;

import co.edu.uniquindio.proyecto_final_empresa_logistica.ConexionBD.Conexion;
import co.edu.uniquindio.proyecto_final_empresa_logistica.builder.EnvioBuilder;
import co.edu.uniquindio.proyecto_final_empresa_logistica.decorator.EnvioDecorator;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.*;
import co.edu.uniquindio.proyecto_final_empresa_logistica.services.IModelFactoryServices;
import co.edu.uniquindio.proyecto_final_empresa_logistica.strategy.CriterioDistancia;
import co.edu.uniquindio.proyecto_final_empresa_logistica.strategy.CriterioPeso;
import co.edu.uniquindio.proyecto_final_empresa_logistica.strategy.TotalCriterio;
import co.edu.uniquindio.proyecto_final_empresa_logistica.utils.TipoEstadoDisponible;
import co.edu.uniquindio.proyecto_final_empresa_logistica.utils.TipoEstadoEnvio;

import java.time.LocalDate;
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
        LocalDate fecha = LocalDate.now();
        System.out.println("Inicializando ModelFactory");
        EmpresaLogistica empresaLogistica = new EmpresaLogistica("Repartimos Felicidad");
        Administrador administrador = new Administrador("123", "Carlos", "ruiz", "321", "123");
        Repartidor repartidor = new Repartidor("123", "Chavez", "chavez", "321", "1234", TipoEstadoDisponible.Activo, "Quindio");
        Usuario usuario = new Usuario("123", "Alejo", "alejo", "321", "12345");
        Envio envio = new EnvioBuilder()
                .idEnvio("1")
                .destino("La Tebaida")
                .origen("Armenia")
                .peso(10)
                .dimenciones("5x3")
                .costo(100.000)
                .fechaCreacion(fecha)
                .fechaEstimadaEntrega(fecha)
                .repartidor(repartidor)
                .usuario(usuario)
                .build1();
        empresaLogistica.agregarEnvio(envio);
        empresaLogistica.getAdministradores().add(administrador);
        empresaLogistica.getRepartidores().add(repartidor);
        empresaLogistica.getUsuarios().add(usuario);
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
    public boolean agregarRepartidor(Repartidor repartidor) {
        return empresaLogistica.agregarRepartidor(repartidor);
    }

    @Override
    public Repartidor obtenerRepartidor(String id) {
        return null;
    }

    @Override
    public boolean eliminarRepartidor(String id) {return empresaLogistica.eliminarRepartidor(id);}

    @Override
    public boolean actualizarRepartidor(String id, Repartidor repartidor ) {return empresaLogistica.actualizarRepartidor(id, repartidor);}

    public Collection<Repartidor> listaRepartidor() {return empresaLogistica.getRepartidores();}

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

    public Usuario getUsuario(){
        return empresaLogistica.getUsuario1();
    }

    public boolean agregarDireccion(String direccion) {
        return empresaLogistica.agregarDireccion(direccion);
    }

    public boolean eliminarDireccion(String direccion) {
        return empresaLogistica.eliminarDireccion(direccion);
    }

    public Collection<Envio> listaEnvios() {
        return empresaLogistica.getEnvios();
    }

    public Collection<Envio> obtenerEnviosRepartidor(String idRepartidor) {
        return empresaLogistica.obtenerEnviosRepartidor(idRepartidor);
    }

    public Repartidor getRepartidor() {
        return empresaLogistica.getRepartidor1();
    }

    public boolean actualizarEstadoEnvio(String id, TipoEstadoEnvio estado) {
        return empresaLogistica.actualizarEstadoEnvio(id, estado);
    }


    public double cotizarEnvio(String origen, String destino, double peso,
                               String volumen, String prioridad) {

        return empresaLogistica.cotizarEnvio(origen, destino, peso, volumen, prioridad);
    }
}
