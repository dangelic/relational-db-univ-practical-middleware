package uni.dbprak21.shopmiddleware.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uni.dbprak21.shopmiddleware.ShopMiddlewareInterface;
import jakarta.persistence.EntityManager;
import uni.dbprak21.shopmiddleware.model.GuestReview;
import uni.dbprak21.shopmiddleware.model.Product;
import uni.dbprak21.shopmiddleware.model.User;
import uni.dbprak21.shopmiddleware.model.UserReview;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
public class ReviewDTO implements ShopMiddlewareInterface {

    private final EntityManager entityManager;

    @Autowired
    public ReviewDTO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void addNewReview(Product product, User user, Integer rating, Integer helpfulVotes, String summary, String content) {
        Date reviewDate = new Date(); // Set the review date to the current date and time

        if (user == null || user.getUsername().equals("__GUEST__")) {
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

    public List<UserReview> viewUserReviews(String username) {
        // Retrieve user reviews for the given username from the database
        return entityManager.createQuery("SELECT ur FROM UserReview ur WHERE ur.user.username = :username", UserReview.class)
                .setParameter("username", username)
                .getResultList();
    }

    public List<GuestReview> viewGuestReviews(int k) {
        // Retrieve the newest k guest reviews from the database
        return entityManager.createQuery("SELECT gr FROM GuestReview gr ORDER BY gr.reviewDate DESC", GuestReview.class)
                .setMaxResults(k)
                .getResultList();
    }
}
