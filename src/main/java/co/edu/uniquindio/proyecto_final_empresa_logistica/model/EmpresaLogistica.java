package co.edu.uniquindio.proyecto_final_empresa_logistica.model;

import co.edu.uniquindio.proyecto_final_empresa_logistica.services.IEmpresaLogisticaServices;
import co.edu.uniquindio.proyecto_final_empresa_logistica.utils.TipoEstadoEnvio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

public class EmpresaLogistica implements IEmpresaLogisticaServices {

    private String nombre;
    private Collection<Administrador> administradores;
    private Collection<Usuario> usuarios;
    private Collection<Repartidor> repartidores;
    private Collection<Envio> envios;
    int tipoPersona;
    Usuario usuario1;
    Administrador administrador1;
    Repartidor repartidor1;

    public EmpresaLogistica() {
    }

    public EmpresaLogistica(String nombre) {
        this.nombre = nombre;
        this.administradores = new LinkedList<>();
        this.usuarios = new LinkedList<>();
        this.repartidores = new LinkedList<>();
        this.envios = new LinkedList<>();
    }


    public String getNombre() {
        return nombre;
    }

    public Collection<Administrador> getAdministradores() {
        return administradores;
    }

    public Collection<Usuario> getUsuarios() {
        return usuarios;
    }

    public Collection<Repartidor> getRepartidores() {
        return repartidores;
    }

    public Collection<Envio> getEnvios() {return envios;}

    public boolean agregarAdministrador(String id, String nombre, String correo, String telefono, String password) {

        Administrador admin = new Administrador(id, nombre, correo, telefono, password);
        boolean centinela = false;
        if (!verificarAdmin(admin.getId())) {
            administradores.add(admin);
            administrador1=admin;
            centinela = true;
            System.out.println("Administrador agregado com sucesso" + centinela);
        }
        return centinela;
    }
    public boolean verificarAdmin(String cedula) {
        boolean centinela = false;
        for (Administrador administrador : administradores) {
            if (administrador.getId().equals(cedula)) {
                centinela = true;
            }
        }
        return centinela;
    }

    public int login(String correo, String contrasena){
        int tipo = 0;
        System.out.println("Iniciando login" + correo);
        System.out.println("Iniciando login" + contrasena);
        for (Administrador administrador : administradores) {
            if (administrador.getCorreo().equals(correo) && administrador.getPassword().equals(contrasena)) {
                tipo = 1;
                tipoPersona = tipo;
                administrador1 = administrador;
            }
        }
        for (Usuario usuario : usuarios) {
            System.out.println("Iniciando usuario "+ usuario.getPassword());
            System.out.println("Iniciando usuario "+ usuario.getCorreo());
            if (usuario.getCorreo().equals(correo) && usuario.getPassword().equals(contrasena)) {
                tipo = 2;
                tipoPersona = tipo;
                usuario1 = usuario;
            }
        }
        for (Repartidor repartidor : repartidores ) {
            if (repartidor.getCorreo().equals(correo) && repartidor.getPassword().equals(contrasena)) {
                tipo = 3;
                tipoPersona = tipo;
                repartidor1 = repartidor;
            }

        }
        return tipo;
    }

    @Override
    public boolean agregarUsuario(Usuario usuario) {
        boolean centinela = false;
        if (!verificarUsuario(usuario.getId())) {
            usuarios.add(usuario);
            centinela = true;
            System.out.println("Usuario agregado com sucesso " + centinela);
        }
        return centinela;
    }

    @Override
    public Usuario obtenerUsuario(String id) {
        for(Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public boolean eliminarUsuario(String id) {
        boolean centinela = false;
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                usuarios.remove(usuario);
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    @Override
    public boolean actualizarUsuario(String id, Usuario actualizado) {
        boolean centinela = false;
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                usuario.setNombre(actualizado.getNombre());
                usuario.setCorreo(actualizado.getCorreo());
                usuario.setTelefono(actualizado.getTelefono());
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    @Override
    public boolean verificarUsuario(String id) {
        boolean centinela = false;
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                centinela = true;
            }
        }
        return centinela;
    }

    @Override
    public boolean agregarRepartidor(Repartidor repartidor) {
        boolean centinela = false;
        if (!verificarUsuario(repartidor.getId())) {
            repartidores.add(repartidor);
            centinela = true;
            System.out.println("Usuario agregado com sucesso " + centinela);
        }
        return centinela;
    }
    @Override
    public Repartidor obtenerRepartidor(String id) {
        return null;
    }

    @Override
    public boolean eliminarRepartidor(String id) {
        boolean centinela = false;
        for (Repartidor repartidor : repartidores) {
            if (repartidor.getId().equals(id)) {
                repartidores.remove(repartidor);
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    @Override
    public boolean actualizarRepartidor(String id, Repartidor actualizado ) {
        boolean centinela = false;
        for (Repartidor repartidor : repartidores) {
            if (repartidor.getId().equals(id)) {
                repartidor.setNombre(actualizado.getNombre());
                repartidor.setCorreo(actualizado.getCorreo());
                repartidor.setTelefono(actualizado.getTelefono());
                repartidor.setZonaCobertura(actualizado.getZonaCobertura());
                repartidor.setEstadoDisponible(actualizado.getEstadoDisponible());
                centinela = true;
                break;
            }
        }
        return centinela;
    }

    public boolean verificarRepartidor(String id) {
        boolean centinela = false;
        for (Repartidor repartidor : repartidores) {
            if (repartidor.getId().equals(id)) {
                centinela = true;
            }
        }
        return centinela;
    }

    public boolean agregarDireccion(String direccion) {
        boolean centinela = false;
        System.out.println("Iniciando agregarDireccion" + direccion);
        if(direccion != null) {
            usuario1.getDirecciones().add(direccion);
            centinela = true;

            System.out.println("Direccion agregado com sucesso " + usuario1.getDirecciones());
        }
        return centinela;
    }

    public boolean eliminarDireccion(String direccion) {
        boolean centinela = usuario1.getDirecciones().removeIf(d -> d.equals(direccion));
        if (centinela) {
            System.out.println("Dirección eliminada con éxito: " + direccion);
        }
        return centinela;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public Repartidor getRepartidor1() {
        return repartidor1;
    }

    public Collection<Envio> obtenerEnviosRepartidor(String idRepartidor) {
        Collection<Envio> enviosRepartidor = new ArrayList<>();
        for (Envio envio : envios) {
            System.out.println("Obteniendo envios"+ envio.getRepartidor().getId()+ "id "+  idRepartidor);
            if (envio.getRepartidor().getId().equals(idRepartidor)) {
                enviosRepartidor.add(envio);
            }
        }
        return enviosRepartidor;
    }

    public boolean actualizarEstadoEnvio(String id, TipoEstadoEnvio estado) {
        boolean centinela = false;
        for (Envio envio : envios) {
            System.out.println("Id.getebvio  "+ envio.getIdEnvio());
            System.out.println("Id  "+ id);
            if (envio.getIdEnvio().equals(id)) {
                System.out.println("entro a if "+ estado);
                envio.setTipoEstadoEnvio(estado);
                centinela = true;
            }
        }
        return  centinela;
    }

    public boolean agregarEnvio(Envio envio) {
        boolean centinela = false;
        if (envio != null) {
            envios.add(envio);
            centinela = true;
            System.out.println("Envio agregado com sucesso " + centinela);
        }
        return centinela;
    }

    public long calcularDistancia(String origen, String destino) {

        if (origen.equalsIgnoreCase(destino)) {
            return 5;
        }

        Map<String, Long> destinos = DISTANCIAS.get(origen);

        if (destinos != null) {
            return destinos.getOrDefault(destino, 100L); // si no existe, valor por defecto
        }

        return 100L; // origen no encontrado
    }

    private static final Map<String, Map<String, Long>> DISTANCIAS = Map.of(
            "Armenia", Map.of(
                    "Cali", 180L,
                    "Medellin", 250L,
                    "Bogota", 290L
            ),
            "Cali", Map.of(
                    "Armenia", 180L,
                    "Medellin", 420L,
                    "Bogota", 460L
            ),
            "Medellin", Map.of(
                    "Armenia", 250L,
                    "Cali", 420L,
                    "Bogota", 420L
            ),
            "Bogota", Map.of(
                    "Armenia", 290L,
                    "Cali", 460L,
                    "Medellin", 420L
            )
    );

    public double cotizarEnvio(String origen, String destino, double peso, String volumen, String prioridad) {

        long distancia = calcularDistancia(origen, destino);

        Tarifa tarifa = new Tarifa(distancia, prioridad, volumen, (long) peso);

        return tarifa.calcularTarifa();
    }
}
