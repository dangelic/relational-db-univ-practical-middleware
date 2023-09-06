package uni.dbprak21.shopmiddleware.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "guestreviews")
public class GuestReview {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Generiere eine UUID für die Bewertung
    @Column(name = "guestreview_id", unique = true, nullable = false)
    private String guestReviewId;

    // Joine Produkte
    @ManyToOne
    @JoinColumn(name = "products_asin", nullable = false)
    private Product product;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "helpful_votes", nullable = false)
    private Integer helpfulVotes;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "review_date", nullable = false)
    private Date reviewDate;

    // Constructors, getters, and setters

    // Constructors
    public GuestReview() {
    }

    public GuestReview(String guestReviewId, Product product, Integer rating, Integer helpfulVotes, String summary, String content, Date reviewDate) {
        this.guestReviewId = guestReviewId;
        this.product = product;
        this.rating = rating;
        this.helpfulVotes = helpfulVotes;
        this.summary = summary;
        this.content = content;
        this.reviewDate = reviewDate;
    }

    // Getters and setters
    public String getGuestReviewId() {
        return guestReviewId;
    }

    public void setGuestReviewId(String guestReviewId) {
        this.guestReviewId = guestReviewId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getHelpfulVotes() {
        return helpfulVotes;
    }

    public void setHelpfulVotes(Integer helpfulVotes) {
        this.helpfulVotes = helpfulVotes;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}