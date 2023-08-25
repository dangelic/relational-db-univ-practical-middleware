package uni.dbprak21.shopmiddleware.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    public ResponseEntity<Optional<Product>> getProduct(String asin) {
        Optional<Product> products = productRepository.findById(asin);
        return ResponseEntity.ok(products);
    }

    public ResponseEntity<List<Product>> getProducts(String pattern) {
        List<Product> products = productInformationDTO.getProducts(pattern);
        return ResponseEntity.ok(products);
    }
}