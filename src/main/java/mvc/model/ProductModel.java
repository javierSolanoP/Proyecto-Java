package mvc.model;

import java.sql.SQLException;

/**
 * @author Tripulante - 2220320
 */

public class ProductModel extends StatementDataBase{
    
    public ProductModel() throws SQLException{
        super();
        tablename = "producto";
    } // Constructor
}
