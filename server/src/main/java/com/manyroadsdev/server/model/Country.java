package com.manyroadsdev.server.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  Eligible shipping coutries in Checkout phase
 */
@Entity
public class Country {
    // *** Constants ***
    private static final int DEF_VALUE_INT = -1;
    private static final String DEF_VALUE_STR = ">nothingToSeeHere<";

    // *** Declaration and initialisation attributes ***
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int countryID;
    private String countryCode;
    private String countryName;
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "countryID")
    List<Province> provinces = new ArrayList<>();

    // *** Constructors ***
    public Country() {
    }

    public Country(int countryID, String countryCode, String countryName, List<Province> provinces) {
        this.countryID = countryID;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.provinces = provinces;
    }

    public Country(String countryCode, String countryName) {
        this.countryCode = countryCode;
        this.countryName = countryName;
    }

    // *** Getter und Setter ***

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryID=" + countryID +
                ", countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", provinces=" + provinces +
                '}';
    }
}
