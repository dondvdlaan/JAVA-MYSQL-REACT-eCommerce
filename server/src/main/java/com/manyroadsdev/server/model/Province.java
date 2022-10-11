package com.manyroadsdev.server.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Eligible shipping provinces in Checkout phase
 */
@Entity
public class Province {
    // *** Constants ***
    private static final int DEF_VALUE_INT = -1;
    private static final String DEF_VALUE_STR = ">nothingToSeeHere<";

    // *** Declaration and initialisation attributes ***
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int provID;
    private String provCode;
    private String provName;
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "provID")
    List<ShippingOption> options = new ArrayList<>();

    // *** Constructors ***

    public Province() {
    }

    public Province(String provCode, String provName) {
        this.provCode = provCode;
        this.provName = provName;
    }

      // *** Getter und Setter ***

    public int getProvID() {
        return provID;
    }

    public void setProvID(int provID) {
        this.provID = provID;
    }

    public String getProvCode() {
        return provCode;
    }

    public void setProvCode(String provCode) {
        this.provCode = provCode;
    }

    public String getProvName() {
        return provName;
    }

    public void setProvName(String provName) {
        this.provName = provName;
    }

    public List<ShippingOption> getOptions() {
        return options;
    }

    public void setOptions(List<ShippingOption> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Province{" +
                "provID=" + provID +
                ", provCode='" + provCode + '\'' +
                ", provName='" + provName + '\'' +
                ", options=" + options +
                '}';
    }
}
