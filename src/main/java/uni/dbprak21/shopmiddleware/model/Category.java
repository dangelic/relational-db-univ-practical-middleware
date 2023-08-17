package uni.dbprak21.shopmiddleware.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @OneToMany(mappedBy = "parentCategoryId")
    private List<Category> subcategories;
    @Id
    @Column(name = "category_id", length = 9, nullable = false)
    private String categoryId;

    @Column(name = "parent_category_id", length = 9)
    private String parentCategoryId;

    @Column(name = "name", length = 255, nullable = false)
    private String name;


    @ManyToMany(mappedBy = "categories")
    private List<Product> products = new ArrayList<>();

    public Category() {
    }

    public Category(String categoryId, String parentCategoryId, String name) {
        this.categoryId = categoryId;
        this.parentCategoryId = parentCategoryId;
        this.name = name;
    }

    // Getters and Setters


    public List<Category> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Category> subcategories) {
        this.subcategories = subcategories;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

