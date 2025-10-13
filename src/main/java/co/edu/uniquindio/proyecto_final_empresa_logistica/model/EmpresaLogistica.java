package co.edu.uniquindio.proyecto_final_empresa_logistica.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class EmpresaLogistica {

    private String nombre;
    private Collection<Administrador> administradores;
    private Collection<Usuario> usuarios;
    private Collection<Repartidor> repartidores;
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
        System.out.println("Iniciando login" + administradores);
        for (Administrador administrador : administradores) {
            if (administrador.getCorreo().equals(correo) && administrador.getPassword().equals(contrasena)) {
                tipo = 1;
                tipoPersona = tipo;
                administrador1 = administrador;
            }
        }
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo) && usuario.getCorreo().equals(contrasena)) {
                tipo = 2;
                tipoPersona = tipo;
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
}
