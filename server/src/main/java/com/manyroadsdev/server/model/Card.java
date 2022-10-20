package com.manyroadsdev.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Payment card
 */
@Entity
public class Card {
    // *** Constants ***
    private static final int DEF_VALUE_INT = -1;
    private static final String DEF_VALUE_STR = ">nothingToSeeHere<";

    // *** Declaration and initialisation attributes ***
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cardID;

    private int paymentMethodID;

    // *** Constructors ***

    public Card() {
        this.paymentMethodID = DEF_VALUE_INT;
    }

    public Card(int paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    public Card(int cardID, int paymentMethodID) {
        this.cardID = cardID;
        this.paymentMethodID = paymentMethodID;
    }
    // *** Getter und Setter ***

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public int getPaymentMethodID() {
        return paymentMethodID;
    }

    public void setPaymentMethodID(int paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardID=" + cardID +
                ", paymentMethodID=" + paymentMethodID +
                '}';
    }
}
