package uni.dbprak21.shopmiddleware.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @Column(name = "shop_id", length = 9, nullable = false)
    private String shopId;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    // Constructors, getters, and setters

    // Constructors
    public Shop() {
    }

    public Shop(String shopId, String name) {
        this.shopId = shopId;
        this.name = name;
    }

    // Getters and setters
    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

