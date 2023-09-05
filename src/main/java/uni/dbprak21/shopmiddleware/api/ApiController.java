package uni.dbprak21.shopmiddleware.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uni.dbprak21.shopmiddleware.ShopMiddleware;
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
public class ApiController implements ShopMiddleware {

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

    // ---> Controller ---> ProductInformationDTO
    @GetMapping("/products/get-product-by-id")
    public ResponseEntity<Product> getProduct(@RequestParam("productId") String productId) {
        return productInformationController.getProduct(productId);
    }

    // ---> Controller ---> ProductInformationDTO
    @GetMapping("/products/get-products-by-pattern")
    public ResponseEntity<List<Product>> getProducts(@RequestParam("pattern") String pattern) {
        return productInformationController.getProducts(pattern);
    }

    // ---> Controller ---> CategoryDTO
    @GetMapping("/categories/tree")
    public ResponseEntity<List<Category>> getCategoryTree() {
        return categoryController.getCategoryTree();
    }

    // ---> Controller ---> CategoryDTO
    @GetMapping("/categories/products-by-path")
    public ResponseEntity<List<Product>> getProductsByCategoryPath(@RequestParam("path") String categoryPath) {
        return categoryController.getProductsByCategoryPath(categoryPath);
    }

    // ---> Controller ---> ReviewDTO
    @GetMapping("/reviews/top-products")
    public ResponseEntity<List<Product>> getTopProducts(@RequestParam("k") int k) {
        return reviewController.getTopProducts(k);
    }

    // ---> Controller ---> OfferDTO
    @GetMapping("/offers/get-similar-cheaper-product")
    public ResponseEntity<List<Product>> getSimilarCheaperProduct(@RequestParam("productId") String productId) {
        return offerController.getSimilarCheaperProduct(productId);
    }

    // ---> Controller ---> ReviewDTO
    @PostMapping("/reviews/add-review")
    public ResponseEntity<String> addNewReview(@RequestBody Map<String, Object> reviewData) {
        return reviewController.addNewReview(reviewData);
    }

    // ---> Controller ---> ReviewDTO
    @GetMapping("/reviews/view-user-reviews")
    public ResponseEntity<List<UserReview>> viewUserReviews(@RequestParam String productId) {
        return reviewController.viewUserReviews(productId);
    }

    // ---> Controller ---> ReviewDTO
    @GetMapping("/reviews/view-guest-reviews")
    public ResponseEntity<List<GuestReview>> viewGuestReviews(@RequestParam String productId) {
        return reviewController.viewGuestReviews(productId);
    }

    // ---> Controller ---> ReviewDTO
    @GetMapping("/reviews/get-trolls")
    public ResponseEntity<List<User>> getTrolls() {
        return reviewController.getTrolls();
    }

    // ---> Controller ---> OfferDTP
    @GetMapping("/offers/get-offers")
    public ResponseEntity<List<PriceInfo>> getOffers(@RequestParam("productId") String productId) {
        return offerController.getOffers(productId);
    }
}