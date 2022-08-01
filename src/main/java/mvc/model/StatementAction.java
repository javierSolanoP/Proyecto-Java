package mvc.model;

import java.util.Map;

/**
 * @author Tripulante - 2220320
 */

public class StatementAction {
    
    // Metodo para convertir un dato en valor:
    private static String convertToValue(String data){
        return "'"+data+"'";
    }
    
    // Metodo para devolver un dato como columna o valor:
    private static String columnOrValue(String data, boolean column){
        if(column){
            return data;
        }else{
            return convertToValue(data);
        }
    }
    
    // Metodo para iterar lista de columnas o valores:
    public static String iteratorOfColumnOrValue(String[] list, boolean column){
        
        String returnedList = "";
        
        for(int i=0; i < list.length; i++){
            
            returnedList += columnOrValue(list[i], column);
            
            if(i != list.length-1){ returnedList += ", "; }
        }
        
        return returnedList;
    }
    
    // Metodo para iterar las columnas y valores de una consulta:
    public static String iiteratorOfColumnOrValue(Map<String, String> map){
        
        String returnedMap = "";
        int counter = 1;
       
        for(Map.Entry<String, String> entry : map.entrySet()){
           
           returnedMap += entry.getKey()+" = "+convertToValue(entry.getValue());
           
           if(counter != map.size()){ returnedMap += ", "; }  
           
           counter++;
        }
       
       return returnedMap;
    }
}
