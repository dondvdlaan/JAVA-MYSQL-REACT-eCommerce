package com.manyroadsdev.server.model;

import javax.persistence.*;

/**
 * Class used to store totals with formatted strings
 */
@Entity
public class Total {

    // *** Constants ***
    private static final int DEF_VALUE_INT = -1;
    private static final String DEF_VALUE_STR = ">nothingToSeeHere<";
    private static final double DEF_VALUE_DBL = 0D;

    // *** Declaration and initialisation of attributes ***
    // Auto increment id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int totalID;
    private String formatted;
    private String formattedWithCode;
    private String formattedWithSymbol;
    private double raw;

    // *** Constructors ***
    public Total() {
        this.formatted = DEF_VALUE_STR;
        this.formattedWithCode = DEF_VALUE_STR;
        this.formattedWithSymbol = DEF_VALUE_STR;
        this.raw = DEF_VALUE_DBL;
    }

    public Total(String formatted, String formattedWithCode, String formattedWithSymbol, double raw) {
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
    public Total(String symbol, double total, String code) {
        this.formatted = String.format("%.2f",total);
        this.formattedWithCode = String.format("%.2f",total) + " " + code;
        this.formattedWithSymbol = symbol + String.format("%.2f",total);
        this.raw = total;
    }

    // *** Getter und Setter ***
    public int getTotalID() {
        return totalID;
    }

    public void setTotalID(Integer totalID) {
        this.totalID = totalID;
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
                "priceID=" + totalID +
                ", formatted='" + formatted + '\'' +
                ", formattedWithCode='" + formattedWithCode + '\'' +
                ", formattedWithSymbol='" + formattedWithSymbol + '\'' +
                ", raw=" + raw +
                '}';
    }
}
