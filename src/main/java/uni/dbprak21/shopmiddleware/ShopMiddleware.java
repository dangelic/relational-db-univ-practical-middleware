package uni.dbprak21.shopmiddleware;

import uni.dbprak21.shopmiddleware.model.PriceInfo;
import uni.dbprak21.shopmiddleware.model.Product;
import uni.dbprak21.shopmiddleware.model.User;
import uni.dbprak21.shopmiddleware.model.GuestReview;
import uni.dbprak21.shopmiddleware.model.UserReview;
import uni.dbprak21.shopmiddleware.model.Category;

import java.util.Collections;
import java.util.List;

public interface ShopMiddleware {

    // Initialisiert die Middleware, erstellt die Datenbankverbindung und führt notwendige Initialisierungsschritte durch.
    default void init() {};

    // Wird bei Beendigung der Anwendung aufgerufen, um Ressourcen freizugeben, insbesondere Datenbankobjekte.
    default void finish() {}

    // Gibt Detailinformationen über ein Produkt anhand seiner Produkt-Id (ASIN) zurück.
    default Product getProduct(String productId) { return null; }

    // Gibt eine Liste von Produkten zurück, deren Titel mit dem angegebenen Pattern übereinstimmen.
    default List<Product> getProducts(String pattern) { return Collections.emptyList(); }

    // Gibt eine Liste der besten k Produkte basierend auf dem Rating zurück.
    default List<Product> getTopProducts(int k) { return Collections.emptyList(); }

    // Ermittelt den Kategorienbaum und gibt den Wurzelknoten zurück.
    default List<Category> getCategoryTree() { return null; }

    // Gibt eine Liste von Produkten zurück, die zur angegebenen Kategorie (als Pfad übergeben) gehören.
    default List<Product> getProductsByCategoryPath(String categoryPath) { return Collections.emptyList(); }

    // Gibt eine Liste ähnlicher und preiswerter Produkte im Vergleich zu einem angegebenen Produkt zurück.
    default List<Product> getSimilarCheaperProduct(String productId) { return Collections.emptyList(); }

    // Fügt eine neue Produktbewertung durch einen Benutzer oder einen Gast hinzu.
    default void addNewReview(Product product, User user, Integer rating, Integer helpfulVotes, String summary, String content) {}

    // Gibt eine Liste von Benutzern zurück, deren Durchschnittsbewertung unterhalb eines Schwellenwerts liegt und die eine Mindestanzahl von Bewertungen haben.
    default List<User> getTrolls(float threshold, int minReviews) { return Collections.emptyList(); }

    // Gibt eine Liste von allen Angeboten für ein bestimmtes Produkt zurück.
    default List<PriceInfo> getOffers(String productId) { return Collections.emptyList(); }

    // Gibt eine Liste der Bewertungen eines Benutzers zurück.
    default List<UserReview> viewUserReviews(String username) { return Collections.emptyList(); }

    // Gibt eine Liste von Bewertungen durch k Gäste(n) zurück.
    default List<GuestReview> viewGuestReviews(int k) { return Collections.emptyList(); }
}