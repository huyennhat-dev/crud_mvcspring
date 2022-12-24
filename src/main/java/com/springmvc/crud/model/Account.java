package com.springmvc.crud.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_account")
public class Account {
    @Id
    @GeneratedValue
    private long id;

    @NotNull(message = "")
    @NotBlank(message = "")
    @Size(min = 3, max = 100, message = "Tên của bạn không hợp lệ!")
    @Column(name = "username")
    private String username;

    @NotNull(message = "")
    @NotBlank(message = "")
    @Size(min = 6, max = 255, message = "Email của bạn không hợp lệ!")
    @Column(name = "email")
    private  String email;

    @NotNull(message = "")
    @NotBlank(message = "")
    @Size(min = 6, message = "Mật khẩu tối thiểu phải có 6 kí tự")
    private String password;

    private  int status;

    public Account(){}

    public Account(long id, String username, String email, String password, int status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
