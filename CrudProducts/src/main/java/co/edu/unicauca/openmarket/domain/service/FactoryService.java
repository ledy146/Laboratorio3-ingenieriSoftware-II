package co.edu.unicauca.openmarket.domain.service;

import co.edu.unicauca.openmarket.access.CategoryRepository;
import co.edu.unicauca.openmarket.access.Factory;
import co.edu.unicauca.openmarket.access.ProductRepository;

/**
 * @author Santiago Nieto
 * @author Ledy Astudillo
 */

public class FactoryService {

    public CategoryService category;
    public ProductService product;

    public FactoryService() {
        product = new ProductService((ProductRepository) Factory.getInstance().getRepository("Product"));
        category = new CategoryService((CategoryRepository) Factory.getInstance().getRepository("Category"));
    }
}
