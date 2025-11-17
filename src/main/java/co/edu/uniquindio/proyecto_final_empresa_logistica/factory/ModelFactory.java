package co.edu.uniquindio.proyecto_final_empresa_logistica.factory;

import co.edu.uniquindio.proyecto_final_empresa_logistica.ConexionBD.Conexion;
import co.edu.uniquindio.proyecto_final_empresa_logistica.builder.EnvioBuilder;
import co.edu.uniquindio.proyecto_final_empresa_logistica.decorator.*;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.*;
import co.edu.uniquindio.proyecto_final_empresa_logistica.services.IModelFactoryServices;
import co.edu.uniquindio.proyecto_final_empresa_logistica.strategy.CriterioDistancia;
import co.edu.uniquindio.proyecto_final_empresa_logistica.strategy.CriterioPeso;
import co.edu.uniquindio.proyecto_final_empresa_logistica.strategy.TotalCriterio;
import co.edu.uniquindio.proyecto_final_empresa_logistica.utils.Persistencia;
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

    public Envio crearEnvio(String id, String origen, String destino, double peso, String dimensiones,
                            double costoBase, boolean carton, boolean burbuja, boolean impermeable,
                            String idRepartidor, String idUsuario) {

        Envio envioNuevo = Envio.builder()
                .idEnvio(id)
                .origen(origen)
                .destino(destino)
                .peso(peso)
                .dimenciones(dimensiones)
                .costo(costoBase)
                .tipoEstadoEnvio(TipoEstadoEnvio.Solicitado)
                .fechaCreacion(java.time.LocalDate.now())
                .build();

        IEnvio envioDecorado = new EnvioBase(costoBase);
        if (carton) envioDecorado = new EmpaqueCartonDecorator(envioDecorado);
        if (burbuja) envioDecorado = new PlasticoBurbujaDecorator(envioDecorado);
        if (impermeable) envioDecorado = new EnvolturaImpermeableDecorator(envioDecorado);

        for(Usuario u : empresaLogistica.getUsuarios()){
            if(u.getId().equals(idUsuario)){
                break;
            }
        }
        empresaLogistica.getEnvios().add(envioNuevo);
        guardarResourceXML(); // Guardar en archivo

        return envioNuevo;
    }

    private ModelFactory() {
        
        this.empresaLogistica = Persistencia.cargarRecursoXML();
        if (this.empresaLogistica == null) {

            this.empresaLogistica = new EmpresaLogistica("UQ Logistica");
            inicializarDatos();
            guardarResourceXML();
        }
        
        this.totalCriterio = new TotalCriterio(new CriterioPeso(), new CriterioDistancia());

        
    }

    private void guardarResourceXML() {
        Persistencia.guardarRecursoXML(this.empresaLogistica);
    }


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
        Repartidor repartidor = new Repartidor("123", "Chavez", "chavez", "321", "1234", true, "Quindio");
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
    public boolean actualizarRepartidor(String id, Repartidor repartidor ) {
        return false;
    }
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

}
