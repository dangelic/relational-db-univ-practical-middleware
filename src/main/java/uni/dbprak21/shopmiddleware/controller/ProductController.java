package uni.dbprak21.shopmiddleware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uni.dbprak21.shopmiddleware.model.Product;
import uni.dbprak21.shopmiddleware.dto.ProductDTO;

@CrossOrigin(origins = "http://localhost:4200") // ng
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductDTO productDTO; // Autowire ProductDTO

    @Autowired
    public ProductController(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    // Get products via title pattern using query parameter
    @GetMapping("/title")
    public ResponseEntity<List<Product>> getProducts(@RequestParam("pattern") String pattern) {
        List<Product> products = productDTO.getProducts(pattern);
        return ResponseEntity.ok(products);
    }

    // Get top k products using query parameter
    @GetMapping("/top")
    public ResponseEntity<List<Product>> getTopProducts(@RequestParam("k") int k) {
        List<Product> products = productDTO.getTopProducts(k);
        return ResponseEntity.ok(products);
    }
}