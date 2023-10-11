package co.edu.unicauca.openmarket.access;

/**
 * Fabrica que se encarga de instanciar ProductRepository o cualquier otro que
 * se cree en el futuro.
 *
 * @author Libardo, Julio
 */
public class Factory {

    private static Factory instance;
    private static IRepoDataBase repository;
    
    private Factory() {
    }

    /**
     * Clase singleton
     *
     * @return
     */
    public static Factory getInstance() {

        if (instance == null) {
            instance = new Factory();
            repository = new RepoDataBase();
        }
        return instance;
    }

    /**
     * Método que crea una instancia de un repositorio
     *
     * @param type cadena que indica qué tipo de repositorio instanciar
     * @return Objeto del repositorio concreto que se desea instanciar
     */
    public Object getRepository(String type) {

        Object result = null;
        
        switch (type) {
            case "Product": //se establece el tipo Product para crear la instancia del mismo
                result = new ProductRepository(repository);
                break;
            case "Category"://se establece el tipo Category para crear la instancia del mismo
                result = new CategoryRepository(repository);
                break;
        }
        return result;
    }
}
