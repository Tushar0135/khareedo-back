package com.caseStudy.Khareedo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class orderhistory {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long OrderId;
    @ManyToOne
    private items item;
    @ManyToOne
    private Users user;
    @Column
    private int quantity;
    @Column
    private double price;
    @Column(nullable = false)
    private LocalDate date;

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }

    public items getItem() {
        return item;
    }

    public void setItem(items item) {
        this.item = item;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        date =LocalDate.now();
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = LocalDate.now();
    }

    public void setDate() {
        this.date=LocalDate.now();
    }
}
