package mvc.controller;

/**
 * @author Tripulante - 2220320
 */

public class ProductoRefrigerado extends ProductController {
    
    @Override
    public double calcularCostoDeAlmacenamiento(){
        return this.getValorBase() * 1.2;
    }
}
