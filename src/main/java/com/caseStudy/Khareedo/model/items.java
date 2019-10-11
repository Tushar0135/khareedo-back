package com.caseStudy.Khareedo.model;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_details")
@EntityListeners(AuditingEntityListener.class)
public class items implements Serializable
{
    public items() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String details;
    private String image;
    private String category;
    private String subcategory;

    public items(Long id,String name, double price, String details, String image, String category, String subcategory, int active) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.details = details;
        this.image = image;
        this.category = category;
        this.subcategory = subcategory;
        this.active = active;
    }
    @Column(nullable = false, columnDefinition ="int default '1'")
    private int active;
     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
