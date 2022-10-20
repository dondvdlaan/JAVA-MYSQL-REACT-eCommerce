package com.manyroadsdev.server.model;

import javax.persistence.*;

/**
 * This class sets the currency to be used per session
 */

@Entity
public class Currency {

    // Constants
    private static final String DEF_VALUE_STR = ">nothingToSeeHere<";

    // Declaration and initialisation attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer currencyID;
    private String code;
    private String symbol;

    // Constructors
    public Currency() {
        this.code = DEF_VALUE_STR;
        this.symbol = DEF_VALUE_STR;
    }

    public Currency(String code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }

    public Currency(Integer currencyID, String code, String symbol) {
        this.currencyID = currencyID;
        this.code = code;
        this.symbol = symbol;
    }
    // *** Getter und Setter ***
    public Integer getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(Integer currencyID) {
        this.currencyID = currencyID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyID=" + currencyID +
                ", code='" + code + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
