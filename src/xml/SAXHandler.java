
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
    public void startElement(String uri, String local, String qName, Attributes atts) {
        contenido.setLength(0);
        
       
        if ("contrato".equalsIgnoreCase(qName)) {
            contratoActual = new Contrato();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
       
        contenido.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String local, String qName) {
        String texto = contenido.toString().trim();
        String tag = qName.toUpperCase(); 

        if ("contrato".equalsIgnoreCase(qName)) {
            if (contratoActual != null) {
                  contratoActual.setId("ID-" + System.currentTimeMillis() + "-" + contratos.size());
                
               
                if (contratoActual.getTitulo() != null || contratoActual.getAdjudicatario() != null) {
                    contratos.add(contratoActual);
                 
                }
            }
            contratoActual = null;
        } else if (contratoActual != null && !texto.isEmpty()) {
            switch (tag) {
                case "TITULO": 
                    contratoActual.setTitulo(texto); 
                    break;
                case "DESCRIPCION": 
                    contratoActual.setDescripcion(texto); 
                    break;
                case "TIPO_DE_CONTRATO": 
                    contratoActual.setTipo(texto); 
                    break;
                case "IMPORTE": 
                    try {
                        String limpio = texto.replace(".", "").replace(",", ".").replaceAll("[^0-9.]", "");
                        contratoActual.setImporte(new BigDecimal(limpio)); 
                    } catch(Exception e){
                        System.err.println("Error en importe: " + texto);
                     
                        contratoActual.setImporte(BigDecimal.ZERO);
                    }
                    break;
                case "FECHA_ADJUDICACION":
                    try { 
                        contratoActual.setFecha(LocalDate.parse(texto, DateTimeFormatter.ofPattern("dd/MM/yyyy"))); 
                    } catch(Exception e){
                        System.err.println("Error en fecha: " + texto);
                     
                        contratoActual.setFecha(LocalDate.now());
                    }
                    break;
                case "ADJUDICATARIO": 
                    contratoActual.setAdjudicatario(texto); 
                    break;
                case "NIF_ADJUDICATARIO": 
                    contratoActual.setNif(texto); 
                    break;
                case "ORGANO_CONTRATACION": 
                    contratoActual.setOrgano(texto); 
                    break;
            }
        }
    }

    public List<Contrato> getContratos() {
        return contratos;
    }
}