package com.one.direction.nabehha.data.database.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("userName")
    @Expose
    private String userName;

    @SerializedName("email")
    @Expose
    private String email;


    @SerializedName("password")
    @Expose
    private int password;
    @SerializedName("id")
    private int userId;

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getE_mail() {
        return email;
    }

    public void setE_mail(String email) {
        this.email = email;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Post{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userName=" + userName +
                ", id=" + userId +
                '}';

    }
}
