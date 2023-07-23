package uni.dbprak21.shopmiddleware.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "asin", length = 10, nullable = false)
    private String asin;

    @Column(name = "ptitle", length = 255, nullable = false)
    private String ptitle;

    @Column(name = "pgroup", length = 5, nullable = false)
    private String pgroup;

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
    public Product(String asin, String ptitle, String pgroup, String ean, String imageUrl, String detailpageUrl, Integer salesrank, String upc, Float averageRating) {
        super();
        this.asin = asin;
        this.ptitle = ptitle;
        this.pgroup = pgroup;
        this.ean = ean;
        this.imageUrl = imageUrl;
        this.detailpageUrl = detailpageUrl;
        this.salesrank = salesrank;
        this.upc = upc;
        this.averageRating = averageRating;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public String getPgroup() {
        return pgroup;
    }

    public void setPgroup(String pgroup) {
        this.pgroup = pgroup;
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