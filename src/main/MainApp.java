
package main;

import basedatos.GestorContratosDB;
import xml.ProcesadorXML;
import xml.GeneradorXML;
import modelos.Contrato;
import java.util.List;

public class MainApp {
    
    public static void main(String[] args) {
        System.out.println("=== PROCESADOR DE CONTRATOS ===");
        
        try {
            // 1. Procesar XML
            ProcesadorXML procesador = new ProcesadorXML();
            List<Contrato> contratos = procesador.procesar();
            
            if (contratos == null || contratos.isEmpty()) {
                System.out.println("No hay contratos para procesar");
                return;
            }
            
            // 2. Base de datos
            GestorContratosDB gestorDB = new GestorContratosDB();
            gestorDB.crearTabla();
            gestorDB.insertar(contratos);
            
            // 3. Genera XML
            GeneradorXML generador = new GeneradorXML();
            generador.generarSinTipo(contratos);
            
            System.out.println("=== PROCESO COMPLETADO ===");
            
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}