
package co.edu.unicauca.openmarket.main;

import co.edu.unicauca.openmarket.domain.service.FactoryService;
import co.edu.unicauca.openmarket.presentation.GUIProducts;

/**
 *
 * @author Libardo Pantoja
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        FactoryService objFservice = new FactoryService();
        
        GUIProducts instance = new GUIProducts(objFservice);
        
        instance.setVisible(true);
    }
    
}
