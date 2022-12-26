package com.springmvc.crud.model;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "tbl_chariot")
public class Chariot {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "user_id")
    private long userID;
    @Column(name = "product_id")
    private long productID;
    @Min(value = 1, message = "Số lượng phải lớn hơn 1")
    private int quantity;

    public  Chariot(){}

    public Chariot(long id, long userID, long productID, int quantity) {
        this.id = id;
        this.userID = userID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
