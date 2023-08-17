package uni.dbprak21.shopmiddleware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uni.dbprak21.shopmiddleware.model.Category;
import uni.dbprak21.shopmiddleware.dto.CategoryDTO;
import uni.dbprak21.shopmiddleware.model.Product;

@CrossOrigin(origins = "http://localhost:4200") // ng
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryDTO categoryDTO; // Autowire ProductDTO

    @Autowired
    public CategoryController(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    // Get products via title pattern using query parameter
    @GetMapping("/tree")
    public ResponseEntity<List<Category>> getCategoryTree() {
        List<Category> categories = categoryDTO.getCategoryTree();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProductsByCategoryPath(@RequestParam("categoryPath") String categoryPath) {
        List<Product> categories = categoryDTO.getProductsByCategoryPath(categoryPath);
        return ResponseEntity.ok(categories);
    }
}