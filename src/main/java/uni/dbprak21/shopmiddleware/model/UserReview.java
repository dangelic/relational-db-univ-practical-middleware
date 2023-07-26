package uni.dbprak21.shopmiddleware.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userreviews")
public class UserReview {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "userreview_id", unique = true, nullable = false)
    private String userReviewId;

    @ManyToOne
    @JoinColumn(name = "products_asin", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "users_username", nullable = false)
    private User user;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "helpful_votes", nullable = false)
    private int helpfulVotes;

    @Column(name = "summary", columnDefinition = "TEXT", nullable = false)
    private String summary;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "review_date", nullable = false)
    private Date reviewDate;

    // Constructors, getters, and setters

    // Constructors
    public UserReview() {
    }

    public UserReview(String userReviewId, Product product, User user, int rating, int helpfulVotes,
                      String summary, String content, Date reviewDate) {
        this.userReviewId = userReviewId;
        this.product = product;
        this.user = user;
        this.rating = rating;
        this.helpfulVotes = helpfulVotes;
        this.summary = summary;
        this.content = content;
        this.reviewDate = reviewDate;
    }

    // Getters and setters
    public String getUserReviewId() {
        return userReviewId;
    }

    public void setUserReviewId(String userReviewId) {
        this.userReviewId = userReviewId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getHelpfulVotes() {
        return helpfulVotes;
    }

    public void setHelpfulVotes(int helpfulVotes) {
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
