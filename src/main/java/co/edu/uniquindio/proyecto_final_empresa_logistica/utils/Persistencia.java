package co.edu.uniquindio.proyecto_final_empresa_logistica.utils;

import co.edu.uniquindio.proyecto_final_empresa_logistica.model.EmpresaLogistica;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Persistencia {

    public static final String RUTA_ARCHIVO_MODELO = "src/main/resources/modelo.xml";

    public static void guardarRecursoXML(EmpresaLogistica empresaLogistica) {
        try (XMLEncoder codificador = new XMLEncoder(new FileOutputStream(RUTA_ARCHIVO_MODELO))) {
            codificador.writeObject(empresaLogistica);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static EmpresaLogistica cargarRecursoXML() {
        try (XMLDecoder decodificador = new XMLDecoder(new FileInputStream(RUTA_ARCHIVO_MODELO))) {
            return (EmpresaLogistica) decodificador.readObject();
        } catch (Exception e) {
            return null;
        }
    }
}