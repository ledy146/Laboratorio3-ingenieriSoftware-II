package co.edu.unicauca.openmarket.access;

import co.edu.unicauca.openmarket.domain.Product;
import java.util.List;

/**
 * Interfaz para un repositorio de producto
 * @author Libardo, Julio
 */
public interface IProductRepository {
    
    boolean save(Product newProducto);

    boolean delete(Long id);

    boolean edit(Long id, Product producto);

    List<Product> findAll();

    Product findById(Long id);

    List<Product> findByName(String name);
    
    List<Product> findByCategory(Long id);  
}
