package com.manyroadsdev.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Product {

    // *** Constants ***
    private static final int DEF_VALUE_INT = -1;
    private static final String DEF_VALUE_STR = ">nothingToSeeHere<";

    // *** Declaration and initialisation attributes ***
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int prodID;
    private String prodName;
    private String prodDescription;
    // Product is parent in relationship, 1-to-1 unidirectional
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "priceID")
    private Price price;
    @Column(length = 3000)
    private String prodImage;
    private int active;

    // *** Constructors ***
    public Product() {
        this.prodName = DEF_VALUE_STR;
        this.prodDescription = DEF_VALUE_STR;
        this.price = new Price();
        this.prodImage = DEF_VALUE_STR;
        this.active = DEF_VALUE_INT;
    }

    public Product(String prodName, String prodDescription, Price price, String prodImage, int active) {
        this.prodName = prodName;
        this.prodDescription = prodDescription;
        this.price = price;
        this.prodImage = prodImage;
        this.active = active;
    }

    public Product(Integer prodID, String prodName, String prodDescription, Price price, String prodImage, Integer active) {
        this.prodID = prodID;
        this.prodName = prodName;
        this.prodDescription = prodDescription;
        this.price = price;
        this.prodImage = prodImage;
        this.active = active;
    }

    public Integer getProdID() {
        return prodID;
    }

    public void setProdID(Integer prodID) {
        this.prodID = prodID;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDescription() {
        return prodDescription;
    }

    public void setProdDescription(String prodDescription) {
        this.prodDescription = prodDescription;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getProdImage() {
        return prodImage;
    }

    public void setProdImage(String prodImage) {
        this.prodImage = prodImage;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Product{" +
                "prodID=" + prodID +
                ", prodName='" + prodName + '\'' +
                ", prodDescription='" + prodDescription + '\'' +
                ", price=" + price +
                ", prodImage='" + prodImage + '\'' +
                ", active=" + active +
                '}';
    }
}
