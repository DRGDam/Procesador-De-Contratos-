
package xml;

import modelos.Contrato;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SAXHandler extends DefaultHandler {
   private List<Contrato> contratos = new ArrayList<>();
    private Contrato contratoActual;
    private StringBuilder contenido = new StringBuilder();
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        contenido.setLength(0);
        if ("contrato".equals(qName)) {
            contratoActual = new Contrato();
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) {
        contenido.append(ch, start, length);
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) {
        String texto = contenido.toString().trim();
        
        if ("contrato".equals(qName)) {
            if (contratoActual != null) {
                contratoActual.setId("ID-" + System.currentTimeMillis() + "-" + contratos.size());
                contratos.add(contratoActual);
            }
            contratoActual = null;
        } else if (contratoActual != null && !texto.isEmpty()) {
            switch (qName) {
                case "TITULO": contratoActual.setTitulo(texto); break;
                case "DESCRIPCION": contratoActual.setDescripcion(texto); break;
                case "TIPO_DE_CONTRATO": contratoActual.setTipo(texto); break;
                case "IMPORTE": 
                    try {
                        String limpio = texto.replace(",", ".");
                        contratoActual.setImporte(new BigDecimal(limpio));
                    } catch (Exception e) {
                        System.out.println("Error en importe: " + texto);
                    }
                    break;
                case "FECHA_ADJUDICACION":
                    try {
                        contratoActual.setFecha(LocalDate.parse(texto, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    } catch (Exception e) {
                        System.out.println("Error en fecha: " + texto);
                    }
                    break;
                case "ADJUDICATARIO": contratoActual.setAdjudicatario(texto); break;
                case "NIF_ADJUDICATARIO": contratoActual.setNif(texto); break;
                case "ORGANO_CONTRATACION": contratoActual.setOrgano(texto); break;
            }
        }
    }
    
    public List<Contrato> getContratos() {
        return contratos;
    }
}
