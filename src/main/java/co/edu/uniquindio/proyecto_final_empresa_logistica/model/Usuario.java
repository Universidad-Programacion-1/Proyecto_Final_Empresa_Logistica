package co.edu.uniquindio.proyecto_final_empresa_logistica.model;

import java.util.ArrayList;

public class Usuario extends Persona {

    private ArrayList<String> direcciones;

    public Usuario() {
    }


    public Usuario(String id, String nombre, String correo, String telefono, String password ) {
        super(id, nombre, correo, telefono, password);
        this.direcciones = new ArrayList<>();
    }

    public ArrayList<String> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(ArrayList<String> direcciones) {
        this.direcciones = direcciones;
    }
}
