package co.edu.uniquindio.proyecto_final_empresa_logistica.controller;

import co.edu.uniquindio.proyecto_final_empresa_logistica.factory.ModelFactory;
import co.edu.uniquindio.proyecto_final_empresa_logistica.model.EmpresaLogistica;


public class LoginController {

    ModelFactory modelFactory = ModelFactory.getInstance();
    EmpresaLogistica empresaLogistica;

    public LoginController(EmpresaLogistica empresaLogistica) {
        this.empresaLogistica = empresaLogistica;
    }

    public int login (String correo, String password) {
        return modelFactory.login(correo, password);
    }

    public boolean validarCorreoTelefono(String correo, String telefono) {
        return modelFactory.validarCorreoTelefono(correo, telefono);
    }

    public boolean contrasenaTemporal(String password, String correo) {
        return modelFactory.contrasenaTemporal(password, correo);
    }
}
