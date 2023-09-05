package uni.dbprak21.shopmiddleware.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uni.dbprak21.shopmiddleware.ShopMiddleware;
import uni.dbprak21.shopmiddleware.model.Product;
import uni.dbprak21.shopmiddleware.dto.ProductInformationDTO;
import uni.dbprak21.shopmiddleware.repositoryjpa.ProductRepository;

@RestController
public class ProductInformationController {

    private final ProductInformationDTO productInformationDTO;

    private final ProductRepository productRepository;


    @Autowired
    public ProductInformationController(ProductInformationDTO productInformationDTO, ProductRepository productRepository) {
        this.productInformationDTO = productInformationDTO;
        this.productRepository = productRepository;
    }

    public ResponseEntity<Product> getProduct(String productId) {
        // Hier wird einfach die Funktion findById des productRepository (als JPA-Repository-Erweiterung) genommen
        // Dies erspart die manuelle Programmierung einer eigenen Query, die sonst in DTO aufgerufen werden würden
        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    public ResponseEntity<List<Product>> getProducts(String pattern) {
        List<Product> products = productInformationDTO.getProducts(pattern);
        return ResponseEntity.ok(products);
    }
}