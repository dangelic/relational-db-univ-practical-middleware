package uni.dbprak21.shopmiddleware.model;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uni.dbprak21.shopmiddleware.model.Product;
import uni.dbprak21.shopmiddleware.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Component
public class ProductDTO {
    private final ProductRepository productRepository;
    private final EntityManager entityManager;

    @Autowired
    public ProductDTO(ProductRepository productRepository, EntityManager entityManager) {
        this.productRepository = productRepository;
        this.entityManager = entityManager;
    }

    public List<Product> getProductsByTitlePattern(String pattern) {
        System.out.println(pattern);
        String hql = "FROM Product p WHERE p.productTitle LIKE :pattern";
        TypedQuery<Product> query = entityManager.createQuery(hql, Product.class);
        query.setParameter("pattern", "%" + pattern + "%");
        return query.getResultList();
    }
}