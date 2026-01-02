
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

public class GeneradorXML {
    
    public void generarSinTipo(List<Contrato> contratos) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            
            Element raiz = doc.createElement("contratos");
            doc.appendChild(raiz);
            
            for (Contrato c : contratos) {
                Element contratoElem = doc.createElement("contrato");
                
                addElement(doc, contratoElem, "titulo", c.getTitulo());
                addElement(doc, contratoElem, "descripcion", c.getDescripcion());
                addElement(doc, contratoElem, "importe", c.getImporte().toString());
                addElement(doc, contratoElem, "fecha", c.getFecha().toString());
                addElement(doc, contratoElem, "adjudicatario", c.getAdjudicatario());
                addElement(doc, contratoElem, "nif", c.getNif());
                addElement(doc, contratoElem, "organo", c.getOrgano());
                
                raiz.appendChild(contratoElem);
            }
            
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(ConfiguracionDB.XML_SALIDA));
            
            transformer.transform(source, result);
            System.out.println("XML generado: " + ConfiguracionDB.XML_SALIDA);
            
        } catch (Exception e) {
            System.out.println("Error generando XML: " + e.getMessage());
        }
    }
    
    private void addElement(Document doc, Element parent, String name, String value) {
        if (value != null) {
            Element elem = doc.createElement(name);
            elem.appendChild(doc.createTextNode(value));
            parent.appendChild(elem);
        }
    }
}