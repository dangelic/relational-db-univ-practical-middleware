package uni.dbprak21.shopmiddleware.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    // Für getCategoryTree(...) ->
        // subcategories als virtuelles Feld in M:1 Beziehung
        // subcategories werden über Setter vom DTO gesetzt
    @OneToMany(mappedBy = "parentCategoryId") // Mehrere Unterkategorien gehören zu einer Oberkategorie
    private List<Category> subcategories;

    // Für getProductsByCategoryPath(...) ->
        // Kategorie ist die "NOT-OWNING-SIDE" in der Beziehung Produkte-Kategorien, in Produkte-Tabelle ist die n:m Verbindung schon definiert.
    @ManyToMany(mappedBy = "categories")
    private List<Product> products = new ArrayList<>();
    @Id
    @Column(name = "category_id", length = 9, nullable = false)
    private String categoryId;

    @Column(name = "parent_category_id", length = 9)
    private String parentCategoryId;

    @Column(name = "name", length = 255, nullable = false)
    private String name;



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

