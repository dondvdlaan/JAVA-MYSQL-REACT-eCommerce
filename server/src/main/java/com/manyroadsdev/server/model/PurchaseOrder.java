package com.manyroadsdev.server.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Customer Order
 */
@Entity
public class PurchaseOrder {

    // *** Constants ***
    private static final int DEF_VALUE_INT = -1;
    private static final String DEF_VALUE_STR = ">nothingToSeeHere<";

    // *** Declaration and initialisation attributes ***
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderID;

    // Unidirectional 1-to-many Items relationship. @JoinColumn orderID is FK at Item
    @OneToMany
    @JoinColumn(name = "orderID")
    private List<Item> orderLineItems;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "custID")
    private Customer customer;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "AddressID")
    private Address shipping;

    private int fulfillment;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "AddressID", insertable=false ,updatable=false)
    private Address billing;
    private java.util.Date orderDate;
    private String customerReference;

    // *** Constructors ***
    public PurchaseOrder() {
        this.orderLineItems = new ArrayList<>();
        this.customer = new Customer();
        this.shipping = new Address();
        this.fulfillment = DEF_VALUE_INT;
        this.billing = new Address();
        this.orderDate = new Date();
        this.customerReference = DEF_VALUE_STR;
    }

    public PurchaseOrder(List<Item> orderLineItems, Customer customer, Address shipping, int fulfillment, Address billing, Date orderDate, String customerReference) {
        this.orderLineItems = orderLineItems;
        this.customer = customer;
        this.shipping = shipping;
        this.fulfillment = fulfillment;
        this.billing = billing;
        this.orderDate = orderDate;
        this.customerReference = customerReference;
    }

    public PurchaseOrder(int orderID, List<Item> orderLineItems, Customer customer, Address shipping, int fulfillment, Address billing, Date orderDate, String customerReference) {
        this.orderID = orderID;
        this.orderLineItems = orderLineItems;
        this.customer = customer;
        this.shipping = shipping;
        this.fulfillment = fulfillment;
        this.billing = billing;
        this.orderDate = orderDate;
        this.customerReference = customerReference;
    }
    // *** Getter und Setter ***
    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String custLastName, int POID) {
        this.customerReference = custLastName + " - " + POID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public List<Item> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(List<Item> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getShipping() {
        return shipping;
    }

    public void setShipping(Address shipping) {
        this.shipping = shipping;
    }

    public int getFulfillment() {
        return fulfillment;
    }

    public void setFulfillment(int fulfillment) {
        this.fulfillment = fulfillment;
    }

    public Address getBilling() {
        return billing;
    }

    public void setBilling(Address billing) {
        this.billing = billing;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", orderLineItems=" + orderLineItems +
                ", customer=" + customer +
                ", shipping=" + shipping +
                ", fulfillment=" + fulfillment +
                ", billing=" + billing +
                ", orderDate=" + orderDate +
                '}';
    }
}
