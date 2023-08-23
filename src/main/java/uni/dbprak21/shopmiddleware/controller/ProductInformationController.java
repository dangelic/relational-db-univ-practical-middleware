package uni.dbprak21.shopmiddleware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uni.dbprak21.shopmiddleware.model.Product;
import uni.dbprak21.shopmiddleware.dto.ProductInformationDTO;

@RestController
@RequestMapping("/api/v1/products")
public class ProductInformationController {

    private final ProductInformationDTO productInformationDTO; // Autowire ProductDTO

    @Autowired
    public ProductInformationController(ProductInformationDTO productInformationDTO) {
        this.productInformationDTO = productInformationDTO;
    }

    // Get products via title pattern using query parameter
    @GetMapping("/title")
    public ResponseEntity<List<Product>> getProducts(@RequestParam("pattern") String pattern) {
        List<Product> products = productInformationDTO.getProducts(pattern);
        return ResponseEntity.ok(products);
    }
}