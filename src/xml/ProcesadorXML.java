
package xml;

import conexion.ConfiguracionDB;
import java.io.File;
import modelos.Contrato;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.util.List;

public class ProcesadorXML {
    
     public List<Contrato> procesar() {
        try {
            File archivo = new File(ConfiguracionDB.XML_ORIGEN);
            if (!archivo.exists()) {
                System.out.println("ERROR: Archivo no encontrado: " + ConfiguracionDB.XML_ORIGEN);
                return null;
            }
            
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            SAXHandler handler = new SAXHandler();
            
            System.out.println("Procesando XML...");
            saxParser.parse(archivo, handler);
            
            List<Contrato> contratos = handler.getContratos();
            System.out.println("Procesados: " + contratos.size() + " contratos");
            
            return contratos;
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}