package uni.dbprak21.shopmiddleware;

import uni.dbprak21.shopmiddleware.model.PriceInfo;
import uni.dbprak21.shopmiddleware.model.Product;
import uni.dbprak21.shopmiddleware.model.User;

import java.util.Collections;
import java.util.List;

public interface ShopMiddlewareInterface {

    // default void init(Properties properties) {};
    default void finish() {};
    default Product getProduct(String productId) { return null; };
    default List<Product> getProducts(String pattern) { return Collections.emptyList(); };
    default List<Product> getTopProducts(int k) { return Collections.emptyList(); };
    // default Category getCategoryTree() { return null; };
    default List<Product> getProductsByCategoryPath(String categoryPath) { return Collections.emptyList(); };
    default List<Product> getSimilarCheaperProduct(String productId) { return Collections.emptyList(); };
    default void addNewReview(Product product, User user, Integer rating, Integer helpfulVotes, String summary, String content) {};
    // default List<User> getTrolls(float rating) { return Collections.emptyList(); };
    default List<PriceInfo> getOffers(String productId) { return Collections.emptyList(); };
}
