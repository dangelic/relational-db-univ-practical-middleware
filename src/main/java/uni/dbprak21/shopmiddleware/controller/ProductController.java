package uni.dbprak21.shopmiddleware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uni.dbprak21.shopmiddleware.exception.ResourceNotFoundException;
import uni.dbprak21.shopmiddleware.model.Product;
import uni.dbprak21.shopmiddleware.repository.ProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@CrossOrigin(origins = "http://localhost:4200") // ng
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    // Get products via title pattern using query parameter
    @GetMapping("/title")
    public List<Product> getProductsByTitlePattern(@RequestParam("pattern") String pattern) {
        System.out.println(pattern);
        String hql = "FROM Product p WHERE p.productTitle LIKE :pattern";
        TypedQuery<Product> query = entityManager.createQuery(hql, Product.class);
        query.setParameter("pattern", "%" + pattern + "%");
        return query.getResultList();
    }
}