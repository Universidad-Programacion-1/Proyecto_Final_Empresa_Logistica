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
import co.edu.uniquindio.proyecto_final_empresa_logistica.utils.TipoEstadoDisponible;
import co.edu.uniquindio.proyecto_final_empresa_logistica.utils.TipoEstadoEnvio;

import java.time.LocalDate;
import java.util.ArrayList;
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

        IEnvio envioDecorado = new EnvioBase(costoBase);
        if (carton) envioDecorado = new EmpaqueCartonDecorator(envioDecorado);
        if (burbuja) envioDecorado = new PlasticoBurbujaDecorator(envioDecorado);
        if (impermeable) envioDecorado = new EnvolturaImpermeableDecorator(envioDecorado);
        double costoFinal = envioDecorado.costo();

        Usuario usuarioDelEnvio = null;
        for (Usuario u : empresaLogistica.getUsuarios()) {
            if (u.getId().equals(idUsuario)) {
                usuarioDelEnvio = u;
                break;
            }
        }

        Envio envioNuevo = Envio.builder()
                .idEnvio(id)
                .origen(origen)
                .destino(destino)
                .peso(peso)
                .dimenciones(dimensiones)
                .costo(costoFinal)
                .tipoEstadoEnvio(TipoEstadoEnvio.Pendiente_Pago)
                .fechaCreacion(java.time.LocalDate.now())
                .usuario(usuarioDelEnvio)
                .build();

        empresaLogistica.getEnvios().add(envioNuevo);
        guardarResourceXML();

        return envioNuevo;
    }

    private ModelFactory() {

        this.empresaLogistica = Persistencia.cargarRecursoXML();
        if (this.empresaLogistica == null) {

            this.empresaLogistica = new EmpresaLogistica("UQ Logistica");
            //inicializarDatos();
            //guardarResourceXML();
        } else {
            if (this.empresaLogistica.getListaPagos() == null) {
                this.empresaLogistica.setListaPagos(new ArrayList<>());
            }
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
        System.out.println("Inicializando ModelFactory------------------------------------------------------------------------------------------------------------------------------------------------------");
        EmpresaLogistica empresaLogistica = new EmpresaLogistica("Repartimos Felicidad");
        Administrador administrador = new Administrador("123", "Carlos", "ruiz", "321", "123");
        Repartidor repartidor = new Repartidor("1234", "Chavez", "chavez", "321", "1234", TipoEstadoDisponible.disponible, "Quindio");
        Usuario usuario = new Usuario("1235", "Alejo", "alejo", "321", "12345");
        Usuario usuario2 = new Usuario("2", "Usuario Dos", "usuario2@mail.com", "321", "12345");

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
        empresaLogistica.agregarRepartidor(repartidor);
        empresaLogistica.getUsuarios().add(usuario);
        empresaLogistica.getUsuarios().add(usuario2);

        empresaLogistica.setListaPagos(new ArrayList<>());

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
    public Collection<Repartidor> obtenerRepartidoresDisponibles(){
        return empresaLogistica.obtenerRepartidoresDisponibles();
    }

    @Override
    public boolean actualizarRepartidor(String id, Repartidor repartidor ) {return empresaLogistica.actualizarRepartidor(id, repartidor);}

    public Collection<Repartidor> listaRepartidor() {
        return empresaLogistica.getRepartidores();
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

    public Collection<Envio> obtenerEnviosUsuario(String idUsuario) {
        return empresaLogistica.obtenerEnviosUsuario(idUsuario);
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

    public boolean realizarPago(Envio envio, String metodoPago, String idUsuario) {
        try {
            String idPago = "P-" + System.currentTimeMillis();
            double monto = envio.getCosto();
            LocalDate fecha = LocalDate.now();

            Pago nuevoPago = new Pago(idPago, monto, fecha, metodoPago, envio.getIdEnvio(), idUsuario);
            empresaLogistica.getListaPagos().add(nuevoPago);

            envio.setTipoEstadoEnvio(TipoEstadoEnvio.Pagado);
            //guardarResourceXML();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validarCorreoTelefono(String correo, String telefono) {
        return empresaLogistica.validarCorreoTelefono(correo, telefono);
    }

    public boolean contrasenaTemporal(String password, String correo) {
        return empresaLogistica.contrasenaTemporal(password, correo);
    }

    public boolean generarReporteUsuario(String id, String tipoDocumento){
        return empresaLogistica.generarDocumentoUsuario(id, tipoDocumento);
    }

    public boolean generarReporteAdministrador(String tipoDocumento){
        return empresaLogistica.generarReporteAdministrador(tipoDocumento);
    }

    public Collection<Pago> obtenerPagos(String idUsuario) {
        ArrayList<Pago> pagosUsuario = new ArrayList<>();
        if(empresaLogistica.getListaPagos() == null) {
            return pagosUsuario;
        }

        for (Pago pago : empresaLogistica.getListaPagos()) {
            if (pago.getIdUsuario().equals(idUsuario)) {
                pagosUsuario.add(pago);
            }
        }
        return pagosUsuario;
    }

}