package uni.dbprak21.shopmiddleware.dto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uni.dbprak21.shopmiddleware.ShopMiddleware;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import uni.dbprak21.shopmiddleware.model.Category;
import uni.dbprak21.shopmiddleware.model.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDTO implements ShopMiddleware {

    private final EntityManager entityManager;

    @Autowired
    public CategoryDTO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Category> getCategoryTree() {
        String HQL = "SELECT c FROM Category c WHERE c.parentCategoryId IS NULL";
        TypedQuery<Category> query = entityManager.createQuery(HQL, Category.class);
        List<Category> rootCategories = query.getResultList();

        for (Category rootCategory : rootCategories) {
            buildCategoryTree(rootCategory);
        }

        return rootCategories;
    }

    private void buildCategoryTree(Category category) {
        String subcategoryHQL = "SELECT c FROM Category c WHERE c.parentCategoryId = :parentCategoryId";
        TypedQuery<Category> subcategoryQuery = entityManager.createQuery(subcategoryHQL, Category.class);
        subcategoryQuery.setParameter("parentCategoryId", category.getCategoryId());
        List<Category> subcategories = subcategoryQuery.getResultList();

        category.setSubcategories(subcategories);

        for (Category subcategory : subcategories) {
            buildCategoryTree(subcategory);
        }
    }

    public List<Product> getProductsByCategoryPath(String categoryPath) {
        String[] categoryNames = categoryPath.split("/");
        List<Product> products = new ArrayList<>();

        List<Category> currentCategories = getCategoryTree();
        for (String categoryName : categoryNames) {
            Category foundCategory = findCategoryByName(currentCategories, categoryName);
            if (foundCategory == null) {
                // Category not found, return empty list
                return products;
            }

            // Add products from the current category
            products.addAll(foundCategory.getProducts());

            // Move to the subcategories for the next iteration
            currentCategories = foundCategory.getSubcategories();
        }

        // Recursively add products from all underlying subcategories
        addProductsFromSubcategories(currentCategories, products);

        return products;
    }

    private void addProductsFromSubcategories(List<Category> categories, List<Product> products) {
        for (Category category : categories) {
            products.addAll(category.getProducts());
            addProductsFromSubcategories(category.getSubcategories(), products);
        }
    }

    private Category findCategoryByName(List<Category> categories, String name) {
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(name)) {
                return category;
            }
        }
        return null;
    }
}