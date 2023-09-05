package uni.dbprak21.shopmiddleware;

import uni.dbprak21.shopmiddleware.model.PriceInfo;
import uni.dbprak21.shopmiddleware.model.Product;
import uni.dbprak21.shopmiddleware.model.User;
import uni.dbprak21.shopmiddleware.model.GuestReview;
import uni.dbprak21.shopmiddleware.model.UserReview;
import uni.dbprak21.shopmiddleware.model.Category;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ShopMiddleware {

    // Gibt Detailinformationen über ein Produkt anhand seiner Produkt-Id (ASIN) zurück.
    ResponseEntity<Product> getProduct(String productId);

    // Gibt eine Liste von Produkten zurück, deren Titel mit dem angegebenen Pattern übereinstimmen.
    ResponseEntity<List<Product>> getProducts(String pattern);

    // Gibt eine Liste der besten k Produkte basierend auf dem Rating zurück.
    ResponseEntity<List<Product>> getTopProducts(int k);

    // Ermittelt den Kategorienbaum und gibt den Wurzelknoten zurück.
    ResponseEntity<List<Category>> getCategoryTree();

    // Gibt eine Liste von Produkten zurück, die zur angegebenen Kategorie (als Pfad übergeben) gehören.
    ResponseEntity<List<Product>> getProductsByCategoryPath(String categoryPath);

    // Gibt eine Liste ähnlicher und preiswerter Produkte im Vergleich zu einem angegebenen Produkt zurück.
    ResponseEntity<List<Product>> getSimilarCheaperProduct(String productId);

    // Fügt eine neue Produktbewertung durch einen Benutzer oder einen Gast hinzu.
    ResponseEntity<String> addNewReview(Map<String, Object> reviewData);

    // Gibt eine Liste von Benutzern zurück, deren Durchschnittsbewertung unterhalb eines Schwellenwerts liegt und die eine Mindestanzahl von Bewertungen haben.
    ResponseEntity<List<User>> getTrolls();

    // Gibt eine Liste von allen Angeboten für ein bestimmtes Produkt zurück.
    ResponseEntity<List<PriceInfo>> getOffers(String productId);

    // Gibt eine Liste der Bewertungen eines Benutzers für ein Produkt zurück.
    ResponseEntity<List<UserReview>> viewUserReviews(String productId);

    // Gibt eine Liste von Bewertungen durch Gäste(n) für ein Produkt zurück.
    ResponseEntity<List<GuestReview>> viewGuestReviews(String productId);
}