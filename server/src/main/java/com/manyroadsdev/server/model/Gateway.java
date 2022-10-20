package com.manyroadsdev.server.model;

import javax.persistence.*;

/**
 * Payment Gateway (not applied yet in this application)
 */
@Entity
public class Gateway {

    // *** Constants ***
    private static final int DEF_VALUE_INT = -1;
    private static final String DEF_VALUE_STR = ">nothingToSeeHere<";

    // *** Declaration and initialisation attributes ***
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int gatewayID;

    private String gateway;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "cardID")
    private Card card;

    // *** Constructors ***

    public Gateway() {
        this.gateway = DEF_VALUE_STR;
        this.card = new Card();
    }

    public Gateway(String gateway, Card card) {
        this.gateway = gateway;
        this.card = card;
    }

    public Gateway(int gatewayID, String gateway, Card card) {
        this.gatewayID = gatewayID;
        this.gateway = gateway;
        this.card = card;
    }
    // *** Getter und Setter ***

    public int getGatewayID() {
        return gatewayID;
    }

    public void setGatewayID(int gatewayID) {
        this.gatewayID = gatewayID;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "Gateway{" +
                "gatewayID=" + gatewayID +
                ", gateway='" + gateway + '\'' +
                ", card=" + card +
                '}';
    }
}
