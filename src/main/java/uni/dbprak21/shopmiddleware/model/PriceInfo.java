package uni.dbprak21.shopmiddleware.model;

import jakarta.persistence.*;

@Entity
@Table(name = "priceinfos")
public class PriceInfo {

    @Id
    @Column(name = "priceinfo_id", length = 9, nullable = false)
    private String priceInfoId;

    @ManyToOne
    @JoinColumn(name = "products_asin", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "shops_shop_id", nullable = false)
    private Shop shop;

    @Column(name = "price")
    private Float price;

    @Column(name = "multiplier")
    private Float multiplier;

    @Column(name = "currency", length = 9)
    private String currency;

    @Column(name = "state", length = 15)
    private String state;

    // Constructors, getters, and setters

    // Constructors
    public PriceInfo() {
    }

    public PriceInfo(String priceInfoId, Product product, Shop shop, Float price, Float multiplier, String currency, String state) {
        this.priceInfoId = priceInfoId;
        this.product = product;
        this.shop = shop;
        this.price = price;
        this.multiplier = multiplier;
        this.currency = currency;
        this.state = state;
    }

    // Getters and setters
    public String getPriceInfoId() {
        return priceInfoId;
    }

    public void setPriceInfoId(String priceInfoId) {
        this.priceInfoId = priceInfoId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Float multiplier) {
        this.multiplier = multiplier;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
