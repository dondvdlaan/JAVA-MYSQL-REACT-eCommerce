package com.manyroadsdev.server.model;

import javax.persistence.*;

/**
 * Eligible option per shipping province in Checkout phase
 */
@Entity
public class ShippingOption {

    // *** Constants ***
    private static final int DEF_VALUE_INT = -1;
    private static final String DEF_VALUE_STR = ">nothingToSeeHere<";

    // *** Declaration and initialisation attributes ***
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int optionID;
    private String optionDescription;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "priceID")
    private Price price;

    // *** Constructors ***
    public ShippingOption() {
        this.optionDescription = DEF_VALUE_STR;
        this.price = new Price();
    }


    public ShippingOption(String optionDescription, Price price) {
        this.optionDescription = optionDescription;
        this.price = price;
    }

    public ShippingOption(int optionID, String optionDescription, Price price) {
        this.optionID = optionID;
        this.optionDescription = optionDescription;
        this.price = price;
    }
    // *** Getter und Setter ***

    public int getOptionID() {
        return optionID;
    }

    public void setOptionID(int optionID) {
        this.optionID = optionID;
    }

    public String getOptionDescription() {
        return optionDescription;
    }

    public void setOptionDescription(String optionDescription) {
        this.optionDescription = optionDescription;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Option{" +
                "optionID=" + optionID +
                ", optionDescription='" + optionDescription + '\'' +
                ", price=" + price +
                '}';
    }
}
