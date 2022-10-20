package com.manyroadsdev.server.model;

import javax.persistence.*;

/**
 * Class Item is a central theme which goes from the cart to a possible new order
 */
@Entity
public class Item {

    // Constants
    private static final int DEF_VALUE_INT = -1;
    private static final String DEF_VALUE_STR = ">nothingToSeeHere<";

    // Declaration and initialisation attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemID;
    private String itemImage;
    private String productName;
    private int itemQuantity;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "totalID")
    private Total lineTotal;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "priceID")
    private Price price;
    private int prodID;

    // *** Constructors ***

    public Item() {
        this.itemImage = DEF_VALUE_STR;
        this.productName = DEF_VALUE_STR;
        this.itemQuantity = DEF_VALUE_INT;
        this.lineTotal = new Total();
        this.price = new Price();
        this.prodID = DEF_VALUE_INT;
    }

    public Item(String itemImage, String productName, int itemQuantity, Total lineTotal, Price price, int prodID) {
        this.itemImage = itemImage;
        this.productName = productName;
        this.itemQuantity = itemQuantity;
        this.lineTotal = lineTotal;
        this.price = price;
        this.prodID = prodID;
    }

    public Item(int itemID, String itemImage, String productName, int itemQuantity, Total lineTotal, Price price, int prodID) {
        this.itemID = itemID;
        this.itemImage = itemImage;
        this.productName = productName;
        this.itemQuantity = itemQuantity;
        this.lineTotal = lineTotal;
        this.price = price;
        this.prodID = prodID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Total getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(Total lineTotal) {
        this.lineTotal = lineTotal;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public int getProdID() {
        return prodID;
    }

    public void setProdID(int prodID) {
        this.prodID = prodID;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemID=" + itemID +
                ", itemImage='" + itemImage + '\'' +
                ", productName='" + productName + '\'' +
                ", itemQuantity=" + itemQuantity +
                ", lineTotal=" + lineTotal +
                ", price=" + price +
                ", prodID=" + prodID +
                '}';
    }
}
