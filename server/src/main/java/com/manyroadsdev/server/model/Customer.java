package com.manyroadsdev.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Customer details
 */
@Entity
public class Customer {

    // *** Constants ***
    private static final int DEF_VALUE_INT = -1;
    private static final String DEF_VALUE_STR = ">nothingToSeeHere<";

    // *** Declaration and initialisation attributes ***
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int custID;

    private String custFirstName;

    private String custLastName;

    private String custEmail;

    // *** Constructors ***

    public Customer() {
        this.custFirstName = DEF_VALUE_STR;
        this.custLastName = DEF_VALUE_STR;
        this.custEmail = DEF_VALUE_STR;
    }

    public Customer(String customerFirstName, String customerLastName, String customerEmail) {
        this.custFirstName = customerFirstName;
        this.custLastName = customerLastName;
        this.custEmail = customerEmail;
    }

    public Customer(int customerID, String customerFirstName, String customerLastName, String customerEmail) {
        this.custID = customerID;
        this.custFirstName = customerFirstName;
        this.custLastName = customerLastName;
        this.custEmail = customerEmail;
    }
    // *** Getter und Setter ***

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public String getCustFirstName() {
        return custFirstName;
    }

    public void setCustFirstName(String custFirstName) {
        this.custFirstName = custFirstName;
    }

    public String getCustLastName() {
        return custLastName;
    }

    public void setCustLastName(String custLastName) {
        this.custLastName = custLastName;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    @Override
    public String toString() {
        return "Customeromer{" +
                "customerID=" + custID +
                ", customerFirstName='" + custFirstName + '\'' +
                ", customerLastName='" + custLastName + '\'' +
                ", customerEmail='" + custEmail + '\'' +
                '}';
    }
}
