package co.edu.unicauca.openmarket.access;

import co.edu.unicauca.openmarket.domain.Category;
import java.util.List;

/**
 
 * @author Ledy Astudillo
 * @author Santiago Nieto
 */
public interface ICategoryRepository {
    
    boolean save(Category newCategory);
    
    boolean delete(Long id);

    boolean edit(Long id, Category category);

    List<Category> findAll();

    Category findById(Long id);

    List<Category> findByName(String name);

        
}
