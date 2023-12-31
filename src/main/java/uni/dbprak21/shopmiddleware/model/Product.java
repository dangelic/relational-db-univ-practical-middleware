package uni.dbprak21.shopmiddleware.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    // Produkt ist die "OWNING-SIDE" in der Beziehung Produkte-Kategorien
    @ManyToMany // Mehrere Produkte können mehrere Kategorien haben
    @JoinTable(
            name = "junction_products_categories", // Die Verbindungstabelle
            joinColumns = @JoinColumn(name = "products_asin"), // Der Schlüssel in der Verbindungstabelle ==> Schlüssel in der Produkt-Tabelle
            inverseJoinColumns = @JoinColumn(name = "categories_category_id") // Der Schlüssel in der Verbindungstabelle ==> Schlüssel in der Kategorien-Tabelle (inverse)
    )
    private List<Category> categories = new ArrayList<>();

    @Id
    @Column(name = "asin", length = 10, nullable = false)
    private String productId;

    @Column(name = "ptitle", length = 255, nullable = false)
    private String productTitle;

    @Column(name = "pgroup", length = 5, nullable = false)
    private String productGroup;

    @Column(name = "ean", length = 13)
    private String ean;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name = "detailpage_url", columnDefinition = "TEXT")
    private String detailpageUrl;

    @Column(name = "salesrank")
    private Integer salesrank;

    @Column(name = "upc", length = 12, unique = true)
    private String upc;

    @Column(name = "average_rating")
    private Float averageRating;

    // Default constructor (required by Hibernate)
    public Product() {

    }

    // Constructor with all fields
    public Product(String productId, String productTitle, String productGroup, String ean, String imageUrl, String detailpageUrl, Integer salesrank, String upc, Float averageRating) {
        super();
        this.productId = productId;
        this.productTitle = productTitle;
        this.productGroup = productGroup;
        this.ean = ean;
        this.imageUrl = imageUrl;
        this.detailpageUrl = detailpageUrl;
        this.salesrank = salesrank;
        this.upc = upc;
        this.averageRating = averageRating;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setPgroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDetailpageUrl() {
        return detailpageUrl;
    }

    public void setDetailpageUrl(String detailpageUrl) {
        this.detailpageUrl = detailpageUrl;
    }

    public Integer getSalesrank() {
        return salesrank;
    }

    public void setSalesrank(Integer salesrank) {
        this.salesrank = salesrank;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public Float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Float averageRating) {
        this.averageRating = averageRating;
    }
}