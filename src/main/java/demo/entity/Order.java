package demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")   // ✅ Correct
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String product;
    private double price;

    @JsonBackReference   // ✅ Child side
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }   

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }   

    public User getUser() {
        return user;
    }

    public void setUser(User user) {   // ✅ FIX (not "users")
        this.user = user;
    }
}