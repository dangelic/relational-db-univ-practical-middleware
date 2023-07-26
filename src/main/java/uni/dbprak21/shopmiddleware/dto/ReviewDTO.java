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

        if (user == null) {
            // User is null, it's a guest review
            GuestReview guestReview = new GuestReview();
            guestReview.setProduct(product);
            guestReview.setRating(rating);
            guestReview.setHelpfulVotes(helpfulVotes);
            guestReview.setSummary(summary);
            guestReview.setContent(content);
            guestReview.setReviewDate(reviewDate); // Set the review date
            // Set other guest review properties as needed
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
            // Set other user review properties as needed
            entityManager.persist(userReview);
        }
    }
}
