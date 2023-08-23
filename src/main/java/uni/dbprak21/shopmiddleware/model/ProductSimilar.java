package uni.dbprak21.shopmiddleware.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products_similars")
public class ProductSimilar {

    @Id
    @ManyToOne
    @JoinColumn(name = "products_asin", nullable = false)
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "similar_product_asin", nullable = false)
    private Product similarProduct;

    // Constructors, getters, and setters

    // Constructors
    public ProductSimilar() {
    }

    public ProductSimilar(Product product, Product similarProduct) {
        this.product = product;
        this.similarProduct = similarProduct;
    }

    // Getters and setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getSimilarProduct() {
        return similarProduct;
    }

    public void setSimilarProduct(Product similarProduct) {
        this.similarProduct = similarProduct;
    }
}