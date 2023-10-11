package co.edu.unicauca.openmarket.domain.service;

import co.edu.unicauca.openmarket.access.ICategoryRepository;
import co.edu.unicauca.openmarket.domain.Category;
import java.util.List;

/**
 * @author Santiago Nieto
 * @author Ledy Astudillo
 */
public class CategoryService {

    // Ahora hay una dependencia de una abstracción, no es algo concreto,
    // no sabe cómo está implementado.
    private ICategoryRepository repository;

    /**
     * Inyección de dependencias en el constructor. Ya no conviene que el mismo
     * servicio cree un repositorio concreto
     *
     * @param repository una clase hija de ICategoryRepository
     */
    public CategoryService(ICategoryRepository repository) {
        this.repository = repository;
    }

    public boolean saveCategory(String name) {

        Category newCategory = new Category();
        newCategory.setName(name);

        if (newCategory.getName().isEmpty()) {
            return false;
        }

        return repository.save(newCategory);

    }

    public boolean deleteCategory(Long id) {
        return repository.delete(id);
    }

    public boolean editCategory(Long categoryId, Category category) {

        if (category == null || category.getName().isEmpty()) {
            return false;
        }
        return repository.edit(categoryId, category);
    }

    public List<Category> findAllCategories() {
        return repository.findAll();
    }

    public Category findCategoryById(Long id) {
        return repository.findById(id);
    }

    public List<Category> findCategoryByName(String name) {
        return repository.findByName(name);
    }
}
