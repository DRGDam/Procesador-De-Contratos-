
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionMySQL {
   public static Connection conectar() {
        try {
            Class.forName(ConfiguracionDB.DRIVER);
            Connection conn = DriverManager.getConnection(
                ConfiguracionDB.URL, 
                ConfiguracionDB.USUARIO, 
                ConfiguracionDB.PASSWORD
            );
            System.out.println("Conectado a MariaDB");
            return conn;
        } catch (Exception e) {
            System.out.println("Error conexión: " + e.getMessage());
            return null;
        }
    }
    
    public static void cerrar(Connection conn) {
        try {
            if (conn != null) conn.close();
        } catch (Exception e) {
            System.out.println("Error cerrando: " + e.getMessage());
        }
    }
}