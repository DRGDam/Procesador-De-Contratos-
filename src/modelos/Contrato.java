
package modelos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Contrato {
    private String id;
    private String titulo;
    private String descripcion;
    private String tipo;
    private BigDecimal importe;
    private LocalDate fecha;
    private String adjudicatario;
    private String nif;
    private String organo;
    
  
    public Contrato() {
     
    }
    
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public BigDecimal getImporte() { return importe; }
    public void setImporte(BigDecimal importe) { this.importe = importe; }
    
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    
    public String getAdjudicatario() { return adjudicatario; }
    public void setAdjudicatario(String adjudicatario) { this.adjudicatario = adjudicatario; }
    
    public String getNif() { return nif; }
    public void setNif(String nif) { this.nif = nif; }
    
    public String getOrgano() { return organo; }
    public void setOrgano(String organo) { this.organo = organo; }
}
    

