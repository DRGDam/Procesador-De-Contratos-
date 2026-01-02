
package basedatos;

import modelos.Contrato;
import conexion.ConexionMySQL;
import java.sql.*;
import java.util.List;

public class GestorContratosDB {
    
      public void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS contratos (" +
                     "id VARCHAR(100) PRIMARY KEY, " +
                     "titulo VARCHAR(500), " +
                     "descripcion TEXT, " +
                     "tipo VARCHAR(100), " +
                     "importe DECIMAL(15,2), " +
                     "fecha DATE, " +
                     "adjudicatario VARCHAR(300), " +
                     "nif VARCHAR(20), " +
                     "organo VARCHAR(300))";
        
        try (Connection conn = ConexionMySQL.conectar();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute(sql);
            System.out.println("Tabla creada/verificada");
            
        } catch (Exception e) {
            System.out.println("Error tabla: " + e.getMessage());
        }
    }
    
    public void insertar(List<Contrato> contratos) {
        String sql = "INSERT INTO contratos VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionMySQL.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            for (Contrato c : contratos) {
                pstmt.setString(1, c.getId());
                pstmt.setString(2, c.getTitulo());
                pstmt.setString(3, c.getDescripcion());
                pstmt.setString(4, c.getTipo());
                pstmt.setBigDecimal(5, c.getImporte());
                pstmt.setDate(6, Date.valueOf(c.getFecha()));
                pstmt.setString(7, c.getAdjudicatario());
                pstmt.setString(8, c.getNif());
                pstmt.setString(9, c.getOrgano());
                pstmt.addBatch();
            }
            
            pstmt.executeBatch();
            System.out.println("Insertados: " + contratos.size() + " contratos");
            
        } catch (Exception e) {
            System.out.println("Error insertando: " + e.getMessage());
        }
    }
}