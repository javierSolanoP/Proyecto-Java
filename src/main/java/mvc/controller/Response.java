package mvc.controller;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tripulante - 2220320
 * @param <K>
 * @param <V>
 */
public class Response<K, V> {
    
    private K key;
    private V value;
    private List<Response> array;
    
    public Response(){ // Constructor
        array = new ArrayList<>();
    }
    
    // Metodo para establecer la llave y el valor:
    private void set(K key, V value){
        this.key = key;
        this.value = value;
    }
    
    // Metodo para aniadir al array el objeto de respuesta:
    public void put(K key, V value){
        Response<K,V> r = new Response<>();
        r.set(key, value);
        array.add(r);
    }
    
    // Metodo para retornar el valor correspondiente a la llave:
    public V get(K key){
        V r = null;
        for(Response object: array){
            if(object.key == key){
                r = (V)object.value;
            }
        }
        return r;
    }
}
