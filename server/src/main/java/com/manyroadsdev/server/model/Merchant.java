package com.manyroadsdev.server.model;

import javax.persistence.*;

/**
 * This class lists the selling parties in the eCommerce application
 */
@Entity
public class Merchant {

    // *** Constants ***
    private static final int DEF_VALUE_INT = 0;
    private static final String DEF_VALUE_STR = ">nothingToSeeHere<";

    // *** Declaration and initialisation attributes ***
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int merchantID;
    private String businessDescription;
    private String businessName;
    private String country;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "currencyID")
    private Currency currency;
    private String logo;
    private String status;
    private String supportEmail;

    // *** Constructors ***

    public Merchant() {
        this.businessDescription = DEF_VALUE_STR;
        this.businessName = DEF_VALUE_STR;
        this.country = DEF_VALUE_STR;
        this.currency = new Currency();
        this.logo = DEF_VALUE_STR;
        this.status = DEF_VALUE_STR;
        this.supportEmail = DEF_VALUE_STR;
    }

    public Merchant(int merchantID, String businessDescription, String businessName, String country, Currency currency, String logo, String status, String supportEmail) {
        this.merchantID = merchantID;
        this.businessDescription = businessDescription;
        this.businessName = businessName;
        this.country = country;
        this.currency = currency;
        this.logo = logo;
        this.status = status;
        this.supportEmail = supportEmail;
    }

    public int getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(int merchantID) {
        this.merchantID = merchantID;
    }

    public String getBusinessDescription() {
        return businessDescription;
    }

    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSupportEmail() {
        return supportEmail;
    }

    public void setSupportEmail(String supportEmail) {
        this.supportEmail = supportEmail;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "merchantID=" + merchantID +
                ", businessDescription='" + businessDescription + '\'' +
                ", businessName='" + businessName + '\'' +
                ", country='" + country + '\'' +
                ", currency=" + currency +
                ", logo='" + logo + '\'' +
                ", status='" + status + '\'' +
                ", supportEmail='" + supportEmail + '\'' +
                '}';
    }
}
