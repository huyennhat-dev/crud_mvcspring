package com.springmvc.crud.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

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

    @NotNull(message = "")
    @NotBlank(message = "")
    @Size(min = 5, max = 1000, message = "Mô tả không hợp lệ")
    private String description;

    private int status;

    @Column(name = "upload_time")
    private String uploadTime;
    @Column(name = "update_time")
    private String updateTime;
    public Product(){}

    public Product(long id, long categoryID, String productName, String productPhoto, String slug, String description, int status, String uploadTime, String updateTime) {
        this.id = id;
        this.categoryID = categoryID;
        this.productName = productName;
        this.productPhoto = productPhoto;
        this.slug = slug;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
