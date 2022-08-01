package mvc.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import mvc.model.ProductModel;

/**
 * @author Tripulante - 2220320
 */

public class ProductController {

    // Propiedades
    private String nombre;
    private String id;
    private double temperatura;
    private double valorBase;
    
    public ProductController(String nombre, String id, double temperatura, double valorBase){ // Constructor con parametros
        this.nombre = nombre;
        this.id = id;
        this.temperatura = temperatura;
        this.valorBase = valorBase;
    }
    
    public ProductController(){ // Constructor sin parametos:
        nombre = "none";
        id = "none";
        temperatura = 0;
        valorBase = 0;
    } 
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getValorBase() {
        return valorBase;
    }
    
    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }
    
    public double calcularCostoDeAlmacenamiento(){
        
        double costo = 0;
        
        if(this.temperatura >= 0 && this.temperatura<= 20){
            
            ProductoRefrigerado pRefrigerado = new ProductoRefrigerado();
            pRefrigerado.setValorBase(this.valorBase);
            costo = pRefrigerado.calcularCostoDeAlmacenamiento();
            
        }else if(this.temperatura >= 21){
            
            ProductoNoRefrigerado pNoRefrigerado = new ProductoNoRefrigerado();
            pNoRefrigerado.setValorBase(this.valorBase);
            costo = pNoRefrigerado.calcularCostoDeAlmacenamiento();
        }
        
        return costo;
    }
   
    // Metodo para crear un producto en la base de datos:
    public Response create() throws SQLException{
        
        Response response = new Response<>();
        
        if(!nombre.equals("") && !id.equals("")){
            
            ProductModel model = new ProductModel();
            
            ResultSet validateProduct = model.read(null).where("id", "=", id).get();
            
            if(!validateProduct.next()){
                
                String[] columns = {"nombre", "id", "temperatura", "valor_base"},
                         values = {nombre, id, String.valueOf(temperatura), String.valueOf(valorBase)};
                
                model.create(columns, values);
                
                response.put("create", true);
                return response;
                
            }else{
                response.put("create", false);
                response.put("error", "Ya existe un producto creado con ese 'id'.");
                return response;
            }
            
        }else{
            response.put("create", false);
            response.put("error", "Debe tener informacion completa del producto.");
            return response;
        } 
    }
    
    // Metodo para enlistar los productos de la base de datos:
    public Response read(String[] columns, String id) throws SQLException{
        
        Response response = new Response<>();
        
        ProductModel model = new ProductModel();
        ResultSet products;
        
        if(Objects.isNull(id)){
            products = model.read(columns).get();
        }else{
            products = model.read(columns).where("id", "=", id).get();
        }
        
        if(products.next()){

            response.put("read", true);
            response.put("products", products);
            return response;
            
        }else{
            response.put("read", false);
            response.put("error", "No existen productos creados en el sistema.");
            return response;
        }     
    }
    
    // Metodo para actualizar un producto de la base de datos:
    public Response update(Map<String, String> columnAndValue, String id) throws SQLException{
        
        Response response = new Response<>();
        ProductModel model = new ProductModel();
            
        ResultSet validateProduct = model.read(null).where("id", "=", id).get();

        if(!Objects.isNull(validateProduct)){

            model.update(columnAndValue).where("id", "=", id).execute();

            response.put("update", true);
            return response;

        }else{
            response.put("update", false);
            response.put("error", "No existe un producto con ese 'id'.");
            return response;
        }
    }
    
    // Metodo para eliminar un producto de la base de datos:
    public Response delete(String id) throws SQLException{
        
        Response response = new Response<>();
        ProductModel model = new ProductModel();
            
        ResultSet validateProduct = model.read(null).where("id", "=", id).get();

        if(validateProduct.next()){

            model.delete().where("id", "=", id).execute();

            response.put("delete", true);
            return response;

        }else{
            response.put("delete", false);
            response.put("error", "No existe un producto con ese 'id'.");
            return response;
        }
    }
}