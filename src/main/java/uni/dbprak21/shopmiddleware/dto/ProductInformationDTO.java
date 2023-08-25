package uni.dbprak21.shopmiddleware.dto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uni.dbprak21.shopmiddleware.ShopMiddleware;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import uni.dbprak21.shopmiddleware.model.PriceInfo;
import uni.dbprak21.shopmiddleware.model.Product;

@Component
public class ProductInformationDTO implements ShopMiddleware {
    private final EntityManager entityManager;

    @Autowired
    public ProductInformationDTO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Product> getProduct(String productId) {
        System.out.println(productId);
        String HQL = "FROM Product p WHERE p.productId LIKE :productId";
        TypedQuery<Product> query = entityManager.createQuery(HQL, Product.class);
        query.setParameter("productId", productId);
        return query.getResultList();
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
}