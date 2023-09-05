package uni.dbprak21.shopmiddleware.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uni.dbprak21.shopmiddleware.ShopMiddleware;
import uni.dbprak21.shopmiddleware.model.Category;
import uni.dbprak21.shopmiddleware.dto.CategoryDTO;
import uni.dbprak21.shopmiddleware.model.Product;

@RestController
public class CategoryController  {

    private final CategoryDTO categoryDTO; // Autowire ProductDTO

    public CategoryController(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    public ResponseEntity<List<Category>> getCategoryTree() {
        List<Category> categories = categoryDTO.getCategoryTree();
        return ResponseEntity.ok(categories);
    }

    public ResponseEntity<List<Product>> getProductsByCategoryPath(String categoryPath) {
        List<Product> categories = categoryDTO.getProductsByCategoryPath(categoryPath);
        return ResponseEntity.ok(categories);
    }
}