package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="cart_detail")
public class CartDetail {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private long quantity;
    private double price;

    //cart_id
    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    //product_id
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CartDetail{");
        sb.append("id=").append(id);
        sb.append(", quantity=").append(quantity);
        sb.append(", price=").append(price);
        sb.append(", cart=").append(cart);
        sb.append(", product=").append(product);
        sb.append('}');
        return sb.toString();
    }
}
