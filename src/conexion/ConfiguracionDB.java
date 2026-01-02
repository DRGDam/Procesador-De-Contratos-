
package conexion;


 public class ConfiguracionDB {
    
    public static final String URL = "jdbc:mariadb://localhost:3306/contratos_andalucia?useUnicode=true&characterEncoding=UTF-8";
    public static final String USUARIO = "root";
    public static final String PASSWORD = "root123";
   
    public static final String DRIVER = "org.mariadb.jdbc.Driver";
    

    public static final String XML_ORIGEN = "contratos.xml";
    public static final String XML_SALIDA = "contratos_sin_tipo_contrato.xml";
}