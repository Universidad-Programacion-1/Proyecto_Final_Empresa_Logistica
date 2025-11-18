package co.edu.uniquindio.proyecto_final_empresa_logistica.model;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReporteEnvios {

    public enum FormatoReporte { CSV, PDF }

    public static void generarReporte(Collection<Envio> envios, String rutaArchivo, FormatoReporte formato) {
        switch (formato) {
            case CSV -> generarCSV(envios, rutaArchivo);
            case PDF -> generarPDF(envios, rutaArchivo);
        }
    }

    private static void generarCSV(Collection<Envio> envios, String rutaArchivo) {
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            writer.append("ID,Origen,Destino,Peso,Dimensiones,Costo,Estado,Fecha Creacion,Fecha Estimada,Usuario,Repartidor\n");

            for (Envio envio : envios) {
                writer.append(envio.getIdEnvio()).append(",")
                        .append(envio.getOrigen()).append(",")
                        .append(envio.getDestino()).append(",")
                        .append(String.valueOf(envio.getPeso())).append(",")
                        .append(envio.getDimenciones()).append(",")
                        .append(String.valueOf(envio.getCosto())).append(",")
                        .append(envio.getTipoEstadoEnvio().toString()).append(",")
                        .append(envio.getFechaCreacion().toString()).append(",")
                        .append(envio.getFechaEstimadaEntrega().toString()).append(",")
                        .append(envio.getUsuario() != null ? envio.getUsuario().toString() : "N/A").append(",")
                        .append(envio.getRepartidor() != null ? envio.getRepartidor().toString() : "N/A")
                        .append("\n");
            }

            System.out.println("Reporte CSV generado en: " + rutaArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generarPDF(Collection<Envio> envios, String rutaArchivo) {
        try {
            PdfWriter writer = new PdfWriter(rutaArchivo);
            PdfDocument pdf = new PdfDocument(writer);

            Document document = new Document(pdf, PageSize.A4.rotate());

            float[] columnWidths = {1, 2, 2, 1, 2, 1, 2, 2, 2, 2, 2};
            Table table = new Table(columnWidths);

            table.setWidth(UnitValue.createPercentValue(100));

            table.addHeaderCell("ID");
            table.addHeaderCell("Origen");
            table.addHeaderCell("Destino");
            table.addHeaderCell("Peso");
            table.addHeaderCell("Dimensiones");
            table.addHeaderCell("Costo");
            table.addHeaderCell("Estado");
            table.addHeaderCell("Fecha Creación");
            table.addHeaderCell("Fecha Estimada");
            table.addHeaderCell("Usuario");
            table.addHeaderCell("Repartidor");

            for (Envio envio : envios) {
                table.addCell(envio.getIdEnvio() != null ? envio.getIdEnvio().toString() : "N/A");
                table.addCell(envio.getOrigen());
                table.addCell(envio.getDestino());
                table.addCell(String.valueOf(envio.getPeso()));
                table.addCell(envio.getDimenciones());
                table.addCell(String.valueOf(envio.getCosto()));
                table.addCell(envio.getTipoEstadoEnvio() != null ? envio.getTipoEstadoEnvio().toString() : "N/A");
                table.addCell(envio.getFechaCreacion() != null ? envio.getFechaCreacion().toString() : "N/A");
                table.addCell(envio.getFechaEstimadaEntrega() != null ? envio.getFechaEstimadaEntrega().toString() : "N/A");
                table.addCell(envio.getUsuario() != null ? envio.getUsuario().toString() : "N/A");
                table.addCell(envio.getRepartidor() != null ? envio.getRepartidor().toString() : "N/A");
            }
            document.add(new Paragraph("Reporte de Envíos\n\n"));
            document.add(table);
            document.close();

            System.out.println("Reporte PDF generado en: " + rutaArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
