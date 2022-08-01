package mvc.controller;

/**
 * @author Tripulante - 2220320
 */

public class ProductoNoRefrigerado extends ProductController {
    
    @Override
    public double calcularCostoDeAlmacenamiento(){
        return this.getValorBase() * 1.1;
    }
}
