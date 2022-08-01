package mvc.model;

import java.util.Map;

/**
 * @author Tripulante - 2220320
 */

public interface Crud {
    
    // Metodo para crear un recurso en la base de datos:
    public void create(String[] columns, String[] values);
    
    // Metodo para enlistar los recursos de la consulta:
    public QueryInstruction read(String[] columns);
    
    // Metodo para actualizar recursos de la base de datos:
    public QueryInstruction update(Map<String, String> columnAndValue);
    
    // Metodo para eliminar un recurso de la base de datos:
    public QueryInstruction delete();    
}