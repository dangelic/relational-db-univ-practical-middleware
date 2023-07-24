package uni.dbprak21.shopmiddleware;

import uni.dbprak21.shopmiddleware.model.Product;

import java.util.List;

public interface ShopMiddlewareInterface {

    // void init(Properties properties);

    // void finish();

    // Product getProduct(String productId);

    List<Product> getProducts(String pattern);

    List<Product> getTopProducts(int k);

    //Category getCategoryTree();

    // List<Product> getProductsByCategoryPath(String categoryPath);

    // List<Product> getSimilarCheaperProduct(String productId);

    // void addNewReview(Review review);
    // we need a look at all reviews here also...

    // List<User> getTrolls(float rating);

    // List<Offer> getOffers(String productId);
}
