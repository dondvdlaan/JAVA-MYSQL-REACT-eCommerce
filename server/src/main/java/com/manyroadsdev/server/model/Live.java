package com.manyroadsdev.server.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Live forms part of class checkOutToken and collects the readily available data
 * for front end user interface
 */
@Entity
public class Live {

    // *** Constants ***
    private static final int DEF_VALUE_INT = 0;
    private static final String DEF_VALUE_STR = ">nothingToSeeHere<";

    // *** Declaration and initialisation attributes ***
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int liveID;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "currencyID")
    private Currency currency;

    // 1 to many relationship with items
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "liveID")
    private List<Item> lineItems = new ArrayList<>();
    private int merchantID;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "totalID")
    private Total subTotal;

    // *** Constructors ***


    public Live() {
        this.currency = new Currency();
        this.lineItems = new ArrayList<>();
        this.merchantID = DEF_VALUE_INT;
        this.subTotal = new Total();
    }

    public Live(int liveID, Currency currency, List<Item> lineItems, int merchantID, Total subTotal) {
        this.liveID = liveID;
        this.currency = currency;
        this.lineItems = lineItems;
        this.merchantID = merchantID;
        this.subTotal = subTotal;
    }
    // *** Getter und Setter ***

    public int getLiveID() {
        return liveID;
    }

    public void setLiveID(int liveID) {
        this.liveID = liveID;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public List<Item> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<Item> lineItems) {
        this.lineItems = lineItems;
    }

    public int getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(int merchantID) {
        this.merchantID = merchantID;
    }

    public Total getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Total subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "Live{" +
                "liveID=" + liveID +
                ", currency=" + currency +
                ", lineItems=" + lineItems +
                ", merchantID=" + merchantID +
                ", subTotal=" + subTotal +
                '}';
    }
}
