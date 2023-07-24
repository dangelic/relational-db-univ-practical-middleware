package uni.dbprak21.shopmiddleware.model;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uni.dbprak21.shopmiddleware.ShopMiddlewareInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Component
public class ProductDTO implements ShopMiddlewareInterface {
    private final EntityManager entityManager;

    @Autowired
    public ProductDTO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Product> getProducts(String pattern) {
        String HQL = "FROM Product p WHERE p.productTitle LIKE :pattern";
        TypedQuery<Product> query = entityManager.createQuery(HQL, Product.class);
        query.setParameter("pattern", "%" + pattern + "%");
        return query.getResultList();
    }

    public List<Product> getTopProducts(int k) {
        String HQL = "FROM Product p WHERE p.averageRating IS NOT NULL ORDER BY p.averageRating DESC"; // not-null constraint as some products are not rated at all.
        TypedQuery<Product> query = entityManager.createQuery(HQL, Product.class);
        query.setMaxResults(k);
        return query.getResultList();
    }
}