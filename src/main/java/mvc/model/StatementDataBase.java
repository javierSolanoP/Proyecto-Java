package mvc.model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 * @author Tripulante - 2220320
 */

public class StatementDataBase implements Crud {
    
    // Propiedades
    private ConnectionDataBase connection;
    private Statement stm;
    protected String tablename;
    
    public StatementDataBase() throws SQLException { // Constructor
        try{
            connection = new ConnectionDataBase();
            stm = connection.getConnection().createStatement();
        }catch(SQLException e){
            System.out.println("error: "+e.getMessage());
        }
    }
    
    // Metodo para crear un recurso en la base de datos:
    @Override
    public void create(String[] columns, String[] values){
        
        String sql = "INSERT INTO "+tablename+"("+StatementAction.iteratorOfColumnOrValue(columns, true)+") "
                    + "VALUES ("+StatementAction.iteratorOfColumnOrValue(values, false)+")";        
        try{
            stm.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println("error: "+e.getMessage());
        }
    }   
    
    // Metodo para enlistar los recursos de la consulta:
    @Override
    public QueryInstruction read(String[] columns){
        
        String sql = "SELECT ";
        
        if(columns != null){
            sql += StatementAction.iteratorOfColumnOrValue(columns, true);
        }else{
            sql += "*";
        }
        
        sql += " FROM "+tablename;
        
        return new QueryInstruction(sql, stm);
    }
    
    // Metodo para actualizar recursos de la base de datos:
    @Override
    public QueryInstruction update(Map<String, String> columnAndValue){
        
        String sql = "UPDATE "+tablename+" SET "+StatementAction.iiteratorOfColumnOrValue(columnAndValue);        
        return new QueryInstruction(sql, stm);
    }
    
    // Metodo para eliminar un recurso de la base de datos:
    @Override
    public QueryInstruction delete(){
        
        String sql = "DELETE FROM "+tablename;
        return new QueryInstruction(sql, stm);
    }
}