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

    private final EntityManager entityManager;

    @Autowired
    public ReviewDTO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void addNewReview(Product product, User user, Integer rating, Integer helpfulVotes, String summary, String content) {
        Date reviewDate = new Date(); // Set the review date to the current date and time

        if (user == null) {
            // User is null, it's a guest review
            GuestReview guestReview = new GuestReview();
            guestReview.setProduct(product);
            guestReview.setRating(rating);
            guestReview.setHelpfulVotes(helpfulVotes);
            guestReview.setSummary(summary);
            guestReview.setContent(content);
            guestReview.setReviewDate(reviewDate); // Set the review date
            // Persist the changes
            entityManager.persist(guestReview);
        } else {
            // User is not null, it's a user review
            UserReview userReview = new UserReview();
            userReview.setProduct(product);
            userReview.setUser(user);
            userReview.setRating(rating);
            userReview.setHelpfulVotes(helpfulVotes);
            userReview.setSummary(summary);
            userReview.setContent(content);
            userReview.setReviewDate(reviewDate); // Set the review date
            // Persist the changes
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

        String userQuery = "SELECT u FROM User u " +
                "WHERE u.id IN :trollUserIds";

        return entityManager.createQuery(userQuery, User.class)
                .setParameter("trollUserIds", trollUserIds)
                .getResultList();
    }

    public List<Product> getTopProducts(int k) {
        String HQL = "FROM Product p WHERE p.averageRating IS NOT NULL ORDER BY p.averageRating DESC"; // not-null constraint as some products are not rated at all.
        TypedQuery<Product> query = entityManager.createQuery(HQL, Product.class);
        query.setMaxResults(k);
        return query.getResultList();
    }
}
