package com.manyroadsdev.server.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    // *** Constants ***
    private static final int DEF_VALUE_INT = 0;
    private static final String DEF_VALUE_STR = ">nothingToSeeHere<";

    // *** Declaration and initialisation attributes ***
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartID;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "totalID")
    private Total cartSubTotal;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "currencyID")
    private Currency currency;

    // Unidirectional 1-to-many Items relationship. @JoinColumn cartID is FK at Item
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "cartID")
    private List<Item> cartLineItems = new ArrayList<>();

    private int totalItems;
    private int totalUniqueItems;

    // *** Constructors ***
    public Cart() {
        this.cartSubTotal = new Total();
        this.currency = new Currency();
        this.totalItems = DEF_VALUE_INT;
        this.totalUniqueItems = DEF_VALUE_INT;
    }

    public Cart(Total cartSubTotal, Currency currency, List<Item> cartLineItems, Integer totalItems, Integer totalUniqueItems) {
        this.cartSubTotal = cartSubTotal;
        this.currency = currency;
        this.cartLineItems = cartLineItems;
        this.totalItems = totalItems;
        this.totalUniqueItems = totalUniqueItems;
    }

    public Cart(Integer cartID, Total cartSubTotal, Currency currency, List<Item> cartLineItems, Integer totalItems, Integer totalUniqueItems) {
        this.cartID = cartID;
        this.cartSubTotal = cartSubTotal;
        this.currency = currency;
        this.cartLineItems = cartLineItems;
        this.totalItems = totalItems;
        this.totalUniqueItems = totalUniqueItems;
    }

    // *** Getter und Setter ***
    public Integer getCartID() {
        return cartID;
    }

    public void setCartID(Integer cartID) {
        this.cartID = cartID;
    }

    public Total getCartSubTotal() {
        return cartSubTotal;
    }

    public void setCartSubTotal(Total cartSubTotal) {
        this.cartSubTotal = cartSubTotal;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public List<Item> getCartLineItems() {
        return cartLineItems;
    }

    public void setCartLineItems(List<Item> cartLineItems) {
        this.cartLineItems = cartLineItems;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getTotalUniqueItems() {
        return totalUniqueItems;
    }

    public void setTotalUniqueItems(Integer totalUniqueItems) {
        this.totalUniqueItems = totalUniqueItems;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartID=" + cartID +
                ", cartSubTotal=" + cartSubTotal +
                ", currency=" + currency +
                ", cartLineItems=" + cartLineItems +
                ", totalItems=" + totalItems +
                ", totalUniqueItems=" + totalUniqueItems +
                '}';
    }
}
