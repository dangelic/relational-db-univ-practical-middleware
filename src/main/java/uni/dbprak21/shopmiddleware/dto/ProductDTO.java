package uni.dbprak21.shopmiddleware.dto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uni.dbprak21.shopmiddleware.ShopMiddlewareInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import uni.dbprak21.shopmiddleware.model.Product;

@Component
public class ProductDTO implements ShopMiddlewareInterface {
    private final EntityManager entityManager;

    @Autowired
    public ProductDTO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Product> getProducts(String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            // If the pattern is empty or null, return all products
            String allProductsHQL = "FROM Product";
            TypedQuery<Product> allProductsQuery = entityManager.createQuery(allProductsHQL, Product.class);
            return allProductsQuery.getResultList();
        } else {
            // If the pattern is not empty, search for products based on the pattern
            String productSearchHQL = "FROM Product p WHERE p.productTitle LIKE :pattern";
            TypedQuery<Product> productSearchQuery = entityManager.createQuery(productSearchHQL, Product.class);
            productSearchQuery.setParameter("pattern", "%" + pattern.toLowerCase() + "%");
            return productSearchQuery.getResultList();
        }
    }

    public List<Product> getTopProducts(int k) {
        String HQL = "FROM Product p WHERE p.averageRating IS NOT NULL ORDER BY p.averageRating DESC"; // not-null constraint as some products are not rated at all.
        TypedQuery<Product> query = entityManager.createQuery(HQL, Product.class);
        query.setMaxResults(k);
        return query.getResultList();
    }
}