package uni.dbprak21.shopmiddleware.dto;


import jakarta.persistence.JoinColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uni.dbprak21.shopmiddleware.ShopMiddleware;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import uni.dbprak21.shopmiddleware.model.Category;
import uni.dbprak21.shopmiddleware.model.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CategoryDTO {

    private final EntityManager entityManager;

    @Autowired
    public CategoryDTO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Benötigte Relationen im Hibernate-Model:
    //      für den Tree: nur Category mit @OneToMany(mappedBy = "parentCategoryId")
    //                      - - Category bekommt virtuelles Feld "subcategories" als M:1 Beziehung zu parentCategoryId aus Categories
    //      für die Rückgabe von Produkten nach Kategorie-Pfad: Category, Products und junction_category_product mit @ManyToMany-Joins
    //                      - Category ist die "NOT-OWNING-SIDE" in der Hibernate-Definition der N:M Beziehung Produkte-Kategorien
    //                      - Products ist die "OWNING-SIDE" in der Hibernate-Definition der N:M Beziehung Produkte-Kategorien
    //                                      - Hier wird die junction-tabelle definiert: name = "junction_products_categories",
    //                                      - Hier werden durch joinColumns und inverseJoinColumns die Schlüssel der Verbindungstabelle auf beiden Seiten definiert

    // Einstiegspunkt: Tree der Kategorien aufgerufen
    public List<Category> getCategoryTree() {
        // Wähle nur Root-Kategorien (Root = keine parentCategoryId)
        String HQL = "SELECT c FROM Category c WHERE c.parentCategoryId IS NULL";
        TypedQuery<Category> query = entityManager.createQuery(HQL, Category.class);
        List<Category> rootCategories = query.getResultList();

        // Baue für alle Root-Kategorien den Tree rekursiv
        for (Category rootCategory : rootCategories) {
            buildCategoryTree(rootCategory); // Rekursion
        }

        return rootCategories;
    }

    // Rekursion: Tree wird rekursiv ab einer gegebenen Oberkategorie gebaut
    private void buildCategoryTree(Category category) {
        // Suche alle Kategorien mit gegebener Oberkategorie, um Subkategorien der Oberkategorien zu erhalten
        String subcategoryHQL = "SELECT c FROM Category c WHERE c.parentCategoryId = :parentCategoryId";
        TypedQuery<Category> subcategoryQuery = entityManager.createQuery(subcategoryHQL, Category.class);
        subcategoryQuery.setParameter("parentCategoryId", category.getCategoryId());

        // Schreibe Subkategorien in eine Liste
        List<Category> subcategories = subcategoryQuery.getResultList();

        // Jede aktuelle Oberkategorie erhält ihre Subkategorien, in dem das das virtuelle Feld in Hibernate-Objekt Category namens subcategories gesetzt wird
        category.setSubcategories(subcategories);

        // Gehe für jede Subkategorie rekursiv dieselben Schritte durch
        for (Category subcategory : subcategories) {
            buildCategoryTree(subcategory); // Jede Subkategorie wird Oberkategorie der neuen Rekursion
        }
    }


    // Gibt immer alles Darunterliegende aus, Bsp.: {"Die Abenteuer des Huckleberry Finn"} =element Formate/Hörbücher und =element Formate/Hörbücher/Belletristik

    public List<Product> getProductsByCategoryPath(String categoryPath) {
        // Teile den Kategorie-Pfad anhand des Schrägstrichs auf, um die Kategorienamen zu erhalten
        String[] categoryNames = categoryPath.split("/");
        Set<Product> products = new HashSet<>(); // HashSet für Duplikat-Freiheit

        // Baue den initialen Kategoriebaum, beginnend mit den Wurzelkategorien
        // dieser setzt schon alle Produkte und Unterkategorien auf die jeweilige Kategorie
        List<Category> currentCategories = getCategoryTree();

        // Schleife: Gehe durch die Kategorien im Pfad, bis eine Kategorie nicht gefunden wurde
        for (String categoryName : categoryNames) {
            // Finde die Kategorie mit dem aktuellen Namen in den aktuellen Kategorien
            Category foundCategory = findCategoryByName(currentCategories, categoryName);
            if (foundCategory == null) {
                // Kategorie nicht gefunden, daher gibt es keine Produkte
                return new ArrayList<>(products); // HashSet wird in Liste für Rückgabe konvertiert
            }

            // Füge Produkte aus der gefundenen Kategorie zum HashSet hinzu
            products.addAll(foundCategory.getProducts());

            // Aktualisiere die aktuellen Kategorien auf die Subkategorie der gefundenen Kategorie
            currentCategories = foundCategory.getSubcategories();
        }

        // Füge rekursiv Produkte aus allen untergeordneten Subkategorien hinzu
        addProductsFromSubcategories(currentCategories, products);

        return new ArrayList<>(products); // HashSet wird in Liste für Rückgabe konvertiert
    }


    private void addProductsFromSubcategories(List<Category> categories, Set<Product> products) {
        // Rekursiv: Füge Produkte aus allen Kategorien hinzu
        for (Category category : categories) {
            // Füge Produkte aus der aktuellen Kategorie zum HashSet hinzu
            products.addAll(category.getProducts());
            // Hole Subkategorien und wiederhole das Hinzufügen
            addProductsFromSubcategories(category.getSubcategories(), products);
        }
    }


    private Category findCategoryByName(List<Category> categories, String name) {
        // Finde eine Kategorie mit dem angegebenen Namen (case-insensitive)
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(name)) {
                return category;
            }
        }
        return null; // Kategorie wurde nicht gefunden
    }
}