
package xml;

import modelos.Contrato;
import conexion.ConfiguracionDB;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import javax.xml.transform.OutputKeys;

public class GeneradorXML {
    
    public void generarSinTipo(List<Contrato> contratos) {
        if (contratos == null || contratos.isEmpty()) {
            System.err.println("Alerta: La lista está vacía. No se generará el XML.");
            return;
        }
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            
            // Creamos la raíz
            Element raiz = doc.createElement("contratos");
            doc.appendChild(raiz);
            
            int contador = 0;
            for (Contrato c : contratos) {
              
                Element contratoElem = doc.createElement("contrato");
            
                String importeStr = (c.getImporte() != null) ? c.getImporte().toPlainString() : "0.00";
                String fechaStr = (c.getFecha() != null) ? c.getFecha().toString() : "0000-00-00";
                
                // Añadimos  hijos
                addElement(doc, contratoElem, "titulo", c.getTitulo());
                addElement(doc, contratoElem, "descripcion", c.getDescripcion());
                addElement(doc, contratoElem, "importe", importeStr);
                addElement(doc, contratoElem, "fecha", fechaStr);
                addElement(doc, contratoElem, "adjudicatario", c.getAdjudicatario());
                addElement(doc, contratoElem, "nif", c.getNif());
                addElement(doc, contratoElem, "organo", c.getOrgano());
                
                raiz.appendChild(contratoElem);
                contador++;
            }
            
          
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer tf = transformerFactory.newTransformer();
            
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            tf.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(ConfiguracionDB.XML_SALIDA));
            tf.transform(source, result);
            
            System.out.println("ÉXITO: XML generado en " + ConfiguracionDB.XML_SALIDA + " con " + contador + " registros.");
            
        } catch (Exception e) {
            System.err.println("Error crítico generando XML: " + e.getMessage());
            e.printStackTrace(); 
        }
    }
    
    private void addElement(Document doc, Element parent, String name, String value) {
       
        String textoFinal = (value != null) ? value : "N/A";
        Element elem = doc.createElement(name);
        elem.appendChild(doc.createTextNode(textoFinal));
        parent.appendChild(elem);
    }
}