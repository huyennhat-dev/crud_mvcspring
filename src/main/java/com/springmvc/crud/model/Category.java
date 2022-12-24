package com.springmvc.crud.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_category")
public class Category {
    @Id
    @GeneratedValue
    private long id;

    @NotNull(message = "")
    @NotBlank(message = "")
    @Size(min = 3, max = 30, message = "Tên danh mục không hợp lệ!")
    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "slug")
    private  String slug;
    private int status;
    public Category(){}
    public Category(long id, String categoryName,String slug, int status) {
        this.id = id;
        this.categoryName = categoryName;
        this.slug = slug;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
