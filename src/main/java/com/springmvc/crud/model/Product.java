package com.springmvc.crud.model;

import javax.persistence.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_product")
public class Product {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "category_id")
    private long categoryID;

    @NotNull(message = "")
    @NotBlank(message = "")
    @Size(min = 3, max = 100, message = "Tên sản phẩm không hợp lệ")
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_photo")
    private String productPhoto;
    private String slug;
    @Column(name = "photo_id")
    private String photoID;

    @NotNull(message = "")
    @Min(value = 0, message = "Gía phải lớn hơn 0")
    private long price;

    @Min(value = 1, message = "Số lượng phải lớn hơn 1")
    private int quantity;

    @NotNull(message = "")
    @NotBlank(message = "")
    @Size(min = 5, message = "Mô tả không hợp lệ")
    @Column(name = "description", length = 65555)
    private String description;

    @Column(name = "quantity_purchased")
    private int quantityPurchased;

    private int status;

    @Column(name = "upload_time")
    private String uploadTime;
    @Column(name = "update_time")
    private String updateTime;

    public Product() {
    }

    public Product(long id, long categoryID, String productName, String productPhoto,
                   String slug, String photoID, long price, int quantity, String description, int quantityPurchased,
                   int status, String uploadTime, String updateTime) {
        this.id = id;
        this.categoryID = categoryID;
        this.productName = productName;
        this.productPhoto = productPhoto;
        this.slug = slug;
        this.photoID = photoID;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.quantityPurchased = quantityPurchased;
        this.status = status;
        this.uploadTime = uploadTime;
        this.updateTime = updateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(String productPhoto) {
        this.productPhoto = productPhoto;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(int quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPhotoID() {
        return photoID;
    }

    public void setPhotoID(String photoID) {
        this.photoID = photoID;
    }
}
