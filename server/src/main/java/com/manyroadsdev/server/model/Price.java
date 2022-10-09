package com.manyroadsdev.server.model;

import javax.persistence.*;

@Entity
public class Price {
    // Auto increment id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int priceID;
    private String formatted;
    private String formattedWithCode;
    private String formattedWithSymbol;
    private double raw;

    public Price() {
    }

    public Price(String formatted, String formattedWithCode, String formattedWithSymbol, double raw) {
        this.formatted = formatted;
        this.formattedWithCode = formattedWithCode;
        this.formattedWithSymbol = formattedWithSymbol;
        this.raw = raw;
    }
    /**
     *  Constructor for special format:
     *     formatted :            "5.00"
     *     formatted_with_code:   "5.00 GBP"
     *     formatted_with_symbol: "£5.00"
     *     raw:                    5
     */
    public Price(String symbol, double price, String code) {
        this.formatted = Double.toString(price);
        this.formattedWithCode = Double.toString(price) + " " + code;
        this.formattedWithSymbol = symbol + Double.toString(price);
        this.raw = price;
    }

    public Integer getPriceID() {
        return priceID;
    }

    public void setPriceID(Integer priceID) {
        this.priceID = priceID;
    }

    public String getFormatted() {
        return formatted;
    }

    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }

    public String getFormattedWithCode() {
        return formattedWithCode;
    }

    public void setFormattedWithCode(String formattedWithCode) {
        this.formattedWithCode = formattedWithCode;
    }

    public String getFormattedWithSymbol() {
        return formattedWithSymbol;
    }

    public void setFormattedWithSymbol(String formattedWithSymbol) {
        this.formattedWithSymbol = formattedWithSymbol;
    }

    public double getRaw() {
        return raw;
    }

    public void setRaw(double raw) {
        this.raw = raw;
    }

    @Override
    public String toString() {
        return "Price{" +
                "priceID=" + priceID +
                ", formatted='" + formatted + '\'' +
                ", formattedWithCode='" + formattedWithCode + '\'' +
                ", formattedWithSymbol='" + formattedWithSymbol + '\'' +
                ", raw=" + raw +
                '}';
    }
}
