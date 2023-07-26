package uni.dbprak21.shopmiddleware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.dbprak21.shopmiddleware.dto.ReviewDTO;
import uni.dbprak21.shopmiddleware.model.Product;
import uni.dbprak21.shopmiddleware.model.User;
import jakarta.persistence.EntityNotFoundException;
import uni.dbprak21.shopmiddleware.repository.ProductRepository;
import uni.dbprak21.shopmiddleware.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200") // ng
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    private final ReviewDTO reviewDTO;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewController(ReviewDTO reviewDTO, ProductRepository productRepository, UserRepository userRepository) {
        this.reviewDTO = reviewDTO;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addReview(@RequestBody Map<String, Object> reviewData) {
        // Extract reviewData from API calls' body
        String asin = (String) reviewData.get("asin");
        String username = (String) reviewData.get("username");
        Integer rating = (Integer) reviewData.get("rating");
        Integer helpfulVotes = (Integer) reviewData.get("helpfulVotes");
        String summary = (String) reviewData.get("summary");
        String content = (String) reviewData.get("content");

        try {
            // Fetch the Product and User objects using the appropriate repositories
            Product product = productRepository.findById(asin)
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + asin));

            User user = null;
            if (username != null && !username.isEmpty()) {
                user = userRepository.findById(username)
                        .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + username));
            }

            // Call the addNewReview method in ReviewDTO
            reviewDTO.addNewReview(product, user, rating, helpfulVotes, summary, content);
            return ResponseEntity.ok("Review added successfully.");
        } catch (EntityNotFoundException ex) {
            // Log the exception and return 404 Not Found response
            logger.error("Entity not found: " + ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            // Log the unexpected exception and return 500 Internal Server Error response
            logger.error("Error adding review: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding review.");
        }
    }
}