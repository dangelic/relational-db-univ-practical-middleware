package uni.dbprak21.shopmiddleware.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.dbprak21.shopmiddleware.controller.CategoryController;
import uni.dbprak21.shopmiddleware.controller.OfferController;
import uni.dbprak21.shopmiddleware.controller.ProductInformationController;
import uni.dbprak21.shopmiddleware.controller.ReviewController;
import uni.dbprak21.shopmiddleware.model.Category;
import uni.dbprak21.shopmiddleware.model.PriceInfo;
import uni.dbprak21.shopmiddleware.model.Product;
import uni.dbprak21.shopmiddleware.model.GuestReview;
import uni.dbprak21.shopmiddleware.model.UserReview;
import uni.dbprak21.shopmiddleware.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

    private final CategoryController categoryController;
    private final OfferController offerController;
    private final ProductInformationController productInformationController;
    private final ReviewController reviewController;

    @Autowired
    public ApiController(CategoryController categoryController,
                         OfferController offerController,
                         ProductInformationController productInformationController,
                         ReviewController reviewController)
    {
        this.categoryController = categoryController;
        this.offerController = offerController;
        this.productInformationController = productInformationController;
        this.reviewController = reviewController;
    }

    @GetMapping("/products/get-product-by-id")
    public ResponseEntity<List<Product>> getProduct(@RequestParam("asin") String asin) {
        return productInformationController.getProducts(asin);
    }
    @GetMapping("/products/get-products-by-pattern")
    public ResponseEntity<List<Product>> getProducts(@RequestParam("pattern") String pattern) {
        return productInformationController.getProducts(pattern);
    }

    @GetMapping("/categories/tree")
    public ResponseEntity<List<Category>> getCategoryTree() {
        return categoryController.getCategoryTree();
    }

    @GetMapping("/categories/products-by-path")
    public ResponseEntity<List<Product>> getProductsByCategoryPath(@RequestParam("path") String categoryPath) {
        return categoryController.getProductsByCategoryPath(categoryPath);
    }

    @GetMapping("/reviews/top-products")
    public ResponseEntity<List<Product>> getTopProducts(@RequestParam("k") int k) {
        return reviewController.getTopProducts(k);
    }

    @GetMapping("/offers/get-similar-cheaper-product")
    public ResponseEntity<List<Product>> getSimilarCheaperProduct(@RequestParam("asin") String asin) {
        return offerController.getSimilarCheaperProduct(asin);
    }

    @PostMapping("/reviews/add-review")
    public ResponseEntity<String> addNewReview(@RequestBody Map<String, Object> reviewData) {
        return reviewController.addNewReview(reviewData);
    }

    @GetMapping("/reviews/view-user-reviews")
    public ResponseEntity<List<UserReview>> viewUserReviews(@RequestParam String username) {
        return reviewController.viewUserReviews(username);
    }

    @GetMapping("/reviews/view-guest-reviews")
    public ResponseEntity<List<GuestReview>> viewGuestReviews(@RequestParam int k) {
        return reviewController.viewGuestReviews(k);
    }

    @GetMapping("/reviews/get-trolls")
    public ResponseEntity<List<User>> getTrolls(@RequestParam float threshold, @RequestParam int minReviews) {
        return reviewController.getTrolls(threshold, minReviews);
    }

    @GetMapping("/offers/get-offers")
    public ResponseEntity<List<PriceInfo>> getOffers(@RequestParam("asin") String asin) {
        return offerController.getOffers(asin);
    }
}