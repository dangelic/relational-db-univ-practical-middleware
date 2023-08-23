package uni.dbprak21.shopmiddleware;

import uni.dbprak21.shopmiddleware.model.PriceInfo;
import uni.dbprak21.shopmiddleware.model.Product;
import uni.dbprak21.shopmiddleware.model.User;
import uni.dbprak21.shopmiddleware.model.GuestReview;
import uni.dbprak21.shopmiddleware.model.UserReview;
import uni.dbprak21.shopmiddleware.model.Category;

import java.util.Collections;
import java.util.List;

public interface ShopMiddleware {

    // default void init(Properties properties) {};
    default void finish() {}
    default Product getProduct(String productId) { return null; }
    default List<Product> getProducts(String pattern) { return Collections.emptyList(); }
    default List<Product> getTopProducts(int k) { return Collections.emptyList(); }
    default List<Category> getCategoryTree() { return null; }
    default List<Product> getProductsByCategoryPath(String categoryPath) { return Collections.emptyList(); }
    default List<Product> getSimilarCheaperProduct(String productId) { return Collections.emptyList(); }
    default void addNewReview(Product product, User user, Integer rating, Integer helpfulVotes, String summary, String content) {}
    default List<User> getTrolls(float threshold, int minReviews) { return Collections.emptyList(); }
    default List<PriceInfo> getOffers(String productId) { return Collections.emptyList(); }

    // New methods to view user and guest reviews
    default List<UserReview> viewUserReviews(String username) { return Collections.emptyList(); }
    default List<GuestReview> viewGuestReviews(int k) { return Collections.emptyList(); }
}