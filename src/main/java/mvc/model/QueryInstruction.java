package mvc.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Tripulante - 2220320
 */

public class QueryInstruction {
     // Propiedades
    private String sql;
    private ResultSet rs;
    private Statement stm;
    
    public QueryInstruction(String sql, Statement stm){ // Constructor
        this.sql = sql;
        this.stm = stm;
    }
    
    // Metodo para realizar un 'WHERE':
    public QueryInstruction where(String column, String condition, String value){
        
        sql += " WHERE "+column+" "+condition+" "+value;
        return new QueryInstruction(sql, stm);    
    }
    
    // Metodo para obtener los recursos de la consulta:
    public ResultSet get(){
        try{
            rs = stm.executeQuery(sql);
        }catch(SQLException e){
            System.out.println("error: "+e.getMessage());
        }
        return rs;
    }
    
    // Metodo para ejecutar consulta:
    public void execute(){
        try{
            stm.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println("error: "+e.getMessage());
        }
    }
}
