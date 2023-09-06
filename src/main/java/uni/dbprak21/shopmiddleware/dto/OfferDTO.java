package uni.dbprak21.shopmiddleware.dto;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uni.dbprak21.shopmiddleware.ShopMiddleware;
import uni.dbprak21.shopmiddleware.model.PriceInfo;
import uni.dbprak21.shopmiddleware.model.Product;

@Component
public class OfferDTO {

    // Benötigte Relationen im Hibernate-Model:
    //                - getOffers:
    //                          - @ManyToOne auf PriceInfo - Produkte: Mehrere PreisInfos für ein Produkt
    //                          - @ManyToOne auf Shops - PriceInfos: Mehrere PreisInfos für einen Shop
    //                - getSimilarCheaperProduct:
    //                          - ...
    //                          - ...
    //                          - Zusatz: similar_products Relation: @ManyToOne auf Similar-Produkt auf Produkt - mehrere ähnliche Produkte auf ein Produkt

    private final EntityManager entityManager;

    @Autowired
    public OfferDTO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<PriceInfo> getOffers(String productId) {
        String HQL = "FROM PriceInfo pi WHERE pi.product.productId = :productId";
        TypedQuery<PriceInfo> query = entityManager.createQuery(HQL, PriceInfo.class);
        query.setParameter("productId", productId);
        return query.getResultList();
    }

    public List<Product> getSimilarCheaperProduct(String productId) {
        // Schritt 1: Gebe alle ähnlichen Produkte für ein gegebenes Produkt X aus
        String similarProductsHQL =
                "SELECT ps.similarProduct " +
                        "FROM ProductSimilar ps " +
                        "WHERE ps.product.productId = :productId";

        TypedQuery<Product> similarProductsQuery = entityManager.createQuery(similarProductsHQL, Product.class);
        similarProductsQuery.setParameter("productId", productId);
        List<Product> similarProducts = similarProductsQuery.getResultList();

        if (similarProducts.isEmpty()) return Collections.emptyList(); // Nix Ähnliches gefunden

        // Schritt 2: Gebe für Produkt X den niedrigsten Preis aus (da es mehrere Angebote pro Produkt geben kann)
        String cheapestPriceHQL =
                "SELECT MIN(pi.price) FROM PriceInfo pi " +
                        "WHERE pi.product.productId = :productId " +
                        "AND pi.price IS NOT NULL";

        TypedQuery<Float> cheapestPriceQuery = entityManager.createQuery(cheapestPriceHQL, Float.class);
        cheapestPriceQuery.setParameter("productId", productId);
        Float cheapestPrice = cheapestPriceQuery.getSingleResult();

        // Schritt 3: Gebe alle ähnlichen Produkte aus, die billiger als der billigste Preis des gegebenen Produktes X sind
        List<Product> similarCheaperProducts = new ArrayList<>();

        // Loop durch alle ähnlichen Produkte zu Produkt X und hole deren Preis (wenn er existiert, also NOT NULL ist), egal wie hoch er ist
        for (Product similarProduct : similarProducts) {
            String similarProductCheapestPriceHQL =
                    "SELECT MIN(pi.price) " +
                            "FROM PriceInfo pi " +
                            "WHERE pi.product.productId = :simProductId " +
                            "AND pi.price IS NOT NULL";

            TypedQuery<Float> similarProductCheapestPriceQuery = entityManager.createQuery(similarProductCheapestPriceHQL, Float.class);
            similarProductCheapestPriceQuery.setParameter("simProductId", similarProduct.getProductId());

            Float similarProductCheapestPrice = null;
            try {
                similarProductCheapestPrice = similarProductCheapestPriceQuery.getSingleResult();
            } catch (NoResultException e) {
                // Case: Query ist leer
            } catch (Exception e) {
                continue; // Skip
            }

            // Check auf similarProductCheapestPrice is not null vor Nutzung
            if (similarProductCheapestPrice != null && similarProductCheapestPrice < cheapestPrice) {
                similarCheaperProducts.add(similarProduct);
            }
        }

        return similarCheaperProducts;
    }

}

/** ---------- DEBUG-QUERY für getSimilarCheaperProduct
 * SELECT DISTINCT p.*
 * FROM products p
 * JOIN products_similars ps ON p.asin = ps.products_asin
 * JOIN priceinfos pi1 ON ps.similar_product_asin = pi1.products_asin
 * JOIN priceinfos pi2 ON p.asin = pi2.products_asin
 * WHERE pi1.price IS NOT NULL AND pi2.price IS NOT NULL
 * AND pi2.price > pi1.price;
 */
