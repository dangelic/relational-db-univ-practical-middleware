package uni.dbprak21.shopmiddleware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uni.dbprak21.shopmiddleware.exception.ResourceNotFoundException;
import uni.dbprak21.shopmiddleware.model.Product;
import uni.dbprak21.shopmiddleware.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:4200") // ng
@RestController
@RequestMapping("/api/v1/")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // Get all products
    @GetMapping("/employees")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    // Get products via ASIN
    @GetMapping("/products/{asin}")
    public ResponseEntity<Product> getProductById(@PathVariable String asin) {
        Product product = productRepository.findById(asin)
                .orElseThrow(() -> new ResourceNotFoundException("product not exist with ASIN :" + asin));
        return ResponseEntity.ok(product);
    }
}