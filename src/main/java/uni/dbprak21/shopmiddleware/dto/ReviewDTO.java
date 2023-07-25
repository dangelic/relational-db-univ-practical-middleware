package uni.dbprak21.shopmiddleware.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uni.dbprak21.shopmiddleware.ShopMiddlewareInterface;
import jakarta.persistence.EntityManager;
import uni.dbprak21.shopmiddleware.model.GuestReview;
import uni.dbprak21.shopmiddleware.model.Product;
import uni.dbprak21.shopmiddleware.model.User;
import uni.dbprak21.shopmiddleware.model.UserReview;

@Component
public class ReviewDTO implements ShopMiddlewareInterface {


    private final EntityManager entityManager;

    @Autowired
    public ReviewDTO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addNewReview(Product product, User user, Integer rating, Integer helpfulVotes, String summary, String content) {
        if (user == null || user.isEmpty()) {
            GuestReview guestReview = new GuestReview();
            guestReview.setProduct(product);
            guestReview.setRating(rating);
            guestReview.setHelpfulVotes(helpfulVotes);
            guestReview.setSummary(summary);
            guestReview.setContent(content);
            // Set other guest review properties as needed
            entityManager.persist(guestReview);
        } else {
            UserReview userReview = new UserReview();
            userReview.setProduct(product);
            userReview.setUser(user);
            userReview.setRating(rating);
            userReview.setHelpfulVotes(helpfulVotes);
            userReview.setSummary(summary);
            userReview.setContent(content);
            // Set other user review properties as needed
            entityManager.persist(userReview);
        }
    }
}