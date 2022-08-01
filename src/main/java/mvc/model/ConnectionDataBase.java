package mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Tripulante - 2220320
 */

public class ConnectionDataBase {
    
    private Connection connection = null;
    private final static String DBMS = "mysql";
    private final static String DB_NAME = "reto_05";
    private final static String URL = "jdbc:"+DBMS+"://localhost:3306/"+DB_NAME;
    private final static String USER_DB = "root";
    private final static String PASSWORD_DB = "";
    private final static String DRIVER = "com."+DBMS+".cj.jdbc.Driver";
    
    public ConnectionDataBase(){ // Constructor
        
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER_DB, PASSWORD_DB);
            System.out.println("Conexion exitosa!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("error de conexion.");
        }
        
    }
    
    public Connection getConnection(){
        return connection;
    }
}