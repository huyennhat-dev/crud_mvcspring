package com.springmvc.crud.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_oder")
public class Oder {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "product_id")
    private long productId;
    private int quantity;
    @Column(name = "number_phone")
    private String numberPhone;
    private String address;
    @Column(name = "oder_time")
    private String oderTime;
    private int status;

    public Oder(){}

    public Oder(long id, long userId, long productId, int quantity, String numberPhone, String address, String oderTime, int status) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.numberPhone = numberPhone;
        this.address = address;
        this.oderTime = oderTime;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOderTime() {
        return oderTime;
    }

    public void setOderTime(String oderTime) {
        this.oderTime = oderTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
