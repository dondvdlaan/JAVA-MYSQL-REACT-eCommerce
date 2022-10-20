package com.manyroadsdev.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * General address details used for billing and shipping
 */
@Entity
public class Address {

    // *** Constants ***
    private static final int DEF_VALUE_INT = -1;
    private static final String DEF_VALUE_STR = ">nothingToSeeHere<";

    // *** Declaration and initialisation attributes ***
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int addressID;

    private String addressName;

    private String addressStreet;

    private String addressTownCity;

    private String addressCountryState;

    private String addressZipCode;

    private String addressCountry;

    // *** Constructors ***
    public Address() {
        this.addressName = DEF_VALUE_STR;
        this.addressStreet = DEF_VALUE_STR;
        this.addressTownCity = DEF_VALUE_STR;
        this.addressCountryState = DEF_VALUE_STR;
        this.addressZipCode = DEF_VALUE_STR;
        this.addressCountry = DEF_VALUE_STR;
    }

    public Address(String addressName, String addressStreet, String addressTownCity, String addressCountryState, String addressZipCode, String addressCountry) {
        this.addressName = addressName;
        this.addressStreet = addressStreet;
        this.addressTownCity = addressTownCity;
        this.addressCountryState = addressCountryState;
        this.addressZipCode = addressZipCode;
        this.addressCountry = addressCountry;
    }

    public Address(int addressID, String addressName, String addressStreet, String addressTownCity, String addressCountryState, String addressZipCode, String addressCountry) {
        this.addressID = addressID;
        this.addressName = addressName;
        this.addressStreet = addressStreet;
        this.addressTownCity = addressTownCity;
        this.addressCountryState = addressCountryState;
        this.addressZipCode = addressZipCode;
        this.addressCountry = addressCountry;
    }

    // *** Getter und Setter ***
    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressTownCity() {
        return addressTownCity;
    }

    public void setAddressTownCity(String addressTownCity) {
        this.addressTownCity = addressTownCity;
    }

    public String getAddressCountryState() {
        return addressCountryState;
    }

    public void setAddressCountryState(String addressCountryState) {
        this.addressCountryState = addressCountryState;
    }

    public String getAddressZipCode() {
        return addressZipCode;
    }

    public void setAddressZipCode(String addressZipCode) {
        this.addressZipCode = addressZipCode;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressID=" + addressID +
                ", addressName='" + addressName + '\'' +
                ", addressStreet='" + addressStreet + '\'' +
                ", addressTownCity='" + addressTownCity + '\'' +
                ", addressCountryState='" + addressCountryState + '\'' +
                ", addressZipCode='" + addressZipCode + '\'' +
                ", addressCountry='" + addressCountry + '\'' +
                '}';
    }
}
