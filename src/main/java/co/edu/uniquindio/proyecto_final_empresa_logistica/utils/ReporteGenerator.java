package co.edu.uniquindio.proyecto_final_empresa_logistica.utils;

import co.edu.uniquindio.proyecto_final_empresa_logistica.model.Envio;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Collection;

public class ReporteGenerator {

    public static void generarCSV(File file, Collection<Envio> envios) throws IOException {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("ID Envío,Origen,Destino,Costo,Estado,Fecha Creación");

            for (Envio envio : envios) {
                String linea = String.join(",",
                        envio.getIdEnvio(),
                        envio.getOrigen(),
                        envio.getDestino(),
                        String.valueOf(envio.getCosto()),
                        envio.getTipoEstadoEnvio().name(),
                        envio.getFechaCreacion().toString()
                );
                writer.println(linea);
            }
        }
    }

}