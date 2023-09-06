package uni.dbprak21.shopmiddleware.dto;

import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uni.dbprak21.shopmiddleware.ShopMiddleware;
import jakarta.persistence.EntityManager;
import uni.dbprak21.shopmiddleware.model.GuestReview;
import uni.dbprak21.shopmiddleware.model.Product;
import uni.dbprak21.shopmiddleware.model.User;
import uni.dbprak21.shopmiddleware.model.UserReview;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class ReviewDTO {

    // Benötigte Relationen im Hibernate-Model:
    //          - addNewReview:
    //                  - @ManyToOne auf Produkte-Tabelle: (aus Sicht der Bewertungen) mehrere Bewertungen für ein Produkt
    //                  - NUR bei Nutzern: @ManyToOne auf User-Tabelle: (aus Sicht der Bewertungen) mehrere Nutzer bewerten selbes Produkt
    //          - getTrolls:
    //                  - @ManyToOne auf User-Tabelle: (aus Sicht der Bewertungen) mehrere Nutzer bewerten selbes Produkt
    //          - getTopProducts:
    //                  - Nur Produkte-Relation, da AVG-Bewertungen hier auch drinstehen (durch Trigger-Funktion aus Testat 2)

    private final EntityManager entityManager;

    @Autowired
    public ReviewDTO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Reviews werden für Nutzer und Gäste gesetzt
    @Transactional // Eine Transaktion
    public void addNewReview(Product product, User user, Integer rating, Integer helpfulVotes, String summary, String content) {
        Date reviewDate = new Date();

        if (user == null) {
            // User ist null ====> Gastbewertung
            GuestReview guestReview = new GuestReview();
            guestReview.setProduct(product);
            guestReview.setRating(rating);
            guestReview.setHelpfulVotes(helpfulVotes);
            guestReview.setSummary(summary);
            guestReview.setContent(content);
            guestReview.setReviewDate(reviewDate); // Set review Date auf jetzt
            // Persistieren der Änderungen
            entityManager.persist(guestReview);
        } else {
            // User ist nicht null ====> Nutzerbewertung
            UserReview userReview = new UserReview();
            userReview.setProduct(product);
            userReview.setUser(user);
            userReview.setRating(rating);
            userReview.setHelpfulVotes(helpfulVotes);
            userReview.setSummary(summary);
            userReview.setContent(content);
            userReview.setReviewDate(reviewDate); // Set review Date auf jetzt
            // Persistieren der Änderungen
            entityManager.persist(userReview);
        }
    }

    public List<UserReview> viewUserReviews(String productId) {
        // Retrieve user reviews for the given username from the database
        return entityManager.createQuery("SELECT ur FROM UserReview ur WHERE ur.product.productId = :productId", UserReview.class)
                .setParameter("productId", productId)
                .getResultList();
    }

    public List<GuestReview> viewGuestReviews(String productId) {
        // Retrieve the newest k guest reviews from the database
        return entityManager.createQuery("SELECT gr FROM GuestReview gr WHERE gr.product.productId = :productId ORDER BY gr.reviewDate DESC", GuestReview.class)
                .setParameter("productId", productId)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<User> getTrolls(float threshold, int minReviews) {
        // Hole alle Usernamen von den Nutzern, die mindestens X Reviews gemacht und ein Rating unter Y (AVG) vergeben haben
        String subquery = "SELECT ur.user.id " +
                "FROM UserReview ur " +
                "GROUP BY ur.user.id " +
                "HAVING AVG(ur.rating) <= :threshold " +
                "AND COUNT(ur) >= :minReviews";

        List<Long> trollUserIds = entityManager.createQuery(subquery, Long.class)
                .setParameter("threshold", threshold)
                .setParameter("minReviews", minReviews)
                .getResultList();

        if (trollUserIds.isEmpty()) {
            return Collections.emptyList();
        }

        // Hole alle Informationen der oben gefundenen Trolle
        String userQuery = "SELECT u FROM User u " +
                "WHERE u.id IN :trollUserIds";

        return entityManager.createQuery(userQuery, User.class)
                .setParameter("trollUserIds", trollUserIds)
                .getResultList();
    }

    public List<Product> getTopProducts(int k) {
        // Hole alle Produkte geordnet nach Rating (wenn sie eins haben)
        String HQL = "FROM Product p WHERE p.averageRating IS NOT NULL ORDER BY p.averageRating DESC"; // not-null constraint da manche Produkte keine Bewertung haben
        TypedQuery<Product> query = entityManager.createQuery(HQL, Product.class);
        // Gebe die höchsten k Bewertungen
        query.setMaxResults(k);
        return query.getResultList();
    }
}
