package uni.dbprak21.shopmiddleware.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username", length = 30, nullable = false)
    private String username;

    // Constructors, getters, and setters

    // Constructors
    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}