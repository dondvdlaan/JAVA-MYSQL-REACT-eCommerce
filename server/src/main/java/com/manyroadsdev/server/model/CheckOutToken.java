package com.manyroadsdev.server.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CheckOutToken is a collection of data during the checkout phase, derived from
 * the cart and to be converted in to a new order.
 */
@Entity
public class CheckOutToken {

    // *** Constants ***
    private static final int DEF_VALUE_INT = 0;
    private static final String DEF_VALUE_STR = ">nothingToSeeHere<";

    // *** Declaration and initialisation attributes ***
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // Unidirectional 1-to-many Items relationship. tokenID is FK at Item table.
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "tokenID")
    private List<Item> lineItems;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "liveID")
    private Live live;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "merchantID")
    private Merchant merchant;

    // Unidirectional 1-to-many Items relationship, tokenID is FK at Product table.
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "tokenID")
    private List<Product> products = new ArrayList<>();
    private int cartID;

    // *** Constructors ***


    public CheckOutToken() {
        this.lineItems = new ArrayList<>();
        this.live = new Live();
        this.merchant = new Merchant();
        this.products = new ArrayList<>();
        this.cartID = DEF_VALUE_INT;
    }

    public CheckOutToken(int tokenID, List<Item> lineItems, Live live, Merchant merchant, List<Product> products, int cartID) {
        this.id = tokenID;
        this.lineItems = lineItems;
        this.live = live;
        this.merchant = merchant;
        this.products = products;
        this.cartID = cartID;
    }
    // *** Getter und Setter ***

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Item> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<Item> lineItems) {
        this.lineItems = lineItems;
    }

    public Live getLive() {
        return live;
    }

    public void setLive(Live live) {
        this.live = live;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    @Override
    public String toString() {
        return "CheckOutToken{" +
                "tokenID=" + id +
                ", lineItems=" + lineItems +
                ", live=" + live +
                ", merchant=" + merchant +
                ", products=" + products +
                ", cartID=" + cartID +
                '}';
    }
}
