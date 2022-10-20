package com.manyroadsdev.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Stripe gateway details (not used yet in this application
 */
@Entity
public class Stripe {

    // *** Constants ***
    private static final int DEF_VALUE_INT = -1;
    private static final String DEF_VALUE_STR = ">nothingToSeeHere<";

    // *** Declaration and initialisation attributes ***
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int stripeID;

    private int paymentMethodID;

    // *** Constructors ***

    public Stripe() {
        this.paymentMethodID = DEF_VALUE_INT;
    }

    public Stripe(int paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    public Stripe(int stripeID, int paymentMethodID) {
        this.stripeID = stripeID;
        this.paymentMethodID = paymentMethodID;
    }
    // *** Getter und Setter ***

    public int getStripeID() {
        return stripeID;
    }

    public void setStripeID(int stripeID) {
        this.stripeID = stripeID;
    }

    public int getPaymentMethodID() {
        return paymentMethodID;
    }

    public void setPaymentMethodID(int paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    @Override
    public String toString() {
        return "Stripe{" +
                "stripeID=" + stripeID +
                ", paymentMethodID=" + paymentMethodID +
                '}';
    }
}
