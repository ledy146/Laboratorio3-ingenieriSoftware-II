package co.edu.unicauca.openmarket.access;

import co.edu.unicauca.openmarket.domain.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Santiago Nieto
 * @author Ledy Astudillo
 */
public class CategoryRepository implements ICategoryRepository {

    private IRepoDataBase objRepository;

    public CategoryRepository(IRepoDataBase reposyT) {
        this.objRepository = reposyT;
    }

   /**
    * @brief Guarda una nueva categoría en una tabla de base de datos llamada "categoría"
    * insertando el nombre de la categoría en la tabla.
    * 
    * @param newCategory Es un objeto de la clase Categoría. Representa la
    * categoría que debe guardarse en la base de datos.
    * @return El método devuelve un valor booleano. Devuelve verdadero si la categoría se guardó
    * correctamente y falso si hay un error o si el objeto newCategory es nulo o tiene un nombre vacío.
    */
    @Override
    public boolean save(Category newCategory) {

        try {
            if (newCategory == null || newCategory.getName().isEmpty()) {
                return false;
            }
            objRepository.connect();
            String sql = "INSERT INTO category ( name ) "
                    + "VALUES ( ? );";

            PreparedStatement pstmt = objRepository.getConection().prepareStatement(sql);
            pstmt.setString(1, newCategory.getName());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            objRepository.disconnect();
        }
    }

   /**
    * @brief La función recupera todas las categorías de una base de datos y las devuelve como una lista.
    * 
    * @return Devuelve una lista de objetos de categoría.
    */
    @Override
    public List<Category> findAll() {

        List<Category> listCategory = new ArrayList<>();
        try {
            String sql = "SELECT * FROM category;";
            objRepository.connect();

            Statement stmt = objRepository.getConection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Category newCategory = new Category();
                newCategory.setCategoryId(rs.getLong("categoryId"));
                newCategory.setName(rs.getString("name"));
                listCategory.add(newCategory);
            }
            rs.close();
            return listCategory;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
            return listCategory;
        } finally {
            objRepository.disconnect();
        }
    }

   /**
    * @brief Actualiza el nombre de una categoría en una base de datos según la ID proporcionada.
    * 
    * @param id Es de tipo Largo y representa el identificador único de la categoría
    * que debe editarse.
    * @param category El parámetro "categoría" es un objeto de la clase Categoría, que contiene
    * información sobre una categoría. Se utiliza para actualizar el nombre de una categoría en la base
    * de datos.
    * @return El método devuelve un valor booleano.
    */
    @Override
    public boolean edit(Long id, Category category) {
        try {

            if (id <= 0 || category == null) {
                return false;
            }
            objRepository.connect();
            String sql = "UPDATE  category "
                    + "SET name=? "
                    + "WHERE categoryId = ?;";

            PreparedStatement pstmt = objRepository.getConection().prepareStatement(sql);
            pstmt.setString(1, category.getName());
            pstmt.setLong(2, id);
            return pstmt.executeUpdate() != 0;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            objRepository.disconnect();
        }
    }

  /**
   * @brief Elimina una categoría de una base de datos según su ID.
   * 
   * @param id Es el identificador único de la categoría que desea eliminar de la
   * base de datos.
   * @return El método devuelve un valor booleano.
   */
    @Override
    public boolean delete(Long id) {
        try {
            if (id <= 0) {
                return false;
            }
            objRepository.connect();
            String sql = "DELETE FROM category "
                    + "WHERE categoryId = ?;";

            PreparedStatement pstmt = objRepository.getConection().prepareStatement(sql);
            pstmt.setLong(1, id);
            return pstmt.executeUpdate() != 0;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            objRepository.disconnect();
        }
    }

  /**
   * @brief Recupera un objeto Categoría de la base de datos según la identificación
   * proporcionada.
   * 
   * @param id Es el identificador único de la categoría que desea encontrar.
   * @return El método devuelve un objeto de tipo Categoría.
   */
    @Override
    public Category findById(Long id) {
        try {

            String sql = "SELECT * FROM category  "
                    + "WHERE categoryId = ?;";

            objRepository.connect();
            PreparedStatement pstmt = objRepository.getConection().prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                Category ctg = new Category();
                ctg.setCategoryId(res.getLong("categoryId"));
                ctg.setName(res.getString("name"));
                res.close();
                return ctg;
            } else {
                res.close();
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            objRepository.disconnect();
        }
    }

    /**
     * @brief Recupera una lista de categorías de una base de datos basada en un
     * nombre determinado.
     * 
     * @param name Es un String que representa el nombre de la categoría que
     * desea buscar.
     * @return El método devuelve una lista de objetos de categoría.
     */
    @Override
    public List<Category> findByName(String name) {
        List<Category> listCategory = new ArrayList<>();
        try {

            String sql = "SELECT * FROM category  "
                    + "WHERE upper(name) = upper(?);";

            objRepository.connect();
            PreparedStatement pstmt = objRepository.getConection().prepareStatement(sql);
            pstmt.setString(1, name);

            ResultSet res = pstmt.executeQuery();

            while (res.next()) {
                Category ctg = new Category();
                ctg.setCategoryId(res.getLong("categoryId"));
                ctg.setName(res.getString("name"));

                listCategory.add(ctg);
            }
            res.close();
            return listCategory;
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
            return listCategory;
        } finally {
            objRepository.disconnect();
        }
    }
}
