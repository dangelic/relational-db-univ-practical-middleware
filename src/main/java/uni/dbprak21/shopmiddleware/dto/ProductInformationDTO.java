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
public class ProductInformationDTO {
    private final EntityManager entityManager;

    @Autowired
    public ProductInformationDTO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Benötigte Relationen im Hibernate-Model: nur Product ohne Verknüpfung
    public List<Product> getProducts(String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            // Wenn String NULL ist, gebe alles zurück
            String allProductsHQL = "FROM Product";
            TypedQuery<Product> allProductsQuery = entityManager.createQuery(allProductsHQL, Product.class);
            return allProductsQuery.getResultList();
        } else {
            // Wenn String NICHT NULL, dann suche über ILIKE (case-insensitive LIKE; doppelte Sicherheit, da pattern.toLowerCase() aufgerufen wird)
            String productSearchHQL = "FROM Product p WHERE p.productTitle ILIKE :pattern";
            TypedQuery<Product> productSearchQuery = entityManager.createQuery(productSearchHQL, Product.class);
            productSearchQuery.setParameter("pattern", "%" + pattern.toLowerCase() + "%"); // case-insensitive
            return productSearchQuery.getResultList();
        }
    }
}